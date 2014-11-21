var controller = angular.module('controllers', [ 'services', 'filters' ]);
controller.controller('loginController', function($scope) {
	var blackList = [ 'create-paper' ];
	var arr = location.href.split('#/');
	if (arr.length > 1) {
		for ( var index in blackList) {
			if (blackList[index] == arr[1]) {
				location.href = 'login.jsp#/';
			}
		}
	}
	$scope.error = location.search.indexOf('error=true') > 0;
});