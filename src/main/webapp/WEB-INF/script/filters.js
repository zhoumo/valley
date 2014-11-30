var filters = angular.module('filters', []);
filters.filter('size', function() {
	return function(object) {
		if (object instanceof Object) {
			return Object.keys(object).length;
		} else {
			return object.length;
		}
	};
});
filters.filter('userInfo', function() {
	return function(user) {
		return user.realName + '<' + user.loginName + '>';
	};
});
filters.filter('adminInfo', function() {
	return function(user) {
		return '当前用户：' + user.realName;
	};
});
filters.filter('userType', function() {
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
filters.filter('getQuestions', function() {
	return function(object, start) {
		var questions = new Object();
		for ( var field in object) {
			if (field.indexOf('Q') == 0) {
				questions[field] = object[field];
			}
		}
		return questions;
	};
});
filters.filter('showQuestion', function() {
	return function(text, index, id) {
		text = text.trim().replace(/\[\[/gi, '').replace(/\]\]/gi, '. ');
		if (text.indexOf('<p>') == 0) {
			return '<p>' + (index + 1) + '. ' + text.substring(3);
		}
		return (index + 1) + '. ' + text;
	};
});
filters.filter('showAnswer', function() {
	return function(questions, id) {
		var text = questions[id.replace('Q', 'A')];
		if (text.indexOf('<p>') == 0) {
			return '<p><b>答案：</b>' + text.substring(3);
		}
		return '<b>答案：</b>' + text;
	};
});
filters.filter('thirdLevelJob', function() {
	return function(jobList) {
		var thirdLevelJobList = new Array();
		for ( var index = 0; index < jobList.length; index++) {
			if (jobList[index].level + 1 == 3) {
				thirdLevelJobList.push(jobList[index]);
			}
		}
		return thirdLevelJobList;
	};
});