var controller = angular.module('controllers', []);
controller.controller('loginController', function($scope) {
	var blackList = [ 'create', 'audit', 'exam', 'papers' ];
	var arr = location.href.split('#/');
	if (arr.length > 1) {
		for ( var index in blackList) {
			if (arr[1].indexOf(blackList[index]) == 0) {
				location.href = 'login.jsp#/';
			}
		}
	}
	$scope.error = location.search.indexOf('error=true') > 0;
});