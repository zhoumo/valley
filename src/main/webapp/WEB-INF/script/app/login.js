angular.module('login', [ 'ngAnimate', 'ngRoute', 'controllers', 'directives' ]).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/login/default.html'
	}).when('/register', {
		templateUrl : 'views/login/register.html'
	}).when('/about', {
		templateUrl : 'views/login/about.html'
	}).when('/cooperate', {
		templateUrl : 'views/login/cooperate.html'
	});
});