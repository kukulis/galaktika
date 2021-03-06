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
							
							self.responseData={};

							
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
							
							self.fleetFilter={
									name: "",
									showDeleted: false,
									showAllUsers: false,
									sortId: "none",
									sortName: "none"
							}
							
							self.sortTypes=["none","asc","desc"];
							
							self.reloadFleets=function () {
								console.log("reloadFleets called" );
								var fromRecord = self.currentPage * self.pageSize;
								$http.get('/fleets', {params:{
									from:fromRecord, 
									amount:self.pageSize,
									name:self.fleetFilter.name,
									showDeleted: self.fleetFilter.showDeleted,
									showAllUsers: self.fleetFilter.showAllUsers,
									sortId: self.fleetFilter.sortId,
									sortName: self.fleetFilter.sortName
									}}).then(function(response) {
									console.log ("fleetsController.http.get responsas");
									self.fleetsResult = response.data;
									self.fleetsTotalAmount = response.data.totalAmount;
									// self.responseData=response.data;
									
									// calculate pages
									self.pages=self.fleetsPages ($scope.showPagesCount); 
								});	
							}
							
							self.filterFleets= function () {
								console.log ( "filter fields - name:"+self.fleetFilter.name
										+"  showDeleted:"+self.fleetFilter.showDeleted
										+"  showAllUsers:"+self.fleetFilter.showAllUsers
										+"  sortId:"+self.fleetFilter.sortId
										+"  sortName:"+self.fleetFilter.sortName
										);
								
								self.reloadFleets();
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
							
							self.deleteFleet=function(pFleetId) {
								console.log("fleetsController.deleteFleet called, pFleetId="+pFleetId );
								$http.delete('/deleteFleet', {params:{fleetId:pFleetId}}).then( function(response) {
									self.responseData = response.data;
									console.log ("fleetsController.http.delete responsas "+response.data);
									if ( response.data )
										self.reloadFleets();
								},
								function (response) {
									self.showErrorMessage(response.data.message);
								}
								);
								return false;
							}
							
							self.test=function() {
								console.log("fleets controller test called");
								
								$http.get('/testerror').then(
								function(response) {
									console.log("success response" );
									self.responseData = response.data;
								},
								function (response) {
									console.log("error response" );
									if ( response.data ) {
										// var data = response.data;
										// console.log(data);
										// angular.copy ( data, self.responseData );
//										self.responseData=response.data;
										self.showErrorMessage(response.data.message);
										
										// console.log(data);
								    } 
									else {
										self.showErrorMessage("unknown error");
									}
									console.log("response.status="+ response.status);
									$('#warningBlock').show(); 

								}
								);	
							}
							
							self.testLodash=function() {
//								function square(n) {
//								        return n * n;
//								}
//								var squares=_.map([4, 8], square);
//								console.log ( squares );
								
								var output = document.querySelector("#messagesDiv");
								
								var messageElement = document.createElement("div");
								messageElement.setAttribute( "class","alert alert-warning");
								
								var closeLink = document.createElement("a");
								closeLink.setAttribute ( "class", "close" );
								closeLink.setAttribute ( "data-dismiss", "alert" );
								closeLink.setAttribute ( "aria-label", "close" );
								closeLink.appendChild ( document.createTextNode("X") );
								closeLink.setAttribute ( "onclick", "removeThisMessageBlock.call(this,event);" );
								messageElement.appendChild ( closeLink );
								
								messageElement.appendChild(document.createTextNode("some message"));
								output.appendChild(messageElement);
							}
							
							
							// TODO move to service ?
							self.showErrorMessage = function ( message ) {
								var output = document.querySelector("#messagesDiv");
								
								var messageElement = document.createElement("div");
								messageElement.setAttribute( "class","alert alert-warning");
								
								var closeLink = document.createElement("a");
								closeLink.setAttribute ( "class", "close" );
								closeLink.setAttribute ( "data-dismiss", "alert" );
								closeLink.setAttribute ( "aria-label", "close" );
								closeLink.appendChild ( document.createTextNode("X") );
								closeLink.setAttribute ( "onclick", "removeThisMessageBlock.call(this,event);" );
								messageElement.appendChild ( closeLink );
								
								messageElement.appendChild(document.createTextNode(message));
								output.appendChild(messageElement);
							}
							
							
							

				});
						