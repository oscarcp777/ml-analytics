package com.ml.security


import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

@Secured(['ROLE_USER'])
class HomeController{
	SpringSecurityService springSecurityService;
    def index() {
		if(!session.user){
			session.user=springSecurityService.currentUser
		}
		[urlUser:'img/icon/user-face.jpeg',user:session.user]
	}
	
	
}
