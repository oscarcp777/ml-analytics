package com.ml.analytics



import static org.springframework.http.HttpStatus.*
import grails.plugins.springsecurity.SpringSecurityService;
import grails.plugins.springsecurity.Secured
import grails.rest.RestfulController
import grails.transaction.Transactional
import com.ml.analytics.SearchService
@Transactional(readOnly = true)
@Secured(['ROLE_USER'])
class SearchController  extends RestfulController {
	def searchService
    static responseFormats = ['json']
	SpringSecurityService springSecurityService;
    SearchController() {
        super(Search)
    }

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
		UserML user=springSecurityService.currentUser
        respond user.searchs, model:[searchCount: user.searchs.size()]
    }

   
    @Transactional
    def save(Search searchInstance) {
        if (searchInstance == null) {
            notFound()
            return
        }

        if (searchInstance.hasErrors()) {
            respond searchInstance.errors, view:'create'
            return
        }

        searchInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'searchInstance.label', default: 'Search'), searchInstance.id])
                redirect searchInstance
            }
            '*' { respond searchInstance, [status: CREATED] }
        }
			
		
		UserML user=springSecurityService.currentUser
		user.searchs.add(searchInstance)
		user.save(flush:true)
		searchService.generateInfo(searchInstance.name);
    }

   
    @Transactional
    def update(Search searchInstance) {
        if (searchInstance == null) {
            notFound()
            return
        }

        if (searchInstance.hasErrors()) {
            respond searchInstance.errors, view:'edit'
            return
        }

        searchInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Search.label', default: 'Search'), searchInstance.id])
                redirect searchInstance
            }
            '*'{ respond searchInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Search searchInstance) {

        if (searchInstance == null) {
            notFound()
            return
        }

        searchInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Search.label', default: 'Search'), searchInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
		UserML user=springSecurityService.currentUser
		user.searchs.remove(searchInstance)
		user.save(flush:true)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'searchInstance.label', default: 'Search'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
