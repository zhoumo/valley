<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form name="paperForm" action="submitPaper.do" method="post" novalidate>
	<div class="paper-top">
		<div class="paper-time">剩余时间：{{time}}</div>
		<div>
			<button type="submit" class="btn btn-primary" ng-click="submitPaper()">提交试题</button>
			<button type="button" class="btn btn-default" ng-click="closePaper()">放弃试题</button>
		</div>
	</div>
	<div class="paper-title">{{paperName}} - 笔试题</div>
	<div class="paper-title">
		题目总数： {{(singleSelections|size)+(multipleSelections|size)+(essayQuestions|size)}}
		<span class="space"></span>
		单选题： {{singleSelections|size}}
		<span class="space"></span>
		多选题： {{multipleSelections|size}}
		<span class="space"></span>
		问答题： {{essayQuestions|size}}
	</div>
	<div class="paper-title">最后更新时间：{{updateTime|date:"yyyy-MM-dd hh:mm:ss"}}</div>
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
			<b>单选题（共{{singleSelections|size}}题）</b>
		</div>
		<div ng-repeat="singleSelection in singleSelections">
			<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
				<div ng-bind-html="singleSelection.question|showQuestion:$index:singleSelection.id:true:'radio'"></div>
			</div>
		</div>
	</div>
	<div ng-show="!hideMultipleSelections">
		<div class="paper-title">
			<b>多选题（共{{multipleSelections|size}}题）</b>
		</div>
		<div ng-repeat="multipleSelection in multipleSelections">
			<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
				<div ng-bind-html="multipleSelection.question|showQuestion:$index:multipleSelection.id:true:'checkbox'"></div>
			</div>
		</div>
	</div>
	<div ng-show="!hideEssayQuestions">
		<div class="paper-title">
			<b>问答题（共{{essayQuestions|size}}题）</b>
		</div>
		<div ng-repeat="essayQuestion in essayQuestions">
			<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
				<div ng-bind-html="essayQuestion.question|showQuestion:$index:essayQuestion.id"></div>
				<textarea rows="5" cols="120"></textarea>
			</div>
		</div>
	</div>
	<div class="paper-bottom">
		<button type="submit" class="btn btn-primary" ng-click="submitPaper()">提交试题</button>
		<button type="button" class="btn btn-default" ng-click="closePaper()">放弃试题</button>
	</div>
</form>