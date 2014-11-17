angular.module('login', [ 'ngRoute', 'controllers', 'directives', 'filters', 'services' ]).config(function($routeProvider) {
	$routeProvider.when('/', {
		templateUrl : 'views/login/default.html',
		controller : 'loginController'
	}).when('/register', {
		templateUrl : 'views/login/register.html'
	}).when('/about', {
		templateUrl : 'views/login/about.html'
	}).when('/cooperate', {
		templateUrl : 'views/login/cooperate.html'
	});
});