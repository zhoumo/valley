<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="login">
	<head>
		<title>登录</title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<jsp:include page="WEB-INF/views/common/script.jsp"></jsp:include>
		<script src="script/app/login.js"></script>
		<script src="script/controller/login.js"></script>
		<link href="style/app-login.css" type="text/css" rel="stylesheet" media="screen" />
	</head>
	<body ng-controller="loginController">
		<jsp:include page="WEB-INF/views/common/header.jsp"></jsp:include>
		<div class="login slide-left" ng-view></div>
		<jsp:include page="WEB-INF/views/common/footer.jsp"></jsp:include>
	</body>
</html>