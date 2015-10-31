package com.ml.analytics
import org.springframework.transaction.annotation.Transactional;

import com.ml.analytics.MongoService
import com.ml.analytics.ClientApiMLService
import com.ml.utils.JSONUtils;
class SearchService {

	def clientApiMLService
	def mongoService
	
	def generateInfo(text){
		def json=clientApiMLService.getDataSearch(text);
		mongoService.insertDataSearch(json);
		print 'todo OK'
	}
	@Transactional
	def getHistogramSales(idSearch){
		def data=[:]
		Search search=Search.get(idSearch)
		Analytics histo=Analytics.findByTextAndType(search.name,Analytics.TypeAnalytics.HISTOGRAM_SALES)
		if(histo==null){
			data=mongoService.getSalesHistogram(search.name)
			Analytics stats= new Analytics(type:Analytics.TypeAnalytics.HISTOGRAM_SALES,text:search.name,data:data);
			stats.save(flush: true)
		}else{
		     data=histo.data
		}
		data
	}
	
	@Transactional
	def getRankingSales(idSearch){
		def data=[:]
		Search search=Search.get(idSearch)
		Analytics bestSellers=Analytics.findByTextAndType(search.name,Analytics.TypeAnalytics.BEST_SELLERS)
		if(bestSellers==null){
			data=mongoService.getSalesRanking(search.name)
			Analytics stats= new Analytics(type:Analytics.TypeAnalytics.BEST_SELLERS,text:search.name,data:data);
			stats.save(flush: true)
		}else{
			 data=bestSellers.data
		}
		data
	}
	
	def getDataUsers(listIds){
		def strList=''
		listIds.each{
			strList+=it+','
		}
		def json =clientApiMLService.getDataUser(strList[0..-2])
		def mapUsers=JSONUtils.toMap(json)
		def mapResult=[:]
		mapUsers.each {
			mapResult.put(it.id.toString(), it)
		}
		mapResult
	}
	
	@Transactional
	def getUsersQuantity(idSearch){
		def data=[:]
		Search search=Search.get(idSearch)
		Analytics histo=Analytics.findByTextAndType(search.name,Analytics.TypeAnalytics.USERS_QUANTITY)
		if(histo==null){
			data=['50':mongoService.getUsersQuantity(search.name)]
			Analytics stats= new Analytics(type:Analytics.TypeAnalytics.USERS_QUANTITY,text:search.name,data:data);
			stats.save(flush: true)
		}else{
		     print histo.data
			 data=histo.data
		}
		data
	}
	def getQuantityXCategory(idSearch){
		def data=[:]
		Search search=Search.get(idSearch)
		Analytics histo=Analytics.findByTextAndType(search.name,Analytics.TypeAnalytics.QUANTITY_CATEGORY)
		if(histo==null){
			data=mongoService.getQuantityXCategory(search.name)
			print data
			Analytics stats= new Analytics(type:Analytics.TypeAnalytics.QUANTITY_CATEGORY,text:search.name,data:data);
			stats.save(flush: true)
		}else{
			 data=histo.data
		}
		data
	}
}
