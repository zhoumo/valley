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
filters.filter('paperStatus', function() {
	return function(status) {
		switch (status) {
		case 1:
			return '已提交';
		case 2:
			return '通过';
		case 3:
			return '未通过';
		default:
			return '已创建';
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
	return function(text, index, type) {
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
			return $sce.trustAsHtml('<p>' + (index + 1) + '. ' + text.substring(3));
		}
		return $sce.trustAsHtml((index + 1) + '. ' + text);
	};
} ]);
filters.filter('showAnswer', [ '$sce', function($sce) {
	return function(questions, id) {
		var text = (id == null ? questions : questions[id.replace('Q', 'A')]);
		if (text.indexOf('<p>') == 0) {
			return $sce.trustAsHtml('<p><b>答案：</b>' + text.substring(3));
		}
		return $sce.trustAsHtml('<b>答案：</b>' + text);
	};
} ]);
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