package com.ml.analytics

import static org.springframework.http.HttpStatus.*

import grails.converters.JSON
import grails.plugins.springsecurity.SpringSecurityService;
import grails.plugins.springsecurity.Secured
import grails.rest.RestfulController
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_USER'])
class AnalyticsController {
	static responseFormats = ['json']
	def searchService
	def bestSellers={
		log.info 'bestSellers ' +params
		def bestSellers = searchService.getRankingSales(params.id)
		def usersIds=[]
		bestSellers.each{
			usersIds.add(it.key)
		}
		def mapUsers=searchService.getDataUsers(usersIds)
		def dataJson=[]
		bestSellers.each{
			def user=mapUsers.get(it.key)
			def transactions= user.seller_reputation.transactions
			dataJson.add([name:user.nickname,
				          link:'http://perfil.mercadolibre.com.ar/profile/showProfile?id='+it.key+'&role=buyer',
						  review:transactions.ratings.positive*100,
						  status:it.value.reputation ?: "Normal",
						  totalSales:transactions.total,
						  searchSales:it.value.soldQuantity])
		}
		render dataJson as JSON 
	}
	
	def histogramSales={
		log.info 'histogramSales ' +params
		def histogram=searchService.getHistogramSales(params.id)
		def listData=[]
		histogram.each{k, v -> 
			listData.add([k,v])
		}
		def series=[[name:'Promedios',data:listData]]
		def dataJson=getBarCharData(params.id,'Histograma de ventas por usuarios','Cantidad de Productos vendidos','Cantidad de Vendedores',series)
		render dataJson as JSON
	}
	def usersQuantity={
		log.info 'dataUsersQuantity ' +params
		def users=searchService.getUsersQuantity(params.id)
		print users
		def max=0
		def series=[]
		users.each { key, value ->
			max=key
			series.add(value)
		}
		def dataJson=getSolidGaugeCharData(params.id,'Cantidad de vendedores distintos','Vendedores',max,series)
		render dataJson as JSON
	}
	def quantityXCategory={
		log.info 'quantityXCategory ' +params
		def data=searchService.getQuantityXCategory(params.id)
		def listData=[]
		data.each{k, v ->
			listData.add([k,v])
		}
		def series=[[name:'Categorias',data:listData]]
		def dataJson=getPieCharData(params.id,'Cantidad de Productos por Categoria','Categorias',listData)
		render dataJson as JSON
	}
	private def getLineCharData(id,title,titleX,series,titleY,serieX){
		[id:id,title:title,titleX:titleX,serieX:serieX,titleY:titleY,series:series]
	}
	private def getPieCharData(id,title,name,data){
		[id:id,title:title,name:name,data:data]
	}
	private def getBarCharData(id,title,xAxis,yAxis,series){
		[id:id,title:title,xAxis:xAxis,yAxis:yAxis,series:series]
	}
	private def getSolidGaugeCharData(id,title,xAxis,max,series){
		[id:id,title:title,xAxis:xAxis,max:max,series:series]
	}
}
