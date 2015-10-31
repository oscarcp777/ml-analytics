package com.ml.analytics

import org.junit.Test;
import com.ml.utils.JSONUtils

class ClientApiMLServiceSpec  {

	@Test
    void testApiMLUserData() {
		def service=new ClientApiMLService();
		def map=JSONUtils.toMap(service.getDataUser('146821722'))
		assert map.nickname == 'ALMASHOPPING ARGENTI'
		assert map.id == 146821722
		
    }
	@Test
	void testApiMLSearchData() {
		def service=new ClientApiMLService();
		def map=JSONUtils.toMap(service.getDataSearch('ford mustang'))
		assert map.paging.total == 29
		assert map.filters[0].id == 'category'
		
	}
}
