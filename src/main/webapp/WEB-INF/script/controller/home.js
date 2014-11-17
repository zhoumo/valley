var controller = angular.module('controllers', [ 'services', 'filters', 'ngGrid' ]);
controller.controller('homeController', [ '$rootScope', 'userService', function($rootScope, userService) {
	userService.getAuthority().success(function(res) {
		$rootScope.user = {
			realName : res.realName,
			userName : res.userName,
			user : true
		};
	});
} ]);