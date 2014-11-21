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
	$scope.questions = new Array();
	$scope.$watch('singleSelection', function(value) {
		var dom = '';
		var sections = (value == null ? [] : value.split('<p>'));
		for ( var section in sections) {
			var line = sections[section].trim();
			var option = /\[\[[a-zA-Z]\]\]/g.exec(line);
			if (option != null) {
				option = option.toString().replace('[[', '').replace(']]', '').trim();
				dom += '<input type="radio" id="' + option + '" name="radio" />' + option;
			}
		}
		$('.paper-choice-answers').html(dom);
	}, true);
	$scope.$watch('multipleSelection', function(value) {
		var dom = '';
		var sections = (value == null ? [] : value.split('<p>'));
		for ( var section in sections) {
			var line = sections[section].trim();
			var option = /\[\[[a-zA-Z]\]\]/g.exec(line);
			if (option != null) {
				option = option.toString().replace('[[', '').replace(']]', '').trim();
				dom += '<input type="checkbox" id="' + option + '" />' + option;
			}
		}
		$('.paper-choice-answers').html(dom);
	}, true);
	$scope.clearQuestion = function() {
		$scope.singleSelection = null;
		$scope.multipleSelection = null;
		$scope.essayQuestion = null;
		$scope.essayQuestionAnswer = null;
	};
	$scope.addQuestion = function(type) {
		if (type == 'single') {
			$('.paper-single-selection').append($($scope.singleSelection));
		} else if (type == 'multiple') {
			$('.paper-single-selection').append($($scope.multipleSelection));
		} else if (type == 'essay') {
			$('.paper-single-selection').append($($scope.essayQuestion));
		}
		$scope.clearQuestion();
	};
} ]);