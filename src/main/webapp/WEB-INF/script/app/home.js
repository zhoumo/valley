angular.module('home', [ 'ngRoute', 'controllers', 'directives', 'filters', 'services' ]).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'home/index'
	}).when('/create-paper', {
		templateUrl : 'home/create-paper',
		controller : 'createPaperController'
	}).when('/edit-user-info', {
		templateUrl : 'home/edit-user-info'
	}).when('/paper-list', {
		templateUrl : 'home/paper-list',
		controller : 'paperListController'
	});
});