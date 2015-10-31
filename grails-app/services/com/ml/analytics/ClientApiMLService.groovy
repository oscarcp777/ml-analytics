package com.ml.analytics


import grails.plugins.rest.client.RestBuilder

class ClientApiMLService {

	final ML_API_URL='https://api.mercadolibre.com/'
	final ML_API_USERS='users?ids='
	final ML_API_CATEGORIES='categories/'
	final ML_API_SEARCHS='sites/MLA/search?q='
	def rest
	ClientApiMLService(){
		super()
		rest = new RestBuilder()
	}
	
    def getDataUser(custIds) {
		print custIds
		def entity =rest.get(ML_API_URL+ML_API_USERS+custIds);
		entity.getBody();
    }
	def getDataCategory(id) {
		def entity =rest.get(ML_API_URL+ML_API_CATEGORIES+id);
		entity.getBody();
	}
	def getDataSearch(text) {
		def entity =rest.get(ML_API_URL+ML_API_SEARCHS+text);
		entity.getBody();
	}
	 
	
}
