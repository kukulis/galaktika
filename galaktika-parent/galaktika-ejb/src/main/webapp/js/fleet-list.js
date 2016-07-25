angularObj.controller('fleetList', function($http) {
	var self = this;
	console.log("fleetList Controller called" );
//	$scope.selectedFleetList=true;
	
	self.reloadFleets = function() {
		$http.get('rest/fleets/getall').then(function(response) {
			self.fleets = response.data;
		});	
	}
	
	self.reloadFleets();
	
	self.deleteFleet=function(fleetId) {
//		console.log("deleteFleet called" );
		$http.delete( "rest/fleets/deleteFleet?fleetId="+fleetId).then( function(response) {
			self.reloadFleets();
		});
	}
});
