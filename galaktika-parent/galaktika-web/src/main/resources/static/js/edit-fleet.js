angularObj.controller('fleetEditController', function($scope, $http, $location, $routeParams) {
	console.log("fleetEditController called id="+$routeParams.id);
	var self = this;
	self.id=0;
	if ( $routeParams.id != null )
		self.id=$routeParams.id;
	self.postResponse={};
	self.showStoreFleet= ($routeParams.id == 0 || $routeParams.id == null );
	
	$http.get('/fleet', {params:{id:self.id}}).then(function(response) {
		console.log ("fleetEditController.http.get responsas");
		self.fleet = response.data;
	});
	
	self.storeFleet = function () {
		console.log ( "storeFleet called" );
		$http.post (
				"/storeFleet", self.fleet
				).then(function(response) {
					self.postResponse=response.data; 
					self.fleet.fleetId=response.data;
				});
	}
});