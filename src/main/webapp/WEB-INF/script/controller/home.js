var controller = angular.module('controllers', [ 'services', 'filters', 'ngSanitize', 'ngGrid', 'ng.ueditor' ]);
controller.controller('homeController', [ '$scope', '$rootScope', 'userService', 'jobService', function($scope, $rootScope, userService, jobService) {
	userService.getAuthority().success(function(res) {
		$rootScope.user = {
			loginName : res.loginName,
			realName : res.realName,
			hasAppliedProducer : res.hasAppliedProducer,
			hasAppliedAuditor : res.hasAppliedAuditor,
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
	$scope.selectedJobs = null;
	$scope.addJob = function() {
		var id = $('#jobList').val();
		var text = $('#jobList option:selected').text();
		if (text != '') {
			if ($scope.selectedJobs == null) {
				$scope.selectedJobs = new Object();
			}
			$scope.selectedJobs[id] = {
				id : id,
				text : text
			};
		}
	};
	$scope.removeJob = function(id) {
		delete $scope.selectedJobs[id];
		if ($.isEmptyObject($scope.selectedJobs)) {
			$scope.selectedJobs = null;
		}
	};
	$scope.setApplyType = function(type) {
		$scope.applyType = type;
	};
} ]);
controller.controller('createPaperController', [ '$scope', '$rootScope', function($scope, $rootScope) {
	if ($rootScope.selectJobId == null) {
		var index = location.href.indexOf('#/');
		location.href = location.href.substring(0, index);
	}
	$scope.singleSelections = new Object();
	$scope.multipleSelections = new Object();
	$scope.essayQuestions = new Object();
	$scope.$watch('singleSelection', function(value) {
		var dom = '';
		var sections = (value == null ? [] : value.split('<p>'));
		for ( var section in sections) {
			var line = sections[section].trim();
			var option = /\[\[[a-zA-Z]\]\]/g.exec(line);
			if (option != null) {
				option = option.toString().replace('[[', '').replace(']]', '').trim();
				dom += '<input type="radio" value="' + option + '" name="radio" />' + option;
			}
		}
		$scope.validate = (dom != '');
		$('#singleSelectionModal .paper-choice-answers').html(dom);
		$('input[type=radio]').eq(0).attr("checked", 'checked');
	}, true);
	$scope.$watch('multipleSelection', function(value) {
		var dom = '';
		var sections = (value == null ? [] : value.split('<p>'));
		for ( var section in sections) {
			var line = sections[section].trim();
			var option = /\[\[[a-zA-Z]\]\]/g.exec(line);
			if (option != null) {
				option = option.toString().replace('[[', '').replace(']]', '').trim();
				dom += '<input type="checkbox" value="' + option + '" checked="checked" />' + option;
			}
		}
		$scope.validate = (dom != '');
		$('#multipleSelectionModal .paper-choice-answers').html(dom);
	}, true);
	$scope.$watch('essayQuestion', function(value) {
		$scope.validate = (value != '' && $scope.essayQuestionAnswer != '');
	}, true);
	$scope.$watch('essayQuestionAnswer', function(value) {
		$scope.validate = (value != '' && $scope.essayQuestion != '');
	}, true);
	$scope.clearQuestion = function() {
		$scope.singleSelection = null;
		$scope.multipleSelection = null;
		$scope.essayQuestion = null;
		$scope.essayQuestionAnswer = null;
	};
	$scope.addQuestion = function(type) {
		var key = $scope.questionKey == null ? Date.now() : $scope.questionKey;
		if (type == 'single') {
			$scope.singleSelections['Q' + key] = $scope.singleSelection;
			$scope.singleSelections['A' + key] = $('input[type=radio]:checked').val();
			$('#singleSelectionModal').modal('hide');
		} else if (type == 'multiple') {
			$scope.multipleSelections['Q' + key] = $scope.multipleSelection;
			var answer = '';
			$('input[type=checkbox]:checked').each(function() {
				answer += $(this).val() + ',';
			});
			$scope.multipleSelections['A' + key] = answer;
			$('#multipleSelectionModal').modal('hide');
		} else if (type == 'essay') {
			$scope.essayQuestions['Q' + key] = $scope.essayQuestion;
			$scope.essayQuestions['A' + key] = $scope.essayQuestionAnswer;
			$('#essayQuestionModal').modal('hide');
		}
		$scope.questionKey = null;
		$scope.clearQuestion();
	};
	$scope.editQuestion = function(type, id) {
		$scope.questionKey = id.substring(1);
		if (type == 'single') {
			$scope.singleSelection = $scope.singleSelections['Q' + $scope.questionKey];
		} else if (type == 'multiple') {
			$scope.multipleSelection = $scope.multipleSelections['Q' + $scope.questionKey];
		} else if (type == 'essay') {
			$scope.essayQuestion = $scope.essayQuestions['Q' + $scope.questionKey];
			$scope.essayQuestionAnswer = $scope.essayQuestions['A' + $scope.questionKey];
		}
	};
	$scope.deleteQuestion = function(id) {
		if (confirm('确认删除?')) {
			delete $scope.singleSelections['Q' + id.substring(1)];
			delete $scope.singleSelections['A' + id.substring(1)];
			delete $scope.multipleSelections['Q' + id.substring(1)];
			delete $scope.multipleSelections['A' + id.substring(1)];
			delete $scope.essayQuestions['Q' + id.substring(1)];
			delete $scope.essayQuestions['A' + id.substring(1)];
		}
	};
} ]);