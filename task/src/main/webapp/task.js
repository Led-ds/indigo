//'use strict';
var taskApp = angular.module('taskApp', ['ngRoute']);

taskApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/shorten', {
        templateUrl: 'task/view/register/shorten.jsp',
        controller: 'shortenController'
    }).otherwise({redirectTo: '/shorten'});
    
}]);
