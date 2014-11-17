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
			title.css('color', attrs.color);
			title.text(attrs.title);
		}
	};
});