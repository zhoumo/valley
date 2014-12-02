angular.module('home', [ 'ngRoute', 'controllers', 'directives', 'filters', 'services' ]).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'home/index'
	}).when('/paper-list', {
		templateUrl : 'home/paper-list',
		controller : 'paperListController'
	}).when('/create-paper', {
		templateUrl : 'home/create-paper',
		controller : 'createPaperController'
	}).when('/answer-paper/:id', {
		templateUrl : 'home/answer-paper',
		controller : 'answerPaperController'
	}).when('/audit-paper', {
		templateUrl : 'home/audit-paper',
		controller : 'auditPaperController'
	}).when('/edit-user-info', {
		templateUrl : 'home/edit-user-info'
	});
});