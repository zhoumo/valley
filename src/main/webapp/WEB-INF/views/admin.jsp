<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="admin">
	<head>
		<title>后台管理</title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<jsp:include page="../../JSP-INF/script.jsp"></jsp:include>
		<script src="script/app/admin.js"></script>
		<script src="script/controller/admin.js"></script>
		<link href="style/app-admin.css" type="text/css" rel="stylesheet" media="screen" />
	</head>
	<body>
		<jsp:include page="../../JSP-INF/header.jsp"></jsp:include>
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
				<div class="tab-pane" ng-class="{'active': actived=='job'||!actived}" ng-controller="jobController"><%@ include file="admin/job.jsp"%></div>
				<div class="tab-pane" ng-class="{'active': actived=='user'}"><%@ include file="admin/user.jsp"%></div>
				<div class="tab-pane" ng-class="{'active': actived=='paper'}"><%@ include file="admin/paper.jsp"%></div>
			</div>
		</div>
		<jsp:include page="../../JSP-INF/footer.jsp"></jsp:include>
	</body>
</html>