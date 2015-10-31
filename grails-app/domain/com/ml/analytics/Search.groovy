package com.ml.analytics

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString(includeNames = true, includeFields = true, excludes = 'dateCreated,lastUpdated,metaClass,class')
@EqualsAndHashCode
class Search {

    String name;

	Search(name){
		this.name=name;
	}
    static constraints = {
        name blank:false
    }
}
