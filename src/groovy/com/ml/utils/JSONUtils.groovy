package com.ml.utils

class JSONUtils {

	static def toMap(json){
		grails.converters.JSON.parse(json);
	}
}
