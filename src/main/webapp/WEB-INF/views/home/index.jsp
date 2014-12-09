<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="height: 640px" safe-mode="false">
	<p>欢迎来到秀才谷个人中心</p>
	<panel title="我的笔试题" type="exam" color="#01caa0">
		<div class="panel-top-button">
			<a href="#/paper/list?type=exam">全部试题</a>
		</div>
		<div class="panel-content"></div>
	</panel>
	<panel title="系统消息" type="message" color="#01caa0">
		<div class="panel-top-button">
			<a>全部清除</a>
		</div>
		<div class="panel-content"></div>
	</panel>
	<panel title="申请成为题库生产者，贡献你的智慧！" type="apply-creator" color="#00aeef">
		<div class="panel-content">
			<a data-toggle="modal" data-target="#applyModal" ng-click="setApplyType('生产者')">现在申请</a>
		</div>
	</panel>
	<panel title="申请成为题库审核人，为题库把关！" type="apply-auditor" color="#00aeef">
		<div class="panel-content">
			<a data-toggle="modal" data-target="#applyModal" ng-click="setApplyType('审核者')">现在申请</a>
		</div>
	</panel>
	<panel title="我要出题" type="create" color="#6f8ba9">
		<div class="panel-top-button">
			<a href="#/paper/list?type=create">我的题库</a>
		</div>
		<div class="panel-content">
			<select id="jobSelect" class="form-control" style="width: 200px" data-ng-options="job.name for job in jobList|thirdLevelJob track by job.id" data-ng-model="job"></select>
			<button class="btn btn-primary" type="button" ng-click="selectJob()">现在去出题</button>
		</div>
	</panel>
	<panel title="我要审题" type="audit" color="#6f8ba9">
	<div class="panel-top-button">
		<a href="#/paper/list?type=audit">已审题库</a>
	</div>
	</panel>
	<panel title="账户管理" type="account" color="#15467a">
	<div class="panel-content">
		<a href="#/account/edit">修改个人资料</a>
	</div>
	</panel>
	<div class="modal fade" id="applyModal" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog" style="width: 800px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">题库{{applyType}}申请</h4>
				</div>
				<form name="applyForm" class="form-horizontal" style="width: 778px" action="apply.do" method="post" novalidate ng-show="applyType=='生产者' && !user.hasAppliedCreator || applyType=='审核者' && !user.hasAppliedAuditor">
					<input name="selectedJobs" type="hidden" value="{{selectedJobs}}" />
					<input name="applyType" type="hidden" value="{{applyType}}" />
					<div class="modal-body">
						<p>申请成为题库{{applyType}}，为题库把关！</p>
						<p>
							<b>资格要求：</b>
						</p>
						<p>相关工作经验5年以上，对相关岗位和专业知识掌握牢固，有审题的兴趣和非常强烈的责任感!</p>
						<p>
							<b>价值回报：</b>
						</p>
						<p>将成为数十万相关职位应聘者的偶像，并有高性价比的现金回报！</p>
						<p>
							<b>个人资历：</b>
						</p>
						<p>
							<textarea name="resume" rows="15" cols="100" ng-model="resume"></textarea>
						</p>
						<p>选择出题的职位：</p>
						<div class="form-group">
							<div class="col-sm-6">
								<select id="jobList" class="form-control" data-ng-options="job.name for job in jobList|thirdLevelJob track by job.id" data-ng-model="job"></select>
							</div>
							<div class="col-sm-2">
								<button type="button" class="btn btn-primary" ng-click="addJob()">添加</button>
							</div>
						</div>
						<div class="form-group" ng-repeat="job in selectedJobs">
							<div class="col-sm-4">{{job.text}}</div>
							<span class="glyphicon glyphicon-remove" ng-click="removeJob(job.id)"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-primary" ng-disabled="!resume || !selectedJobs">发送请求</button>
					</div>
				</form>
				<div class="modal-body" ng-show="applyType=='生产者' && user.hasAppliedCreator || applyType=='审核者' && user.hasAppliedAuditor">
					<p>申请成为题库{{applyType}}，为题库把关！</p>
					<p>
						<b>资格要求：</b>
					</p>
					<p>相关工作经验5年以上，对相关岗位和专业知识掌握牢固，有审题的兴趣和非常强烈的责任感!</p>
					<p>
						<b>价值回报：</b>
					</p>
					<p>将成为数十万相关职位应聘者的偶像，并有高性价比的现金回报！</p>
					<p style="color: red">您已经提交过该申请，系统正在审核过程中，请耐心等待！</p>
				</div>
			</div>
		</div>
	</div>
</div>