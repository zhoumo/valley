<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form safe-mode="true" name="paperForm" action="finishExam.do" method="post" novalidate>
	<input name="id" type="hidden" value="{{examId}}" />
	<div class="paper-top">
		<div class="paper-time">剩余时间：{{paper.time}}</div>
	</div>
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
			</div>
			<input type="hidden" name="radio{{$index}}_{{singleSelection.id}}" />
		</div>
	</div>
	<div ng-show="!hideMultipleSelections">
		<div class="paper-title">
			<b>多选题（共{{paper.questions|getQuestions:1|size}}题）</b>
		</div>
		<div ng-repeat="multipleSelection in paper.questions|getQuestions:1">
			<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
				<div ng-bind-html="multipleSelection.question|showQuestion:$index:'checkbox'"></div>
			</div>
			<input type="hidden" name="checkbox{{$index}}_{{multipleSelection.id}}" />
		</div>
	</div>
	<div ng-show="!hideEssayQuestions">
		<div class="paper-title">
			<b>问答题（共{{paper.questions|getQuestions:2|size}}题）</b>
		</div>
		<div ng-repeat="essayQuestion in paper.questions|getQuestions:2">
			<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
				<div ng-bind-html="essayQuestion.question|showQuestion:$index"></div>
				<textarea name="textarea_{{essayQuestion.id}}" rows="5" cols="120"></textarea>
			</div>
		</div>
	</div>
	<div class="paper-bottom">
		<button type="submit" class="btn btn-primary" ng-click="finishExam()">提交试卷</button>
		<button type="button" class="btn btn-default" ng-click="endExam()">放弃考试</button>
	</div>
</form>