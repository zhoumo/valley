<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form safe-mode="true">
	<div class="modal fade" id="singleSelectionModal" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">编辑单（多）选题</h4>
				</div>
				<div class="modal-body">
					<div class="ueditor" ng-model="content"></div>
					<textarea ng-model="content"></textarea>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" type="submit">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
		<button class="btn btn-primary">添加多选题</button>
		<button class="btn btn-primary">添加问答题</button>
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
</form>