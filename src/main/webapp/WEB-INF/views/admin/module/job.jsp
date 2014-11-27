<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="jobTree" class="col-sm-4 admin-job-tree"></div>
<div class="col-sm-8 admin-job-panel" style="border-left: 0px">
	<form name="jobForm" class="register-form form-horizontal" action="saveJob.do" method="post" novalidate>
		<div class="form-group">
			<label class="col-sm-2 control-label">职位名称</label>
			<div class="col-sm-6">
				<input name="name" type="text" class="form-control" ng-model="jobName" required="required" />
			</div>
			<div class="error" ng-if="jobForm.name.$invalid">必填</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">所属分类</label>
			<div class="col-sm-6">
				<select class="form-control" name="parentId" data-ng-options="job.name for job in jobList track by job.id" data-ng-model="jobParent">
					<option selected="selected"></option>
				</select>
			</div>
		</div>
		<input type="hidden" name="id" ng-model="jobId" value="{{jobId}}" />
		<button class="btn btn-primary" type="submit" ng-disabled="!jobId">保存</button>
		<button class="btn btn-primary" type="button" ng-disabled="!jobId" ng-click="deleteJob()">删除</button>
		<button class="btn btn-primary" type="button" data-toggle="modal" data-target="#jobModal" ng-click="clearJob()">添加职位</button>
	</form>
</div>
<div class="modal fade" id="jobModal" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">职位管理</h4>
			</div>
			<form name="jobForm" class="register-form form-horizontal" action="saveJob.do" method="post" novalidate>
				<div class="modal-body">
					<div class="form-group">
						<label class="col-sm-2 control-label">职位名称</label>
						<div class="col-sm-6">
							<input name="name" type="text" class="form-control" ng-model="jobName" required="required" />
						</div>
						<div class="error" ng-if="jobForm.name.$invalid">必填</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">所属分类</label>
						<div class="col-sm-6">
							<select class="form-control" name="parentId" data-ng-options="job.name for job in jobList track by job.id" data-ng-model="jobParent">
								<option selected="selected"></option>
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" type="submit" ng-disabled="jobForm.$invalid">保存</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="clearJob()">关闭</button>
				</div>
			</form>
		</div>
	</div>
</div>