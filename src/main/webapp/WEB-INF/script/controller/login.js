var controller = angular.module('controllers', [ 'services', 'filters' ]);
controller.controller('loginController', function($scope) {
	var href = location.href;
	var index = href.indexOf('#/');
	if (index > 0 && index + 2 != href.length && href.indexOf('jsessionid') > 0) {
		location.href = href.substring(0, index);
	}
	$scope.error = location.search.indexOf('error=true') > 0;
});