var controller = angular.module('controllers', [ 'ngGrid', 'ngSanitize', 'ngUeditor', 'services', 'filters' ]);
controller.controller('homeController', [ '$scope', '$rootScope', '$location', 'userService', 'jobService', function($scope, $rootScope, $location, userService, jobService) {
	userService.getAuthority().success(function(res) {
		$rootScope.user = {
			loginName : res.loginName,
			realName : res.realName,
			hasAppliedCreator : res.hasAppliedCreator,
			hasAppliedAuditor : res.hasAppliedAuditor,
			user : true
		};
	});
	jobService.getJobList().success(function(res) {
		$scope.jobList = res;
		$scope.job = res[0];
	});
	$scope.selectJob = function() {
		var jobId = $('#jobSelect').val();
		if (jobId == '?') {
			alert('请选择一个职位！');
		} else {
			$rootScope.selectJobId = jobId;
			$rootScope.selectJobName = $('#jobSelect option:selected').text();
			$location.path('create-paper');
		}
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
controller.controller('createPaperController', [ '$scope', '$rootScope', '$location', function($scope, $rootScope, $location) {
	if ($rootScope.selectJobId == null) {
		var index = $location.path().indexOf('#/');
		$location.path($location.path().substring(0, index));
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
controller.controller('examController', [ '$scope', '$routeParams', '$interval', 'paperService', function($scope, $routeParams, $interval, paperService) {
	paperService.getPaper($routeParams.id).success(function(res) {
		$scope.paper = res;
		$interval(function() {
			if ($scope.paper.time != 0) {
				$scope.paper.time = $scope.paper.time - 1;
			} else {
				alert("答题时间到！");
				window.onbeforeunload = null;
				window.close();
			}
		}, 1000);
		paperService.timer($routeParams.id).success(function(res) {
			$scope.paper.time = res;
		});
	});
	$scope.submitPaper = function() {
		$('[type=radio]:checked').each(function() {
			$('[name^=' + $(this).attr('name') + '_]').val($(this).val());
		});
		var anwsers = new Object();
		$('[type=checkbox]:checked').each(function() {
			var name = $(this).attr('name');
			if (!anwsers[name]) {
				var array = new Array();
				array.push($(this).val());
				anwsers[name] = array;
			} else {
				anwsers[name].push($(this).val());
			}
		});
		for ( var name in anwsers) {
			$('[name^=' + name + '_]').val(anwsers[name]);
		}
	};
} ]);
controller.controller('auditPaperController', [ '$scope', 'paperService', function($scope, paperService) {
} ]);
controller.controller('paperListController', [ '$scope', '$location', 'paperService', function($scope, $location, paperService) {
	$scope.type = $location.search().type;
	$scope.startAnswer = function(id) {
		if (confirm("确定开始答题吗?")) {
			paperService.startExam(id).success(function(res) {
				if (res) {
					window.open('#/exam/' + id, 'newwindow', 'toolbar=no,menubar=no,scrollbars=yes,location=no,status=no');
					location.reload();
				} else {
					alert('考试未能成功开始！');
				}
			});
		}
	};
	$scope.continueAnswer = function(id) {
		window.open('#/exam/' + id, 'newwindow', 'toolbar=no,menubar=no,scrollbars=yes,location=no,status=no');
	};
	paperService.getPaperList($scope.type, 1, 10).success(function(res) {
		$scope.paperList = res.result;
	});
} ]);