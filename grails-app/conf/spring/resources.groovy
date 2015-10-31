// Place your Spring DSL code here
import grails.rest.render.json.*
import com.ml.analytics.Search
 
beans = {
    
    searchJSONRenderer(JsonRenderer, Search) {
        excludes = ['class', 'dateCreated']
    }
    
    searchJSONCollectionRenderer(JsonCollectionRenderer, Search) {
        excludes = ['class', 'dateCreated']
    }
}
