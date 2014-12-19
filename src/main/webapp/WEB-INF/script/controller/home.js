var controller = angular.module('controllers', [ 'ngGrid', 'ngSanitize', 'ngUeditor', 'services', 'filters' ]);
controller.controller('homeController', [ '$scope', '$rootScope', '$location', 'userService', 'jobService', 'paperService', function($scope, $rootScope, $location, userService, jobService, paperService) {
	userService.getAuthority().success(function(res) {
		$rootScope.user = {
			loginName : res.loginName,
			realName : res.realName,
			applyCreator : res.applyCreator,
			applyAuditor : res.applyAuditor,
			approveCreator : res.approveCreator,
			approveAuditor : res.approveAuditor,
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
			$location.path('paper/create');
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
	$scope.auditPaper = function(id) {
		$location.path('paper/audit/' + id);
	};
	paperService.getPaperList('audit', 1, 3).success(function(res) {
		$scope.auditPapers = res.result;
	});
	userService.getMessage(1, 3).success(function(res) {
		$scope.messages = res.result;
	});
} ]);
controller.controller('paperCreateController', [ '$scope', '$rootScope', '$location', 'paperService', function($scope, $rootScope, $location, paperService) {
	$scope.id = $location.search().id;
	$scope.singleSelections = new Object();
	$scope.multipleSelections = new Object();
	$scope.essayQuestions = new Object();
	if ($scope.id != null) {
		paperService.getPaper($scope.id).success(function(res) {
			$scope.paperName = res.name;
			$scope.selectJobId = res.job.id;
			$scope.selectJobName = res.job.name;
			for ( var index = 0; index < res.questions.length; index++) {
				var question = res.questions[index];
				if (question.type == 0) {
					$scope.singleSelections['Q' + question.id] = question.question;
					$scope.singleSelections['A' + question.id] = question.answer;
					$scope.singleSelections['D' + question.id] = question.difficulty;
				} else if (question.type == 1) {
					$scope.multipleSelections['Q' + question.id] = question.question;
					$scope.multipleSelections['A' + question.id] = question.answer;
					$scope.multipleSelections['D' + question.id] = question.difficulty;
				} else if (question.type == 2) {
					$scope.essayQuestions['Q' + question.id] = question.question;
					$scope.essayQuestions['A' + question.id] = question.answer;
					$scope.essayQuestions['D' + question.id] = question.difficulty;
				}
			}
		});
	}
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
			$scope.singleSelections['D' + key] = $('#singleSelectionDifficulty').val();
			$('#singleSelectionModal').modal('hide');
		} else if (type == 'multiple') {
			$scope.multipleSelections['Q' + key] = $scope.multipleSelection;
			var answer = '';
			$('input[type=checkbox]:checked').each(function() {
				answer += $(this).val() + ',';
			});
			$scope.multipleSelections['A' + key] = answer;
			$scope.multipleSelections['D' + key] = $('#multipleSelectionDifficulty').val();
			$('#multipleSelectionModal').modal('hide');
		} else if (type == 'essay') {
			$scope.essayQuestions['Q' + key] = $scope.essayQuestion;
			$scope.essayQuestions['A' + key] = $scope.essayQuestionAnswer;
			$scope.essayQuestions['D' + key] = $('#essayQuestionDifficulty').val();
			$('#essayQuestionModal').modal('hide');
		}
		$scope.questionKey = null;
		$scope.clearQuestion();
	};
	$scope.editQuestion = function(type, id) {
		$scope.questionKey = id.substring(1);
		if (type == 'single') {
			$scope.singleSelection = $scope.singleSelections['Q' + $scope.questionKey];
			$('#singleSelectionDifficulty').val($scope.singleSelections['D' + $scope.questionKey]);
		} else if (type == 'multiple') {
			$scope.multipleSelection = $scope.multipleSelections['Q' + $scope.questionKey];
			$('#multipleSelectionDifficulty').val($scope.multipleSelections['D' + $scope.questionKey]);
		} else if (type == 'essay') {
			$scope.essayQuestion = $scope.essayQuestions['Q' + $scope.questionKey];
			$scope.essayQuestionAnswer = $scope.essayQuestions['A' + $scope.questionKey];
			$('#essayQuestionDifficulty').val($scope.essayQuestions['D' + $scope.questionKey]);
		}
	};
	$scope.deleteQuestion = function(id) {
		if (confirm('确认删除?')) {
			delete $scope.singleSelections['Q' + id.substring(1)];
			delete $scope.singleSelections['A' + id.substring(1)];
			delete $scope.singleSelections['D' + id.substring(1)];
			delete $scope.multipleSelections['Q' + id.substring(1)];
			delete $scope.multipleSelections['A' + id.substring(1)];
			delete $scope.multipleSelections['D' + id.substring(1)];
			delete $scope.essayQuestions['Q' + id.substring(1)];
			delete $scope.essayQuestions['A' + id.substring(1)];
			delete $scope.essayQuestions['D' + id.substring(1)];
		}
	};
} ]);
controller.controller('paperExamController', [ '$scope', '$routeParams', '$interval', 'paperService', function($scope, $routeParams, $interval, paperService) {
	var id = $routeParams.id.split('_');
	$scope.paperId = id[0];
	$scope.examId = id[1];
	$scope.finishExam = function() {
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
	$scope.endExam = function() {
		if (confirm('确定放弃答题吗?')) {
			window.onbeforeunload = null;
			window.close();
		}
	};
	paperService.getPaper($scope.paperId).success(function(res) {
		$scope.paper = res;
		$interval(function() {
			if ($scope.paper.time != 0) {
				$scope.paper.time = $scope.paper.time - 1;
			} else {
				alert('答题时间到！');
				window.onbeforeunload = null;
				window.close();
			}
		}, 1000);
		paperService.timer($scope.paperId).success(function(res) {
			$scope.paper.time = res;
		});
	});
} ]);
controller.controller('paperAuditController', [ '$scope', '$routeParams', 'paperService', function($scope, $routeParams, paperService) {
	$scope.id = $routeParams.id;
	$scope.counter = 0;
	$scope.accumulate = function() {
		$scope.counter++;
		$scope.$apply();
	};
	paperService.getPaper($routeParams.id).success(function(res) {
		$scope.paper = res;
	});
} ]);
controller.controller('paperShowController', [ '$scope', '$routeParams', 'paperService', function($scope, $routeParams, paperService) {
	paperService.getPaper($routeParams.id).success(function(res) {
		$scope.paper = res;
	});
} ]);
controller.controller('paperListController', [ '$scope', '$location', 'paperService', function($scope, $location, paperService) {
	$scope.type = $location.search().type;
	$scope.startAnswer = function(id) {
		if (confirm('确定开始答题吗?')) {
			paperService.startExam(id).success(function(res) {
				if (!res) {
					alert('考试未能成功开始！');
				} else {
					var success = window.open('#/paper/exam/' + id + '_' + res, 'newwindow', 'toolbar=no,menubar=no,scrollbars=yes,location=no,status=no');
					if (!success) {
						alert('考试页面可能被浏览器拦截！');
					}
					location.reload();
				}
			});
		}
	};
	$scope.continueAnswer = function(paperId, examId) {
		window.open('#/paper/exam/' + paperId + '_' + examId, 'newwindow', 'toolbar=no,menubar=no,scrollbars=yes,location=no,status=no');
	};
	$scope.editPaper = function(id) {
		$location.path('paper/create').search('id=' + id);
	};
	$scope.submitPaper = function(id) {
		if (confirm('确定提交试卷吗?')) {
			location.href = 'submitPaper.do?id=' + id;
		}
	};
	$scope.showPaper = function(id) {
		$location.path('paper/show/' + id).search('');
	};
	paperService.getPaperList($scope.type, 1, 10).success(function(res) {
		$scope.paperList = res.result;
	});
} ]);