package com.ml.analytics

import org.junit.Test;

class MongoServiceTest {

	@Test
	void testExecuteQuery(){
		def service=new MongoService()
		println service.getSalesHistogram("ipod")
		println service.getUsersQuantity("ipod")
		println service.getQuantityXCategory("ipod")
		println service.getSalesRanking("ipod")
	}
}
