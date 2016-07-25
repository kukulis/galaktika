angularObj.controller('editFleet', function($http, $scope, $routeParams,
		$location) {
	var self = this;
	self.id = 0;
	if ($routeParams.id != null)
		self.id = $routeParams.id;

	$http.get('rest/fleets/getone', {
		params : {
			fleetId : self.id
		}
	}).then(function(response) {
		// console.log ("fleetEdit Controller http.get responsas");
		self.fleet = response.data;
	});

	var updateFleet = function() {
		fleetObject = {
			fleetId : self.fleet.fleetId,
			name : self.fleet.name
		};
		$http.post("rest/fleets/updateFleet", fleetObject).then(
				function(response) {
					console.log("response.data: " + response.data);
					$location.path("/fleet-list").replace();
				});
	}
	
	var createFleet=function() {
//		console.log("createFleet called" );
		$http.put("rest/fleets/createFleet?fleetName="+self.fleet.name ).then(
				function(response) {
//					console.log("response.data: " + response.data);
					$location.path("/fleet-list").replace();
				});
	}
	
	self.storeFleet = function() {
		// console.log ( "storeFleet called" );
		if (self.id > 0) {
			updateFleet();
		} else {
			createFleet();
		}
	}
	
	self.submitButtonName=function() {
		if ( self.id>0 )
			return "Update";
		else
			return "Create";
	}
	
	self.goToList = function() {
		$location.path("/fleet-list").replace();
	}

});