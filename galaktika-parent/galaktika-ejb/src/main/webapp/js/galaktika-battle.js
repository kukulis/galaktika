var angularObj = angular
		.module('galaktikaBattle', [ 'ngRoute' ])
		.config(
				function($routeProvider, $httpProvider) {
					$routeProvider.when('/', {
						templateUrl : 'templates/battle-home.html',
						controller : 'battleHome',
						controllerAs : 'controller'
					}).when('/home', {
						templateUrl : 'templates/battle-home.html',
						controller : 'battleHome',
						controllerAs : 'controller'
					}).otherwise('/');

					$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
				});

angularObj.controller('battleHome', function($http, $window, $location, $routeParams ) {
	var self = this;
	self.goToRoot = function() {
		var absUrl = $location.absUrl();
		var urlRoot = absUrl.substring(0, absUrl.indexOf("battle.html"));
		$window.location.href = urlRoot;
	}
	

	
	self.loadFleet=function(pFleetId, fleetCallback) {
		var returnFleet = null;
		$http.get('rest/fleets/getone', {
			params : {
				fleetId : pFleetId
			}
		}).then(function(response) {
			// console.log ("fleetEdit Controller http.get responsas");
			fleetCallback(response.data);
		});
	}

	self.aFleetId=$routeParams.aFleetId;
	self.bFleetId=$routeParams.bFleetId;
	
	self.loadFleet(self.aFleetId, function(fleet){ self.aFleet=fleet; } );
	self.loadFleet(self.bFleetId, function(fleet){ self.bFleet=fleet; } );
	
	self.maxRounds=100;
	
	self.battleResponse={rounds:null};
	self.doBattle=function(){
		console.log("doBattle called");
		$http.get('rest/battle/do', {
			params : {
				aFleetId : self.aFleetId,
				bFleetId : self.bFleetId,
				maxRounds: self.maxRounds
			}
		}).then(function(response) {
			self.battleResponse=response.data;
		});
	}
	
});
