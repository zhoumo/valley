angular.module('home', [ 'ngRoute', 'controllers', 'directives', 'filters', 'services' ]).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'home/index'
	}).when('/produce', {
		templateUrl : 'home/produce'
	});
});