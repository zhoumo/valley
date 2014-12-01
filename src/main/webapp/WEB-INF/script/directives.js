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