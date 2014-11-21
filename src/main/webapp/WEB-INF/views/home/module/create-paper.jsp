<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form safe-mode="true">
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
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" ng-click="addQuestion('single')">保存</button>
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
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" ng-click="addQuestion('multiple')">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="clearQuestion()">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="essayQuestionModal" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog" style="width: 900px">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">编辑单（多）选题</h4>
				</div>
				<div class="modal-body">
					<label>题目：</label>
					<div class="ueditor" ng-model="essayQuestion" style="width: 835px; height: 200px"></div>
					<label>答案：</label>
					<div class="ueditor" ng-model="essayQuestionAnswer" style="width: 835px; height: 200px"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal" ng-click="addQuestion('essay')">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="clearQuestion()">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="paper-title">
		新增笔试题：
		<label ng-bind="selectJobName"></label>
	</div>
	<hr class="paper-line" />
	<div class="paper-title">
		题库名称：
		<input required="required" type="text" />
	</div>
	<div class="paper-title">
		<button class="btn btn-primary" data-toggle="modal" data-target="#singleSelectionModal">添加单选题</button>
		<button class="btn btn-primary" data-toggle="modal" data-target="#multipleSelectionModal">添加多选题</button>
		<button class="btn btn-primary" data-toggle="modal" data-target="#essayQuestionModal">添加问答题</button>
		<button class="btn btn-primary" type="submit">提交题库</button>
	</div>
	<div class="paper-title">已录入0题，其中单选0题，多选0题，问答0题。</div>
	<div class="paper-title">
		<input id="hideSingleSelection" type="checkbox" />
		<label for="hideSingleSelection">隐藏单选题</label>
		<input id="hideMultipleSelection" type="checkbox" />
		<label for="hideMultipleSelection">隐藏多选题</label>
		<input id="hideEssayQuestion" type="checkbox" />
		<label for="hideEssayQuestion">隐藏问答题</label>
	</div>
	<hr class="paper-line" />
	<div class="paper-single-selection"></div>
	<div class="paper-multiple-selection"></div>
	<div class="paper-essay-question"></div>
</form>