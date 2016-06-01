angularObj
				.controller(
						'fleetsController',
						function($scope, $http, $location) {
							console.log ("fleetsController");
							
							this.currentPage=0;
							this.pageSize=10;
							if ( $scope.pageSize != null )
								this.pageSize=$scope.pageSize;
							this.fleetsTotalAmount=0;
							
							$scope.test="testas";
							
							var self = this;

							
							self.fleetsPages = function (showPagesCount) {
								console.log ("fleetsController.fleetsPages called fleetsTotalAmount="+self.fleetsTotalAmount+" showPagesCount="+showPagesCount  );
								var pages=[];
								var pagesCount=Math.floor( (self.fleetsTotalAmount -1 ) / self.pageSize ) + 1;
								
								// try to make the current page to be in the middle
								var pageFrom = self.currentPage - Math.floor(showPagesCount / 2);
								if ( pageFrom < 0 )
									pageFrom = 0;
								
								if ( pageFrom >= pagesCount )
									pageFrom = pagesCount - 1;
								
								var pageTo = pageFrom + showPagesCount;
								if ( pageTo >= pagesCount )
									pageTo = pagesCount - 1;
								
								// make limited pages list, depending on current page 
								for (i=pageFrom;i<=pageTo;i++ ) {
									pages[pages.length]=i;
								}
								return pages;
							}
							
							self.reloadFleets=function () {
								console.log("reloadFleets called" );
								var fromRecord = self.currentPage * self.pageSize;
								$http.get('/fleets', {params:{from:fromRecord, amount:self.pageSize}}).then(function(response) {
									console.log ("fleetsController.http.get responsas");
									self.fleetsResult = response.data;
									self.fleetsTotalAmount = response.data.totalAmount;
									
									// calculate pages
									self.pages=self.fleetsPages ($scope.showPagesCount); 
								});	
							}
							
							
							// load fleets the first time
							// self.reloadFleets();
							
							// load fleets when changing page
							self.setCurrentPage=function(page) {
								console.log("fleetsController.setCurrentPage called, page="+page );
								$scope.test="reikia keisti į puslapį "+page;
								self.currentPage=page;
								self.reloadFleets();
							}
							
							$scope.$watch("pageSize", function(){
								console.log("watch pageSize="+$scope.pageSize);
								self.pageSize=$scope.pageSize;
								self.reloadFleets();
							});
							
							self.deleteFleet=function(fleetId) {
								console.log("fleetsController.deleteFleet called, fleetId="+fleetId );
								return false;
							}

				});
						