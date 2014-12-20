var services = angular.module('services', []);
services.service('baseService', [ '$http', function($http) {
	return {
		getHttp : function(url) {
			return $http({
				method : 'get',
				url : url
			});
		},
		setGridPaging : function(scope, config, paging) {
			scope.pagingOptions = {
				pageSizes : [ 10 ],
				pageSize : 10,
				currentPage : 1
			};
			scope.totalServerItems = 0;
			angular.extend(config, {
				enableColumnResize : true,
				multiSelect : false,
				enablePaging : true,
				showFooter : true,
				i18n : 'zh-cn',
				totalServerItems : 'totalServerItems',
				pagingOptions : scope.pagingOptions,
			});
			scope.gridOptions = config;
			scope.$watch('pagingOptions', function(newVal, oldVal) {
				if (newVal !== oldVal) {
					scope.paging();
				}
			}, true);
			scope.paging = paging;
			scope.paging();
		}
	};
} ]);
services.service('userService', [ 'baseService', function(baseService) {
	return {
		setGridPaging : function(scope, config, paging) {
			baseService.setGridPaging(scope, config, paging);
		},
		getAuthority : function() {
			return baseService.getHttp('getAuthority.do');
		},
		getUserList : function(pageNo, pageSize, type) {
			return baseService.getHttp('getUserList.do?pageNo=' + pageNo + '&pageSize=' + pageSize + '&type=' + type);
		},
		getMessage : function(pageNo, pageSize) {
			return baseService.getHttp('getMessage.do?pageNo=' + pageNo + '&pageSize=' + pageSize);
		}
	};
} ]);
services.service('jobService', [ 'baseService', function(baseService) {
	return {
		setGridPaging : function(scope, config, paging) {
			baseService.setGridPaging(scope, config, paging);
		},
		getJobTree : function() {
			return baseService.getHttp('getJobTree.do');
		},
		getJobList : function() {
			return baseService.getHttp('getJobList.do');
		},
		getApplyJobList : function() {
			return baseService.getHttp('getApplyJobList.do');
		}
	};
} ]);
services.service('paperService', [ 'baseService', function(baseService) {
	return {
		setGridPaging : function(scope, config, paging) {
			baseService.setGridPaging(scope, config, paging);
		},
		getPaperList : function(pageNo, pageSize, type, job, author) {
			return baseService.getHttp('getPaperList.do?pageNo=' + pageNo + '&pageSize=' + pageSize + '&type=' + type + '&job=' + job + '&author=' + author);
		},
		getPaper : function(id) {
			return baseService.getHttp('getPaper.do?id=' + id);
		},
		startExam : function(id) {
			return baseService.getHttp('startExam.do?paperId=' + id);
		},
		timer : function(id) {
			return baseService.getHttp('timer.do?paperId=' + id);
		}
	};
} ]);