package com.ml.security
import grails.converters.JSON

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class PublicController {

    def springSecurityService

    def index() { 
        if (springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
        }
    }
	
	
}
