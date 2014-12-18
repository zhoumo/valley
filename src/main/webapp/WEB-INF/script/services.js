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
		}
	};
} ]);
services.service('jobService', [ 'baseService', function(baseService) {
	return {
		setGridPaging : function(scope, config, paging) {
			baseService.setGridPaging(scope, config, paging);
		},
		getJobList : function() {
			return baseService.getHttp('getJobList.do');
		},
		getJobTree : function() {
			return baseService.getHttp('getJobTree.do');
		}
	};
} ]);
services.service('paperService', [ 'baseService', function(baseService) {
	return {
		setGridPaging : function(scope, config, paging) {
			baseService.setGridPaging(scope, config, paging);
		},
		getPaperList : function(type, pageNo, pageSize) {
			return baseService.getHttp('getPaperList.do?pageNo=' + pageNo + '&pageSize=' + pageSize + '&type=' + type);
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