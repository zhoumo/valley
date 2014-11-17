var filters = angular.module('filters', []);
filters.filter('userInfo', function() {
	return function(user) {
		return user.realName + '<' + user.userName + '>';
	};
});
filters.filter('adminInfo', function() {
	return function(user) {
		return '当前用户：' + user.realName;
	};
});
filters.filter("userType", function() {
	return function(type) {
		switch (type) {
		case 1:
			return 'IT从业者';
		case 2:
			return 'HR';
		case 3:
			return '猎头顾问';
		default:
			return '管理员';
		}
	};
});