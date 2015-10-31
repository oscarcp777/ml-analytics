modules = {
    core {
		resource url:'css/bootstrap/bootstrap.css'
		resource url:'js/jquery.js' , disposition: 'head'
		resource url:'js/bootstrap/bootstrap.js'
		resource url:'css/font-awesome.css' 
	}
	highcharts {
		dependsOn 'core'
		resource url:'js/chart/highcharts.js', exclude:'minify'
		resource url:'js/chart/highcharts-more.js', exclude:'minify'
		resource url:'js/chart/solid-gauge.js', exclude:'minify'
		resource url:'js/chart/exporting.js', exclude:'minify'
		resource url:'js/chart/export-csv.js', exclude:'minify'
	}
	angular{
		dependsOn 'core'
		resource url:'js/angular/angular.min.js' , disposition: 'head'
		resource url:'js/angular/angular-route.min.js' , disposition: 'head'
		resource url:'js/angular/angular-resource.min.js' , disposition: 'head'
		resource url:'js/angular/angular-sanitize.min.js'
		resource url:'js/angular/highcharts-ng.min.js'
		resource url:'js/angular/app/charFactory.js'
		resource url:'js/angular/angular-locale_es-ar.js'
		resource url:'js/angular/ui-bootstrap-tpls-0.14.3.min.js'
		resource url:'js/angular/app/services.js' , disposition: 'head'
		resource url:'js/angular/app/controllers.js' , disposition: 'head'
		resource url:'js/angular/app/app.js' , disposition: 'head'
		resource url:'js/angular/xeditable.min.js'
		resource url:'js/angular/angular-table.min.js' 
		resource url:'js/angular/ng-table.min.js'
		resource url:'css/ng-table.min.css'
		resource url:'css/app.css'
		resource url:'css/xeditable.css'
	}
}