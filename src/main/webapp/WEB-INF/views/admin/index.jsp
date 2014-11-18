<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="admin-container" ng-controller="adminController">
	<ul id="tab" class="nav nav-tabs">
		<li ng-class="{'active': actived=='job'||!actived}">
			<a data-toggle="tab" ng-click="activing('job')">职位分类</a>
		</li>
		<li ng-class="{'active': actived=='user'}">
			<a data-toggle="tab" ng-click="activing('user')">用户管理</a>
		</li>
		<li ng-class="{'active': actived=='paper'}">
			<a data-toggle="tab" ng-click="activing('paper')">试题库</a>
		</li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane" ng-class="{'active': actived=='job'||!actived}" ng-controller="jobController">
			<jsp:include page="module/job.jsp"></jsp:include>
		</div>
		<div class="tab-pane" ng-class="{'active': actived=='user'}" ng-controller="userController">
			<jsp:include page="module/user.jsp"></jsp:include>
		</div>
		<div class="tab-pane" ng-class="{'active': actived=='paper'}" ng-controller="paperController">
			<jsp:include page="module/paper.jsp"></jsp:include>
		</div>
	</div>
</div>