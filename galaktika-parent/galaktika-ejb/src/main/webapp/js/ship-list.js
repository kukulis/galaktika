angularObj.controller('shipList', function($http, $routeParams) {
	console.log("shipList controller called");
	var self = this;
	self.fleetId = $routeParams.fleetId;

	self.shipGroups = [];
	self.fleet = {};

	self.reloadShips = function() {
		$http.get('rest/ships/' + self.fleetId + '/getall', {}).then(
				function(response) {
					self.shipGroups = response.data;
				});
	}

	self.reloadShips();

	$http.get('rest/fleets/getone', {
		params : {
			fleetId : self.fleetId
		}
	}).then(function(response) {
		self.fleet = response.data;
	});

	self.deleteShipGroup = function(shipId) {
		$http.delete("rest/ships/" + self.fleetId + "/delete/"+shipId, {
		}).then(function(response) {
			self.reloadShips();
		});
	}

});