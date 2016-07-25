var angularObj = angular
		.module('galaktikaRoot', [ 'ngRoute' ])
		.config(
				function($routeProvider, $httpProvider) {
					$routeProvider.when('/', {
						templateUrl : 'templates/home.html',
						controller : 'home',
						controllerAs : 'controller'
					}).when('/fleet-list', {
						templateUrl : 'templates/fleet-list.html',
						controller : 'fleetList',
						controllerAs : 'controller'
					}).when('/edit-fleet', {
						templateUrl : 'templates/edit-fleet.html',
						controller : 'editFleet',
						controllerAs : 'controller'
					}).when('/ship-list', {
						templateUrl : 'templates/ship-list.html',
						controller : 'shipList',
						controllerAs : 'controller'
					}).when('/ship-edit', {
						templateUrl : 'templates/ship-edit.html',
						controller : 'shipEdit',
						controllerAs : 'controller'
					}).otherwise('/');

					$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
				});

angularObj.controller('home', function($http, $scope) {
	var self = this;
	console.log("home controller called" );
})
.controller('navigation',
function($rootScope, $http, $location) {
	var self = this;
//	console.log ( "path="+$location.path()+"  hash="+$location.hash());
	self.getMenuClass=function(menuItem) {
		if ( $location.path() == menuItem
				|| '/edit-fleet'==$location.path() && menuItem=='/fleet-list'
				)
			return "active";
		else 
			return "";
	}
});
