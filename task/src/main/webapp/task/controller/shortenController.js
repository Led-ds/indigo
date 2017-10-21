taskApp.controller('shortenController', ['$scope', '$http', '$location', function($scope, $http, $location) {

   var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/task/rest/shortens";
   
   //console.log("URL "+url);
   $scope.shorten = {};
   
   $scope.getAll = function(){
   	   $http.get(url).success(function(data, status) {	   		
	   		$scope.shortens = data;
	   }).error(function(data, status, headers, config) {
	     
	   });	   
   };
   
   $scope.getAll();
   
   $scope.excluir = function(id){
	   delete(url+"/"+id).success(function(data, status) {	   		
	   		$scope.getAll(); 		   		
	   }).error(function(data, status, headers, config) {
	     
	   });
   };   
   
   $scope.saveOrUpdate = function(shorten){
   		$scope.$broadcast('show-errors-check-validity');
   		if ($scope.form.$valid) {   		
   			if(shorten != null && shorten.id > 0){
   				$http.put(url+"/"+shorten.id, shorten).success(function(data, status) {		   		
			   		cleanForm();		
			   }).error(function(data, status, headers, config) {
			     
			   });
   			}else{
   				$http.post(url, shorten).success(function(data, status) {		   
   					cleanForm();		
   				}).error(function(data, status, headers, config) {
   					
   				});		      		   				
   			}
	   		
	   		$scope.$broadcast('show-errors-reset');
   		}  		
   } 
   
   var cleanForm = function(){
   		$scope.shorten = {};
		$scope.getAll();   	
   }  

}]);