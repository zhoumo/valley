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
		var index = location.href.indexOf('#/');
		location.href = location.href.substring(0, index);
	}
	$scope.$watch('content', function(value) {
		var dom = '';
		var sections = (value == null ? [] : value.split('<p>'));
		for ( var section in sections) {
			var line = sections[section].trim();
			if (line.indexOf('[[') == 0 && line.indexOf(']]') > 0) {
				var option = line.substring(2).split(']]');
				dom += '<input type="checkbox" value="' + option[1].trim().replace('</p>', '').replace('<br/>', '') + '" />' + option[0];
			}
		}
		$('.paper-choice-answers').html(dom);
	}, true);
	$scope.clearQuestion = function() {
		$scope.content = null;
	};
	$scope.addQuestion = function() {
	};
} ]);