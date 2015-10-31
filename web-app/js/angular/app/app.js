(function () {
  'use strict';
  angular.module('ml', ['ngRoute', 'ml.controllers','xeditable','highcharts-ng','ui.bootstrap','ngTable']);

  function config ($routeProvider) {  
	  $routeProvider
	    .when('/', {
	      templateUrl: 'views/configSearchs.html',
	      controller: 'SearchesCtrl',
	      controllerAs: 'search'
	    })
	    .when('/stats/:itemId', {
	      templateUrl: 'views/stats.html',
	      controller: 'StatsCtrl',
	      controllerAs: 'stats'
	    }).otherwise({
	          redirectTo: '/'
	    });
	}
  angular.module('ml').config(config);
})();
