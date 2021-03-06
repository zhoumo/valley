<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form safe-mode="true" name="paperForm" action="savePaper.do" method="post" novalidate>
	<input type="hidden" name="selectJobId" value="{{selectJobId}}">
	<input type="hidden" name="singleSelections" value="{{singleSelections}}" />
	<input type="hidden" name="multipleSelections" value="{{multipleSelections}}" />
	<input type="hidden" name="essayQuestions" value="{{essayQuestions}}" />
	<div class="modal fade" id="singleSelectionModal" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog" style="width: 900px">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">编辑单选题</h4>
				</div>
				<div class="modal-body">
					<label>题目：</label>
					<div class="ueditor" ng-model="singleSelection" style="width: 835px; height: 350px"></div>
					<label>答案：</label>
					<div class="paper-choice-answers"></div>
					<label>难度：</label>
					<select id="singleSelectionDifficulty" difficulty></select>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" ng-click="addQuestion('single')" ng-disabled="!validate">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="clearQuestion()">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="multipleSelectionModal" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog" style="width: 900px">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">编辑多选题</h4>
				</div>
				<div class="modal-body">
					<label>题目：</label>
					<div class="ueditor" ng-model="multipleSelection" style="width: 835px; height: 350px"></div>
					<label>答案：</label>
					<div class="paper-choice-answers"></div>
					<label>难度：</label>
					<select id="multipleSelectionDifficulty" difficulty></select>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" ng-click="addQuestion('multiple')" ng-disabled="!validate">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="clearQuestion()">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="essayQuestionModal" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog" style="width: 900px">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">编辑问答题</h4>
				</div>
				<div class="modal-body">
					<label>题目：</label>
					<div class="ueditor" ng-model="essayQuestion" style="width: 835px; height: 200px"></div>
					<label>答案：</label>
					<div class="ueditor" ng-model="essayQuestionAnswer" style="width: 835px; height: 200px"></div>
					<label>难度：</label>
					<select id="essayQuestionDifficulty" difficulty></select>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" ng-click="addQuestion('essay')" ng-disabled="!validate">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="clearQuestion()">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<ol class="breadcrumb">
		<li>
			<a href="#" onclick="window.onbeforeunload = null">首页</a>
		</li>
		<li>
			<a href="#/paper/list?type=create" onclick="window.onbeforeunload = null">试卷列表</a>
		</li>
		<li class="active">当前位置</li>
	</ol>
	<div class="paper-title">
		新增笔试题：
		<label ng-bind="selectJobName"></label>
		<input name="id" value="{{id}}" type="hidden" />
	</div>
	<hr class="paper-line" />
	<div class="paper-title">
		试卷名称：
		<input required="required" type="text" name="name" ng-model="paperName" />
	</div>
	<div class="paper-title">
		<button class="btn btn-primary" data-toggle="modal" data-target="#singleSelectionModal">添加单选题</button>
		<button class="btn btn-primary" data-toggle="modal" data-target="#multipleSelectionModal">添加多选题</button>
		<button class="btn btn-primary" data-toggle="modal" data-target="#essayQuestionModal">添加问答题</button>
		<button class="btn btn-primary" type="submit" onclick="window.onbeforeunload = null" ng-disabled="paperForm.$invalid">提交试卷</button>
	</div>
	<div class="paper-title">
		<input id="hideSingleSelection" type="checkbox" ng-click="hideSingleSelections=!hideSingleSelections" />
		<label for="hideSingleSelection">隐藏单选题</label>
		<input id="hideMultipleSelection" type="checkbox" ng-click="hideMultipleSelections=!hideMultipleSelections" />
		<label for="hideMultipleSelection">隐藏多选题</label>
		<input id="hideEssayQuestion" type="checkbox" ng-click="hideEssayQuestions=!hideEssayQuestions" />
		<label for="hideEssayQuestion">隐藏问答题</label>
	</div>
	<hr class="paper-line" />
	<div ng-show="!hideSingleSelections">
		<div class="paper-title">
			<b>单选题（共{{singleSelections|getQuestions|size}}题）</b>
		</div>
		<div ng-repeat="(id, text) in singleSelections|getQuestions">
			<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
				<div ng-bind-html="text|showQuestion:$index"></div>
				<div ng-bind-html="singleSelections|showAnswer:id"></div>
				<div ng-bind-html="singleSelections|showDifficulty:id"></div>
				<a class="paper-question-button" data-toggle="modal" data-target="#singleSelectionModal" ng-click="editQuestion('single',id)">编辑</a>
				<a class="paper-question-button" ng-click="deleteQuestion(id)">删除</a>
			</div>
		</div>
	</div>
	<div ng-show="!hideMultipleSelections">
		<div class="paper-title">
			<b>多选题（共{{multipleSelections|getQuestions|size}}题）</b>
		</div>
		<div ng-repeat="(id, text) in multipleSelections|getQuestions">
			<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
				<div ng-bind-html="text|showQuestion:$index"></div>
				<div ng-bind-html="multipleSelections|showAnswer:id"></div>
				<div ng-bind-html="multipleSelections|showDifficulty:id"></div>
				<a class="paper-question-button" data-toggle="modal" data-target="#multipleSelectionModal" ng-click="editQuestion('multiple',id)">编辑</a>
				<a class="paper-question-button" ng-click="deleteQuestion(id)">删除</a>
			</div>
		</div>
	</div>
	<div ng-show="!hideEssayQuestions">
		<div class="paper-title">
			<b>问答题（共{{essayQuestions|getQuestions|size}}题）</b>
		</div>
		<div ng-repeat="(id, text) in essayQuestions|getQuestions">
			<div ng-class="{'paper-question-even':$even,'paper-question-odd':$odd}">
				<div ng-bind-html="text|showQuestion:$index"></div>
				<div ng-bind-html="essayQuestions|showAnswer:id"></div>
				<div ng-bind-html="essayQuestions|showDifficulty:id"></div>
				<a class="paper-question-button" data-toggle="modal" data-target="#essayQuestionModal" ng-click="editQuestion('essay',id)">编辑</a>
				<a class="paper-question-button" ng-click="deleteQuestion(id)">删除</a>
			</div>
		</div>
	</div>
</form>