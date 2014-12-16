<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form action="renewPaper.do" method="post" novalidate>
	<ol class="breadcrumb">
		<li>
			<a href="#">首页</a>
		</li>
		<li>
			<a href="#/paper/list?type=create">试卷列表</a>
		</li>
		<li class="active">当前位置</li>
	</ol>
	<input type="hidden" name="id" value="{{paper.id}}" />
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
				<div ng-bind-html="singleSelection.difficulty|showDifficulty"></div>
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
				<div ng-bind-html="multipleSelection.difficulty|showDifficulty"></div>
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
				<div ng-bind-html="essayQuestion.difficulty|showDifficulty"></div>
			</div>
			<audit-status paper="{{paper}}" question="{{essayQuestion}}" />
		</div>
	</div>
	<div class="paper-bottom" ng-show="paper.status == 2">
		<button type="submit" class="btn btn-primary">重新编辑试卷</button>
	</div>
</form>