package com.ml.security
import com.ml.analytics.UserML
class FacebookUser {
	Long uid
	String accessToken
	Date accessTokenExpires
	static belongsTo = [user: UserML] //connected to main Spring Security domain
  
	static constraints = {
	  uid unique: true
	}
    
}
