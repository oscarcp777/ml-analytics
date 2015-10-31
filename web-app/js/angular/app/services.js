(function () {
  'use strict';
  angular.module('ml.services', ['ngResource']);

  function Searches($resource) {
	  return $resource('api/searchs/:id');
  }
  function Menu(){
	  var menu={};
	  menu.list=[];
	  menu.update = function(searches){
		  menu.list=searches;
	  };
	  return menu;
  }
  function Analytics($resource) {
	   return { quantityXCategory: $resource('analytics/quantityXCategory/:id'),
	    		bestSellers: $resource('analytics/bestSellers/:id'),
	    		histogramSales: $resource('analytics/histogramSales/:id'),
	    		usersQuantity: $resource('analytics/usersQuantity/:id')
	    	   };
  }
  angular.module('ml.services')
         .factory('Searches',Searches)
         .factory('Analytics',Analytics)
         .factory('Menu',Menu);

})();
