package com.ml.analytics

import java.util.Map;

class Analytics {

	Map data
	TypeAnalytics type
	String text
   
	
	enum TypeAnalytics {
		QUANTITY_CATEGORY(1),
		HISTOGRAM_SALES(2),
		USERS_QUANTITY(3),
		BEST_SELLERS(4)
	 
		final int id
		private TypeAnalytics(int id) { this.id = id }
	}
	static constraints = {
	    type blank: false
		text blank: false
	 }
}
