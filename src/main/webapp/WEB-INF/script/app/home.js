angular.module('home', [ 'ngAnimate', 'ngRoute', 'controllers', 'directives' ]).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'home/index'
	}).when('/paper/list', {
		templateUrl : 'home/paper/list',
		controller : 'paperListController'
	}).when('/paper/create', {
		templateUrl : 'home/paper/create',
		controller : 'paperCreateController'
	}).when('/paper/audit/:id', {
		templateUrl : 'home/paper/audit',
		controller : 'paperAuditController'
	}).when('/paper/show/:id', {
		templateUrl : 'home/paper/show',
		controller : 'paperShowController'
	}).when('/paper/exam/:id', {
		templateUrl : 'home/paper/exam',
		controller : 'paperExamController'
	});
});