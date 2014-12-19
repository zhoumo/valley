<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="height: 640px" safe-mode="false">
	<p>欢迎来到秀才谷个人中心</p>
	<panel title="我的笔试题" type="exam" color="#01caa0">
		<div class="panel-top-button">
			<a href="#/paper/list?type=exam">全部试卷</a>
		</div>
		<div class="panel-content"></div>
	</panel>
	<panel title="系统消息" type="message" color="#01caa0">
		<div class="panel-top-button">
			<a href="readAllMessage.do">全部清除</a>
		</div>
		<ul class="panel-list" ng-repeat="message in messages">
			<li>
				<label style="width: 220px">{{message.title}}</label>
				<label>{{message.createTime|date:"yyyy-MM-dd hh:mm:ss"}}</label>
			</li>
		</ul>
	</panel>
	<panel title="申请成为题库生产者，贡献你的智慧！" type="apply-creator" color="#00aeef" ng-show="!user.approveCreator">
		<div class="panel-content">
			<a data-toggle="modal" data-target="#applyModal" ng-click="setApplyType('生产者')">现在申请</a>
		</div>
	</panel>
	<panel title="申请成为题库审核人，为题库把关！" type="apply-auditor" color="#00aeef" ng-show="!user.approveAuditor">
		<div class="panel-content">
			<a data-toggle="modal" data-target="#applyModal" ng-click="setApplyType('审核者')">现在申请</a>
		</div>
	</panel>
	<panel title="我要出题" type="create" color="#6f8ba9" ng-show="user.approveCreator">
		<div class="panel-top-button">
			<a href="#/paper/list?type=create">我的试卷</a>
		</div>
		<div class="panel-content">
			<select id="jobSelect" class="form-control" style="width: 200px" data-ng-options="job.name for job in jobList|thirdLevelJob track by job.id" data-ng-model="job"></select>
			<button class="btn btn-primary" type="button" ng-click="selectJob()">现在去出题</button>
		</div>
	</panel>
	<panel title="我要审题" type="audit" color="#6f8ba9" ng-show="user.approveAuditor">
		<div class="panel-top-button">
			<a href="#/paper/list?type=audited">已审试卷</a>
		</div>
		<ul class="panel-list" ng-repeat="paper in auditPapers">
			<li>
				<label style="width: 320px">{{paper.name}}</label>
				<a ng-click="auditPaper(paper.id)">审核</a>
			</li>
		</ul>
	</panel>
	<panel title="账户管理" type="account" color="#15467a">
		<div class="panel-content">
			<a data-toggle="modal" data-target="#userModal">修改个人资料</a>
		</div>
	</panel>
	<div class="modal fade" id="applyModal" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog" style="width: 860px">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">题库{{applyType}}申请</h4>
				</div>
				<form name="applyForm" class="form-horizontal" style="width: 778px" action="apply.do" method="post" novalidate ng-show="applyType=='生产者' && !user.applyCreator || applyType=='审核者' && !user.applyAuditor">
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
							<textarea name="resume" rows="15" cols="110" ng-model="resume"></textarea>
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
				<div class="modal-body" ng-show="applyType=='生产者' && user.applyCreator || applyType=='审核者' && user.applyAuditor">
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
	<div class="modal fade" id="userModal" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">修改个人资料</h4>
				</div>
				<form name="userForm" class="form-horizontal" action="updateUser.do" method="post" novalidate>
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2">姓 名</label>
							<div class="col-sm-6">
								<input name="realName" type="text" class="form-control" placeholder="请输入您的真实姓名" ng-model="user.realName" required="required" autocomplete="off" />
							</div>
							<div class="error" ng-if="userForm.realName.$invalid">未填写</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2">密 码</label>
							<div class="col-sm-6">
								<input name="password" type="password" class="form-control" placeholder="6-12位的密码" ng-model="password" required="required" ng-minlength="6" ng-maxlength="12" autocomplete="off" />
							</div>
							<div class="error" ng-if="userForm.password.$invalid">密码格式错误</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary" type="submit" ng-disabled="userForm.$invalid">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>