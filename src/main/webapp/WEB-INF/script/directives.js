var directives = angular.module('directives', []);
directives.directive('panel', function() {
	return {
		restrict : 'E',
		template : '<div class="panel"><div class="panel-title"></div><span ng-transclude></span></div>',
		transclude : true,
		link : function(scope, element, attrs) {
			var panel = element.children().eq(0);
			var title = panel.children().eq(0);
			panel.css('border-left', '5px solid ' + attrs.color);
			panel.css('background', 'url(images/panel/' + attrs.type + '.png)');
			panel.css('background-position', 'bottom right');
			panel.css('background-repeat', 'no-repeat');
			title.css('color', attrs.color);
			title.text(attrs.title);
		}
	};
});
directives.directive('auditPanel', function() {
	return {
		restrict : 'E',
		template : '<button type="button" class="btn btn-success">通过</button><div class="space"></div><button type="button" class="btn btn-danger">不通过</button>',
		link : function(scope, element, attrs) {
			var input = $('<input type="hidden" name="audit' + attrs.id + '" />');
			var label = $('<label></label>');
			label.css('float', 'left');
			var auditYes = element.children().eq(0);
			auditYes.click(function() {
				input.val(true);
				auditYes.css('display', 'none');
				auditNo.css('display', 'none');
				label.text('审核通过');
				label.css('color', 'green');
				scope.accumulate();
			});
			var auditNo = element.children().eq(2);
			auditNo.click(function() {
				var reason = window.prompt('请输入不通过的原因：', '');
				input.val(reason == '' ? '未知' : reason);
				auditYes.css('display', 'none');
				auditNo.css('display', 'none');
				label.text('审核不通过，原因：' + reason);
				label.css('color', 'red');
				scope.accumulate();
			});
			element.append(label);
			element.append(input);
		}
	};
});
directives.directive('auditStatus', function() {
	return {
		restrict : 'E',
		link : function(scope, element, attrs) {
			var paper = JSON.parse(attrs.paper);
			var question = JSON.parse(attrs.question);
			if (paper.status == 2 || paper.status == 3) {
				if (question.audit) {
					element.append('<label style="color:green">已通过审核</label>');
				} else {
					element.append('<label style="color:red">未通过审核，原因是：' + question.comment + '</label>');
				}
			} else {
				element.append('<label style="color:#808080">未审核</label>');
			}
		}
	};
});
directives.directive('approve', function() {
	return {
		restrict : 'E',
		link : function(scope, element, attrs) {
			var value = JSON.parse(attrs.value);
			if (!value || value.length == 0) {
				element.append('未申请');
				return;
			}
			if (value[0].approved == null) {
				var message = '【申请职位】\n';
				for ( var index = 0; index < value.length; index++) {
					message += value[index].job.name + '\n';
				}
				message += '\n';
				message += '【个人资历】\n' + value[0].resume;
				message += '\n\n';
				var button = $('<div style="color:red">待处理</div>');
				button.click(function() {
					scope.approve(attrs.type, attrs.user, message);
				});
				element.append(button);
			} else if (value[0].approved) {
				element.append('<div style="color:green">已通过</div>');
			} else {
				element.append('<div style="color:red">未通过</div>');
			}
		}
	};
});
directives.directive('unique', function($http) {
	return {
		restrict : 'A',
		require : 'ngModel',
		scope : {
			unique : '='
		},
		link : function(scope, element, attrs) {
			element.bind('keyup change blur', function() {
				$http({
					method : 'get',
					url : attrs.name + 'Unique.do?' + attrs.name + '=' + element.val()
				}).success(function(res) {
					scope.unique = (res == 'true');
				});
			});
		}
	};
});
directives.directive('safeMode', function() {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			if (attrs.safeMode == 'true') {
				window.onbeforeunload = function() {
					return '';
				};
			} else {
				window.onbeforeunload = function() {
					return;
				};
			}
		}
	};
});
directives.directive('difficulty', function() {
	return {
		restrict : 'A',
		template : '<option value="0">简单</option><option selected="selected" value="1">一般</option><option value="2">困难</option>'
	};
});
