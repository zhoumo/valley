<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-paper-panel" ng-controller="paperController">
	<div class="form-group">
		<label>职位名称：</label>
		<input type="text" />
		<label>试卷作者：</label>
		<input type="text" />
		<div class="space"></div>
		<button class="btn btn-primary" type="button">查询试卷</button>
		<button class="btn btn-primary" type="button">统计报表</button>
	</div>
	<div ng-grid="gridOptions" style="width: 100%; height: 400px;"></div>
	<div id="showPaperDialog" class="modal fade" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog" style="width: 800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">试卷详情</h4>
				</div>
				<div class="modal-body" style="text-align: left">
					<div class="paper-title">{{paper.name}} - 笔试题</div>
					<div class="paper-title">
						题目总数： {{(paper.questions|getQuestions:0|size)+(paper.questions|getQuestions:1|size)+(paper.questions|getQuestions:2|size)}}
						<span class="space"></span>
						单选题： {{paper.questions|getQuestions:0|size}}
						<span class="space"></span>
						多选题： {{paper.questions|getQuestions:1|size}}
						<span class="space"></span>
						问答题： {{paper.questions|getQuestions:2|size}}
					</div>
					<div class="paper-title">最后更新时间：{{paper.updateTime|date:"yyyy-MM-dd hh:mm:ss"}}</div>
					<div class="paper-title">
						<input id="hideSingleSelection" type="checkbox" ng-click="hideSingleSelections=!hideSingleSelections" />
						<label for="hideSingleSelection">隐藏单选题</label>
						<span class="space"></span>
						<input id="hideMultipleSelection" type="checkbox" ng-click="hideMultipleSelections=!hideMultipleSelections" />
						<label for="hideMultipleSelection">隐藏多选题</label>
						<span class="space"></span>
						<input id="hideEssayQuestion" type="checkbox" ng-click="hideEssayQuestions=!hideEssayQuestions" />
						<label for="hideEssayQuestion">隐藏问答题</label>
					</div>
					<div ng-show="!hideSingleSelections">
						<div class="paper-title">
							<b>单选题（共{{paper.questions|getQuestions:0|size}}题）</b>
						</div>
						<div ng-repeat="singleSelection in paper.questions|getQuestions:0">
							<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
								<div ng-bind-html="singleSelection.question|showQuestion:$index:'radio'"></div>
								<div ng-bind-html="singleSelection.answer|showAnswer"></div>
							</div>
							<audit-status paper="{{paper}}" question="{{singleSelection}}" />
						</div>
					</div>
					<div ng-show="!hideMultipleSelections">
						<div class="paper-title">
							<b>多选题（共{{paper.questions|getQuestions:1|size}}题）</b>
						</div>
						<div ng-repeat="multipleSelection in paper.questions|getQuestions:1">
							<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
								<div ng-bind-html="multipleSelection.question|showQuestion:$index:'checkbox'"></div>
								<div ng-bind-html="multipleSelection.answer|showAnswer"></div>
							</div>
							<audit-status paper="{{paper}}" question="{{multipleSelection}}" />
						</div>
					</div>
					<div ng-show="!hideEssayQuestions">
						<div class="paper-title">
							<b>问答题（共{{paper.questions|getQuestions:2|size}}题）</b>
						</div>
						<div ng-repeat="essayQuestion in paper.questions|getQuestions:2">
							<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
								<div ng-bind-html="essayQuestion.question|showQuestion:$index"></div>
								<div ng-bind-html="essayQuestion.answer|showAnswer"></div>
							</div>
							<audit-status paper="{{paper}}" question="{{essayQuestion}}" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</div>