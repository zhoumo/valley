var controller = angular.module('controllers', [ 'services', 'filters', 'ngGrid' ]);
controller.controller('adminController', [ '$scope', '$rootScope', 'userService', function($scope, $rootScope, userService) {
	$scope.actived = location.search.replace('?', '');
	$scope.activing = function(active) {
		$scope.actived = active;
		window.location.href = '?' + active;
	};
	userService.getAuthority().success(function(res) {
		$rootScope.user = {
			realName : res.realName,
			userName : res.userName,
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
			$scope.jobParent = null;
			for ( var i = 0; i < scope.jobList.length; i++) {
				if (scope.jobList[i].id == node.parent) {
					$scope.jobParent = scope.jobList[i];
				}
			}
		});
	});
	$scope.deleteJob = function() {
		location.href = 'deleteJob.do?id=' + $scope.jobId;
	};
	$scope.clearJob = function() {
		$scope.jobId = null;
		$scope.jobName = null;
		$scope.jobParent = null;
	};
} ]);
controller.controller('userController', [ '$scope', 'userService', function($scope, userService) {
	$scope.userList = [];
	$scope.totalServerItems = 0;
	$scope.pagingOptions = {
		pageSizes : [ 10 ],
		pageSize : 10,
		currentPage : 1
	};
	$scope.gridOptions = {
		data : 'userList',
		multiSelect : false,
		enablePaging : true,
		showFooter : true,
		i18n : "zh-cn",
		totalServerItems : 'totalServerItems',
		pagingOptions : $scope.pagingOptions,
		columnDefs : [ {
			field : 'id',
			displayName : 'ID',
			width : 50
		}, {
			field : 'userName',
			displayName : '用户名',
			width : 200
		}, {
			field : 'realName',
			displayName : '真实姓名'
		}, {
			field : 'userType',
			displayName : '用户类型',
			cellTemplate : '<div style="margin:5px">{{COL_FIELD|userType}}</div>'
		}, {
			field : 'enabled',
			displayName : '用户状态',
		}, {
			field : 'updateTime',
			displayName : '更新时间',
			width : 150,
			cellTemplate : '<div style="margin:5px">{{COL_FIELD|date:"yyyy-MM-dd hh:mm:ss"}}</div>'
		}, {
			displayName : '操作',
			cellTemplate : '<div style="margin:5px"><a ng-click="updateUser({user:row.entity})">修改</a> | <a ng-click="deleteUser({user:row.entity})">删除</a></div>'
		} ]
	};
	$scope.paging = function() {
		userService.getUserList($scope.pagingOptions.currentPage, $scope.pagingOptions.pageSize).success(function(res) {
			$scope.userList = res.result;
			$scope.totalServerItems = res.totalCount;
		});
	};
	$scope.paging();
	$scope.$watch('pagingOptions', function(newVal, oldVal) {
		if (newVal !== oldVal) {
			$scope.paging();
		}
	}, true);
	$scope.updateUser = function(entity) {
		$scope.userId = entity.user.id;
		$scope.userName = entity.user.userName;
		$scope.realName = entity.user.realName;
		$('#userModal').modal('show');
	};
	$scope.deleteUser = function(entity) {
		if (window.confirm('确定要删除' + entity.user.userName + '吗？')) {
			location.href = 'deleteUser.do?id=' + entity.user.id;
		}
	};
} ]);