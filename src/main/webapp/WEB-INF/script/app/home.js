angular.module('home', [ 'ngAnimate', 'ngRoute', 'controllers', 'directives' ]).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'home/index'
	}).when('/papers', {
		templateUrl : 'home/papers',
		controller : 'paperListController'
	}).when('/create', {
		templateUrl : 'home/create',
		controller : 'createPaperController'
	}).when('/audit', {
		templateUrl : 'home/audit',
		controller : 'auditPaperController'
	}).when('/exam/:id', {
		templateUrl : 'home/exam',
		controller : 'examController'
	}).when('/account', {
		templateUrl : 'home/account'
	});
});