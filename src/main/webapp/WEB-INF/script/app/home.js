angular.module('home', [ 'ngRoute', 'controllers', 'directives', 'filters', 'services' ]).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'home/index'
	}).when('/apply-auditor', {
		templateUrl : 'home/apply-auditor'
	}).when('/apply-producer', {
		templateUrl : 'home/apply-producer'
	}).when('/audited-paper-list', {
		templateUrl : 'home/audited-paper-list'
	}).when('/create-paper', {
		templateUrl : 'home/create-paper'
	}).when('/edit-user-info', {
		templateUrl : 'home/edit-user-info'
	}).when('/my-paper-list', {
		templateUrl : 'home/my-paper-list'
	});
});