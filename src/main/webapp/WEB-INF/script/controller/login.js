var controller = angular.module('controllers', [ 'services', 'filters', 'ngGrid' ]);
controller.controller('loginController', function($scope) {
	$scope.error = location.search.indexOf('error=true') > 0;
});