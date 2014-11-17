angular.module('home', [ 'ui.router', 'controllers', 'directives', 'filters', 'services' ]).config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('/index');
	$stateProvider.state('index', {
		url : '/index',
		views : {
			'' : {
				templateUrl : 'home/default',
				controller : 'homeController'
			},
			'my-exam@index' : {
				templateUrl : 'home/my-exam'
			},
			'system-message@index' : {
				templateUrl : 'home/system-message'
			},
			'apply-producer@index' : {
				templateUrl : 'home/apply-producer'
			},
			'apply-auditor@index' : {
				templateUrl : 'home/apply-auditor'
			},
			'produce@index' : {
				templateUrl : 'home/produce'
			},
			'audit@index' : {
				templateUrl : 'home/audit'
			},
			'account@index' : {
				templateUrl : 'home/account'
			}
		}
	});
});