var directives = angular.module('directives', []);
directives.directive("panel", function() {
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
directives.directive("auditPanel", function() {
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