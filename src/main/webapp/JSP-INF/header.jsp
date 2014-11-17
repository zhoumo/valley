<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
	<div class="header-container">
		<div class="header-logo"></div>
		<div class="header-panel" ng-if="user.user">
			<div class="header-user-info" ng-bind="user|userInfo"></div>
			<div>
				<a>我的秀才</a>
				|
				<a>系统设置</a>
				|
				<a>个人资料</a>
			</div>
		</div>
		<div class="header-action" ng-if="!user">
			<a href="login.jsp#/register">注册</a>
			<a href="login.jsp">登录</a>
			<a href="login.jsp#/about">关于我们</a>
			<a href="login.jsp#/cooperate">商务合作</a>
		</div>
		<div class="header-action" ng-if="user.user">
			<a>意见反馈</a>
			<a>帮助中心</a>
			<a href="j_spring_security_logout">退出</a>
		</div>
		<div class="header-action" ng-if="user.admin">
			<div class="header-admin-info" ng-bind="user|adminInfo"></div>
			<a href="#" onclick="location.reload()">后台首页</a>
			<a href="j_spring_security_logout">注销</a>
		</div>
	</div>
	<div class="header-split"></div>
</div>