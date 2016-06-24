angularObj.controller('fleetShipsController', function($scope, $http, $location, $routeParams) {
	var self = this;
	self.fleetId=0;
	if ( $routeParams.fleetId != null )
		self.fleetId=$routeParams.fleetId;
});