package com.ml.analytics
import grails.converters.JSON
import groovy.json.JsonSlurper

import org.jongo.*

import com.mongodb.*
class MongoService {
	
	def getCollection(name){
		MongoClient client = new MongoClient("localhost")
		DB db = client.getDB("analytics")
		Jongo jongo = new Jongo(db);
		jongo.getCollection(name);
	}
	def insertDataSearch(json){
		MongoCollection search = getCollection("searchsData")
//		DBObject dbObject = (DBObject)JSON.parse(json);
		def result=search.insert(json)
		print ' insert result '+result
		
	}
	
	
	// Devuelve mapa con formato [cantidadVendida:frecuenciaAparicion]
	def  getSalesHistogram(String searchText){
		MongoCollection searches = getCollection("searchsData")
		def result=searches.aggregate("{\$match:{query:\""+searchText+"\"}}")
										.and("{\$unwind:\"\$results\"}")
										.and("{\$project:{_id:{seller:\"\$results.seller\",soldQuantity:\"\$results.sold_quantity\"}}}")
										.and("{\$group:{_id:\"\$_id.seller.id\", idem:{\$sum:1},totalSold:{\$sum:\"\$_id.soldQuantity\"}}}")
										.and("{\$group:{_id:\"\$totalSold\",freq:{\$sum:1}}}")
										.and("{\$sort:{_id:1}}]").as(SalesHistogramResult.class);
		def mapResult = [:]
		
		result.results.each {
			mapResult.put(it._id.toString(), it.freq)
		}
		mapResult
	}
	
	private class SalesHistogramResult{
		Integer _id;
		Integer freq;
	}
	private class QuantityXCategoryResult{
		String _id;
		Integer cant;
	}
	/**
	 * db.searchsData.aggregate([{$match:{query:"ipod"}},
	 * {$project:{_id:"$results"}},{$unwind:"$_id"},
	 * {$group:{_id:"$_id.category_id",cant:{$sum:1}}}])
	 */
	def getQuantityXCategory(text){
		MongoCollection searches = getCollection("searchsData")
		def result=searches.aggregate("{\$match:{query:\""+text+"\"}}")
								 .and("{\$project:{_id:\"\$results\"}}")
								 .and("{\$unwind:\"\$_id\"}")
								 .and("{\$group:{_id:\"\$_id.category_id\",cant:{\$sum:1}}}").as(QuantityXCategoryResult.class);
		def mapResult = [:]
		result.results.each {
			mapResult.put(it._id.toString(), it.cant)
		}
		mapResult
	}
	// Devuelve entero con cantidad de usuarios de la pagina
	def Integer getUsersQuantity(String searchText){
		MongoCollection searches = getCollection("searchsData")
		def result=searches.aggregate("{\$match:{query:\""+searchText+"\"}}")
										.and("{\$project:{_id:\"\$results.seller\"}}")
										.and("{\$unwind:\"\$_id\"}")
										.and("{\$group:{_id:\"\$_id.id\"}}")
										.and("{\$group:{_id:null,cant:{\$sum:1}}}").as(UsersQuantity.class);
		def usersQuantity = null
		result.results.each {
			usersQuantity = it.cant
		}
		usersQuantity
	}
	
	private class UsersQuantity{
		Integer cant;
	}
	
	def getSalesRanking(String searchText){
		MongoCollection searches = getCollection("searchsData")
		def result=searches.aggregate("{\$match:{query:\""+searchText+"\"}}")
										.and("{\$unwind:\"\$results\"}")
										.and("{\$project:{_id:\"\$results.seller.id\",sellerReputation:\"\$results.seller.power_seller_status\",soldQuantity:\"\$results.sold_quantity\"}}")
										.and("{\$sort:{\"soldQuantity\":-1}}")
										.and("{\$limit:10}]").as(SalesRankingResult.class);
		
		def mapResult = [:]
		result.results.each{
			mapResult[it._id.toString()]=['reputation':it.sellerReputation,'soldQuantity':it.soldQuantity]
		}
		mapResult
	}
	
	private class SalesRankingResult{
		Long _id;
		String sellerReputation;
		Integer soldQuantity;
	}
	
}
