(function () {
  'use strict';

  angular.module('ml.controllers', ['ml.services']);

  function SearchesCtrl($rootScope,$scope,$uibModal,Searches,Menu,NgTableParams) {
	  var self=this;
      Searches.query(function(data) {
    	  self.searches = data;
    	  self.tableParams = new NgTableParams({count: 5}, { counts:[],data:data});
      });

      this.saveSearch = function(data, id) {
	   data.id=id;
	   Searches.save(data);
	   self.tableParams = new NgTableParams({count: 5}, { counts:[],data:self.searches});
	   Menu.update(self.searches);
	   $rootScope.refreshMenu={change:true};
	  };
	
	  // remove user
	  this.removeSearch = function(index,id) {
		  Searches.delete({id:id},function(data) {
			    var search=self.searches[index];
	    		var del=self.searches.splice(self.searches.indexOf(search), 1);
	    		self.tableParams = new NgTableParams({count: 5}, { counts:[],data:self.searches});
	    		Menu.update(self.searches);
	    		$rootScope.refreshMenu={change:true};
	        });
	  };
	
	  // add user
	  this.addSearch = function() {
		  var modalInstance = $uibModal.open({
		      animation: true,
		      templateUrl: 'views/modalAddSearch.html',
		      controller: 'ModalCtrl'
		    });
		    modalInstance.result.then(function (search) {
		    	Searches.save({name:search},function(data) {
		    		self.searches.push(data)
		    		 self.tableParams = new NgTableParams({count: 5}, { counts:[],data:self.searches});
		    		 Menu.update(self.searches);
		    		 $rootScope.refreshMenu={change:true};
		        });
		    	
		    });
	  };
	 
  }
  function ModalCtrl($scope, $uibModalInstance){
	  $scope.search='';
	  $scope.ok = function () {
	    $uibModalInstance.close($scope.search);
	  };

	  $scope.cancel = function () {
	    $uibModalInstance.dismiss('cancel');
	  };
  }
  function MenuCtrl($rootScope,Searches,Menu){
	  var self=this;
	  self.current=0;
	  $rootScope.refreshMenu={change:false};
	  Searches.query(function(data) {
		  Menu.update(data);
    	  self.searches = Menu.list;
      });
	  
	  this.isSet = function(item) {
          return self.current === item;
      };
      this.setItem = function(item) {
    	  self.current= item;
      };
      $rootScope.$watch('refreshMenu', function(newValue, oldValue) {
    	  if (newValue === oldValue) {
    	      return;
    	    }
    	  self.searches = Menu.list;
    	});
  }
  function StatsCtrl($scope,Analytics, $routeParams){
	  var self=this;
	  self.bestSellers={};
	  self.histogramSales={};
	  self.usersQuantity={};
	  self.quantityXCategory={};
	  Analytics.quantityXCategory.get({id: $routeParams.itemId},function(data) {
		  self.quantityXCategory = getPieChar(data);
      });
	  Analytics.bestSellers.query({id: $routeParams.itemId},function(data) {
		  self.bestSellers = data;
      });
	  Analytics.histogramSales.get({id: $routeParams.itemId},function(data) {
		  self.histogramSales = getColumnChar(data);
      });
	  Analytics.usersQuantity.get({id: $routeParams.itemId},function(data) {
		  self.usersQuantity = getSolidGaugeChar(data);
          $('#usersQuantity').highcharts(self.usersQuantity);
      });
  }
  
  angular.module('ml.controllers')
  .controller('SearchesCtrl',SearchesCtrl)
  .controller('MenuCtrl',MenuCtrl)
  .controller('StatsCtrl',StatsCtrl)
  .controller('ModalCtrl',ModalCtrl);


})();
