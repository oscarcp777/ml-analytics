package com.ml.analytics

import com.ml.security.SecUser;


class UserML extends SecUser{
	
	String firstName
	String lastName
	static hasMany = [ searchs : Search ]
	static constraints = {
		firstName(blank:false)
		lastName(blank:false)
	}
	
	@Override
	public String toString() {
		return firstName +' '+lastName;
	}
}
