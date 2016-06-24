var angularObj = angular
		.module('hello', [ 'ngRoute' ])
		.config(
				function($routeProvider, $httpProvider) {

					$routeProvider.when('/', {
						templateUrl : 'home.html',
						controller : 'home',
						controllerAs : 'controller'
					}).when('/login', {
						templateUrl : 'login.html',
						controller : 'navigation',
						controllerAs : 'controller'
					}).when('/sim-fleet-list', {
						templateUrl : '/sim/sim-fleet-list.html',
						controller : 'fleetsController',
						controllerAs : 'controller'
					}).when('/sim-edit-fleet', {
						templateUrl : '/sim/sim-edit-fleet.html',
						controller : 'fleetEditController',
						controllerAs : 'controller'
					}).when('/sim-ships', {
						templateUrl : '/sim/sim-ships.html',
						controller : 'fleetShipsController',
						controllerAs : 'controller'
					}).otherwise('/');

					$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

				});
angularObj
		.controller('home', function($http) {
			var self = this;
			$http.get('/resource/').then(function(response) {
				self.greeting = response.data;
			})
		})

		.controller(
				'navigation',

				function($rootScope, $http, $location) {

					var self = this

					var authenticate = function(credentials, callback) {

						var headers = credentials ? {
							authorization : "Basic "
									+ btoa(credentials.username + ":"
											+ credentials.password)
						} : {};

						$http.get('vartotojas', {
							headers : headers
						}).then(function(response) {
							if (response.data.name) {
								$rootScope.authenticated = true;
								$rootScope.username=response.data.name;
							} else {
								$rootScope.authenticated = false;
							}
							callback && callback();
						}, function() {
							$rootScope.authenticated = false;
							callback && callback();
						});

					} // autenticate end

					authenticate();
					self.credentials = {};
					self.login = function() {
						authenticate(self.credentials, function() {
							if ($rootScope.authenticated) {
								$location.path("/");
								self.error = false;
							} else {
								$location.path("/login");
								self.error = true;
							}
						});
					}; // login end
					
					self.logout = function() {
						  $http.post('logout', {}).finally(function() {
						    $rootScope.authenticated = false;
						    $location.path("/");
						  });
						}
					
				});

	