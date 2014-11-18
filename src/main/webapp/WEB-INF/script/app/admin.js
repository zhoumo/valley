angular.module('admin', [ 'ui.router', 'controllers', 'directives', 'filters', 'services' ]).config(function($stateProvider, $urlRouterProvider) {
	$stateProvider.state('index', {
		url : '/index',
		views : {
			'' : {
				templateUrl : 'admin/index',
			},
			'job@index' : {
				templateUrl : 'admin/job'
			},
			'user@index' : {
				templateUrl : 'admin/user'
			}
		}
	});
	$urlRouterProvider.otherwise('/index');
});