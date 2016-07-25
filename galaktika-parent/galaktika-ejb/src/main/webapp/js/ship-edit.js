angularObj.controller('shipEdit', function($http, $routeParams, $location ) {
	// console.log("shipEdit controller called");
	var self = this;

	self.fleetId = $routeParams.fleetId;
	self.shipId = 0;
	if ($routeParams.shipId != null)
		self.shipId = $routeParams.shipId;

	self.shipGroup = {};
	$http.get("rest/ships/" + self.fleetId + "/getone", {
		params : {
			shipId : self.shipId
		}
	}).then(function(response) {
		self.shipGroup = response.data;
	});

	self.saveShipGroup = function() {
		console.log("saveShipGroup called");
		if (self.shipId == 0)
			self.createShipGroup();
		else
			self.updateShipGroup();
	}

	self.createShipGroup = function() {
		$http.put("rest/ships/" + self.fleetId + "/create", self.shipGroup)
			.then(function(response) {
				console.log("response.data: " + response.data);
				$location.path("/ship-list").replace();
			});
	}

	self.updateShipGroup = function() {
		$http.post("rest/ships/" + self.fleetId + "/update", self.shipGroup)
		.then(function(response) {
			console.log("response.data: " + response.data);
			$location.path("/ship-list").replace();
		});
	}

});