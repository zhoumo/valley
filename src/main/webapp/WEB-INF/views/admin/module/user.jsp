<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-user-panel">
	<div class="form-group">
		<select class="form-control" data-ng-model="type" data-ng-options="type.name for type in userTypes track by type.id" ng-change="changeType(type)"></select>
		<button class="btn btn-primary" type="button" data-toggle="modal" data-target="#userModal">添加用户</button>
	</div>
	<div ng-grid="gridOptions" style="width: 100%; height: 400px;"></div>
	<div class="modal fade" id="userModal" role="dialog" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">用户管理</h4>
				</div>
				<form name="userForm" class="form-horizontal" action="saveUser.do" method="post" novalidate>
					<div class="modal-body">
						<input type="hidden" name="id" ng-model="userId" value="{{userId}}" />
						<div class="form-group">
							<label class="col-sm-2">登录名</label>
							<div class="col-sm-6">
								<input name="loginName" type="text" class="form-control" placeholder="手机号/邮箱" ng-model="loginName" ng-pattern="/^[0-9]{11}|([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/" required="required" autocomplete="off" />
							</div>
							<div class="error" ng-if="userForm.loginName.$invalid">未填写或非法登录名</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2">密 码</label>
							<div class="col-sm-6">
								<input name="password" type="password" class="form-control" placeholder="6-12位的密码" ng-model="password" required="required" ng-minlength="6" ng-maxlength="12" autocomplete="off" />
							</div>
							<div class="error" ng-if="userForm.password.$invalid">密码格式错误</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2">姓 名</label>
							<div class="col-sm-6">
								<input name="realName" type="text" class="form-control" placeholder="请输入您的真实姓名" ng-model="realName" required="required" autocomplete="off" />
							</div>
							<div class="error" ng-if="userForm.realName.$invalid">未填写</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2">用户角色</label>
							<div class="col-sm-6">
								<select class="form-control" name="type">
									<option value="1" selected="selected">管理员</option>
									<option value="2">IT从业者</option>
									<option value="3">HR</option>
									<option value="4">猎头顾问</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2">用户状态</label>
							<div class="col-sm-6">
								<select class="form-control" name="enabled">
									<option value="0">禁用</option>
									<option value="1" selected="selected">启用</option>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-primary" type="submit" ng-disabled="userForm.$invalid">保存</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>