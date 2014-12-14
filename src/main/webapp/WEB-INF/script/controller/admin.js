var controller = angular.module('controllers', [ 'ngGrid', 'services', 'filters' ]);
controller.controller('adminController', [ '$scope', '$rootScope', '$location', 'userService', function($scope, $rootScope, $location, userService) {
	$scope.actived = $location.search().active;
	$scope.activing = function(active) {
		$scope.actived = active;
		$location.search('active=' + active);
	};
	userService.getAuthority().success(function(res) {
		$rootScope.user = {
			loginName : res.loginName,
			realName : res.realName,
			admin : true
		};
	});
} ]);
controller.controller('jobController', [ '$scope', 'jobService', function($scope, jobService) {
	jobService.getJobList().success(function(res) {
		$scope.jobList = res;
	});
	jobService.getJobTree().success(function(res) {
		$('#jobTree').treeview({
			nodeIcon : 'glyphicon',
			data : res,
			onNodeSelected : function(event, node) {
				$scope.$broadcast('selectJob', node);
			}
		});
	});
	$scope.$on('selectJob', function(event, node) {
		$scope.$apply(function(scope) {
			scope.jobId = node.id;
			scope.jobName = node.text;
			scope.jobParent = null;
			for (var i = 0; i < scope.jobList.length; i++) {
				if (scope.jobList[i].id == node.parent) {
					$scope.jobParent = scope.jobList[i];
				}
			}
		});
	});
	$scope.deleteJob = function() {
		if (window.confirm('确定删除' + $scope.jobName + '吗？')) {
			location.href = 'deleteJob.do?id=' + $scope.jobId;
		}
	};
	$scope.clearJob = function() {
		$scope.jobId = null;
		$scope.jobName = null;
		$scope.jobParent = null;
	};
} ]);
controller.controller('userController', [ '$scope', 'userService', function($scope, userService) {
	$scope.userList = [];
	userService.setGridPaging($scope, {
		data : 'userList',
		columnDefs : [ {
			field : 'id',
			displayName : 'ID',
			width : 30
		}, {
			field : 'loginName',
			displayName : '用户名',
			width : 180
		}, {
			field : 'realName',
			displayName : '真实姓名'
		}, {
			field : 'type',
			displayName : '用户类型',
			cellTemplate : '<div">{{COL_FIELD|userType}}</div>'
		}, {
			displayName : '申请出题'
		}, {
			displayName : '申请审核'
		}, {
			field : 'enabled',
			displayName : '用户状态',
			cellTemplate : '<div">{{COL_FIELD|userEnabled}}</div>'
		}, {
			field : 'updateTime',
			displayName : '更新时间',
			width : 150,
			cellTemplate : '<div>{{COL_FIELD|date:"yyyy-MM-dd hh:mm:ss"}}</div>'
		}, {
			displayName : '操作',
			cellTemplate : '<a ng-click="updateUser({user:row.entity})">修改</a>&nbsp;&nbsp;<a ng-click="deleteUser({user:row.entity})">删除</a>'
		} ]
	}, function() {
		userService.getUserList($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize).success(function(res) {
			$scope.userList = res.result;
			$scope.totalServerItems = res.totalCount;
		});
	});
	$scope.updateUser = function(entity) {
		$scope.userId = entity.user.id;
		$scope.loginName = entity.user.loginName;
		$scope.realName = entity.user.realName;
		$('#userModal').modal('show');
	};
	$scope.deleteUser = function(entity) {
		if (window.confirm('确定删除' + entity.user.loginName + '吗？')) {
			location.href = 'deleteUser.do?id=' + entity.user.id;
		}
	};
} ]);
controller.controller('paperController', [ '$scope', 'paperService', function($scope, paperService) {
	$scope.paperList = [];
	paperService.setGridPaging($scope, {
		data : 'paperList',
		columnDefs : [ {
			field : 'id',
			displayName : 'ID',
			width : 30
		}, {
			field : 'name',
			displayName : '试卷名',
			width : 100,
			cellTemplate : '<a ng-click="showPaper(row.entity.id)">{{COL_FIELD}}</a>'
		}, {
			field : 'job.name',
			displayName : '所属职位',
			width : 100
		}, {
			field : 'author.realName',
			displayName : '作者',
			width : 55
		}, {
			field : 'createTime',
			displayName : '创建时间',
			width : 100,
			cellTemplate : '<div>{{COL_FIELD|date:"yyyy-MM-dd"}}</div>'
		}, {
			field : 'status',
			displayName : '状态',
			width : 60,
			cellTemplate : '<div>{{COL_FIELD|paperStatus}}</div>'
		}, {
			field : 'auditor.realName',
			displayName : '审核者',
			width : 55
		}, {
			field : 'auditTime',
			displayName : '审核时间',
			width : 100,
			cellTemplate : '<div>{{COL_FIELD|date:"yyyy-MM-dd"}}</div>'
		}, {
			field : 'questions',
			displayName : '总题数',
			width : 55,
			cellTemplate : '<div>{{COL_FIELD|size}}</div>'
		}, {
			field : 'questions',
			displayName : '单选题',
			width : 55,
			cellTemplate : '<div>{{COL_FIELD|getQuestions:0|size}}</div>'
		}, {
			field : 'questions',
			displayName : '多选题',
			width : 55,
			cellTemplate : '<div>{{COL_FIELD|getQuestions:1|size}}</div>'
		}, {
			field : 'questions',
			displayName : '问答题',
			width : 55,
			cellTemplate : '<div>{{COL_FIELD|getQuestions:2|size}}</div>'
		}, {
			displayName : '操作',
			cellTemplate : '<a ng-click="deletePaper({paper:row.entity})">删除</a>&nbsp;&nbsp;<a ng-show="row.entity.status != 1" ng-click="reviewPaper({paper:row.entity})">重审</a>'
		} ]
	}, function() {
		paperService.getPaperList("all", $scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize).success(function(res) {
			$scope.paperList = res.result;
			$scope.totalServerItems = res.totalCount;
		});
	});
	$scope.deletePaper = function(entity) {
		if (window.confirm('确定删除' + entity.paper.name + '吗？')) {
			location.href = 'deletePaper.do?id=' + entity.paper.id;
		}
	};
	$scope.reviewPaper = function(entity) {
		if (window.confirm('确定重审' + entity.paper.name + '吗？')) {
			location.href = 'reviewPaper.do?id=' + entity.paper.id;
		}
	};
	$scope.showPaper = function(id) {
		paperService.getPaper(id).success(function(res) {
			$scope.paper = res;
			$('#showPaperDialog').modal('show');
		});
	};
} ]);