var controller = angular.module('controllers', [ 'services', 'filters', 'ngGrid', 'ng.ueditor' ]);
controller.controller('homeController', [ '$scope', '$rootScope', 'userService', 'jobService', function($scope, $rootScope, userService, jobService) {
	userService.getAuthority().success(function(res) {
		$rootScope.user = {
			realName : res.realName,
			userName : res.userName,
			user : true
		};
	});
	jobService.getJobList().success(function(res) {
		$scope.jobList = res;
		$scope.job = res[0];
	});
	$scope.selectJob = function() {
		$rootScope.selectJobId = $('#jobSelect').val();
		$rootScope.selectJobName = $('#jobSelect option:selected').text();
		location.href = '#/create-paper';
	};
} ]);
controller.controller('createPaperController', [ '$scope', '$rootScope', function($scope, $rootScope) {
	if ($rootScope.selectJobId == null) {
		location.href = '#/';
	}
} ]);