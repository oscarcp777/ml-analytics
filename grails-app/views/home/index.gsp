<!DOCTYPE html>
<html lang="es">
<head>
<meta name='layout' content='main' />
</head>

<body>
	<div ng-app="ml">
		<g:render contextPath="../search" template="navBar"></g:render>
		<div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar" ng-controller="MenuCtrl as menu">
          <ul class="nav nav-sidebar">
             <li ng-class="{ active:menu.isSet(0) }">
               <a  href="#/" ng-click="menu.setItem(0)">
             	<i class="fa fa-cogs menu-icon"></i>
             	<span>Configuración</span>
                </a> 
             </li>
             <li ng-repeat="item in menu.searches" ng-class="{ active:menu.isSet($index+1) }">
                <a ng-href="#/stats/{{item.id}}" ng-click="menu.setItem($index+1)"> 
                	<i class="fa fa-line-chart menu-icon"></i> 
                	<span>{{item.name}}</span>
                </a>
             </li>
           
          </ul>

        </div>

             <div ng-view></div>
			
			
        </div>
      </div>
	</div>
</body>
</html>