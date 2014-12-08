var filters = angular.module('filters', []);
filters.filter('size', function() {
	return function(object) {
		if (object instanceof Object) {
			return Object.keys(object).length;
		} else if (object instanceof Array) {
			return object.length;
		} else {
			return 0;
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
	return function(object, type) {
		if (object == null) {
			return;
		}
		if (type == null) {
			var questions = new Object();
			for ( var field in object) {
				if (field.indexOf('Q') == 0) {
					questions[field] = object[field];
				}
			}
			return questions;
		} else {
			var questions = new Array();
			for ( var index = 0; index < object.length; index++) {
				var question = object[index];
				if (question.type == type) {
					questions.push(question);
				}
			}
			return questions;
		}
	};
});
filters.filter('showQuestion', [ '$sce', function($sce) {
	function checkHtml(text, checkable) {
		if (checkable) {
			return $sce.trustAsHtml(text);
		} else {
			return text;
		}
	}
	return function(text, index, checkable, type) {
		if (type == null) {
			text = text.trim().replace(/\[\[/gi, '').replace(/\]\]/gi, '. ');
		} else {
			var regexp = new RegExp(/(\[\[)(.{1})(\]\])/g);
			var result = null;
			while ((result = regexp.exec(text.trim())) != null) {
				text = text.trim().replace(result[0], '<input name="' + type + index + '" type="' + type + '" value="' + result[2] + '" /> ' + result[2] + '. ');
			}
		}
		if (text.indexOf('<p>') == 0) {
			return checkHtml('<p>' + (index + 1) + '. ' + text.substring(3), checkable);
		}
		return checkHtml((index + 1) + '. ' + text, checkable);
	};
} ]);
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
		jobList = jobList || [];
		var thirdLevelJobList = new Array();
		for ( var index = 0; index < jobList.length; index++) {
			if (jobList[index].level + 1 == 3) {
				thirdLevelJobList.push(jobList[index]);
			}
		}
		return thirdLevelJobList;
	};
});