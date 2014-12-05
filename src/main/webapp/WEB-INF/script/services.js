var services = angular.module('services', []);
services.service('userService', [ '$http', function($http) {
	return {
		getAuthority : function() {
			return $http({
				method : 'post',
				url : 'getAuthority.do'
			});
		},
		getUserList : function(pageNo, pageSize) {
			return $http({
				method : 'post',
				url : 'getUserList.do?pageNo=' + pageNo + '&pageSize=' + pageSize
			});
		}
	};
} ]);
services.service('jobService', [ '$http', function($http) {
	return {
		getJobList : function() {
			return $http({
				method : 'post',
				url : 'getJobList.do'
			});
		},
		getJobTree : function() {
			return $http({
				method : 'post',
				url : 'getJobTree.do'
			});
		}
	};
} ]);
services.service('paperService', [ '$http', function($http) {
	return {
		getPaperList : function(type, pageNo, pageSize) {
			return $http({
				method : 'post',
				url : 'getPaperList.do?pageNo=' + pageNo + '&pageSize=' + pageSize + '&type=' + type
			});
		},
		getPaper : function(id) {
			return $http({
				method : 'post',
				url : 'getPaper.do?id=' + id
			});
		},
		startExam : function(id) {
			return $http({
				method : 'post',
				url : 'startExam.do?paperId=' + id
			});
		},
		timer : function(id) {
			return $http({
				method : 'post',
				url : 'timer.do?paperId=' + id
			});
		}
	};
} ]);