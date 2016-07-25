angularObj.controller('fleetList', function($http, $location, $window ) {
	var self = this;
	console.log("fleetList Controller called" );
// $scope.selectedFleetList=true;
	self.fleetSelection=[];
	
	self.reloadFleets = function() {
		$http.get('rest/fleets/getall').then(function(response) {
			self.fleets = response.data;
		});	
	}
	
	self.reloadFleets();
	
	self.deleteFleet=function(fleetId) {
// console.log("deleteFleet called" );
		$http.delete( "rest/fleets/deleteFleet?fleetId="+fleetId).then( function(response) {
			self.reloadFleets();
		});
	}
	
	self.callBattle=function(){
		var absUrl=$location.absUrl();
		var urlRoot=absUrl.substring(0, absUrl.indexOf("#"));
		if ( urlRoot.endsWith("/")) {
			// console.log("it ends with '/'" );
			urlRoot=urlRoot.substring(0, urlRoot.length - 1 );
		}
		// console.log("urlRoot="+urlRoot);
		// console.log("fleetSelection="+self.fleetSelection);
		if ( self.fleetSelection.length < 2 ) {
			alert("not enough fleets selected "+self.fleetSelection.length+" (need two )" );
		}
		else {
			$window.location.href=urlRoot+"/battle.html#/?aFleetId="+self.fleetSelection[0]
			+"&bFleetId="+self.fleetSelection[1];
		}
	}
	
	self.isFleetSelected=function(fleetId) {
		return self.fleetSelection.indexOf(fleetId) > 0;
	}
	self.toggleFleetSelection=function(fleetId) {
		var idx = self.fleetSelection.indexOf(fleetId);
		if (idx > -1)
			self.fleetSelection.splice(idx,1);
		else
			self.fleetSelection.push(fleetId);
	}
});
