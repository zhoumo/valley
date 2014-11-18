<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="admin">
	<head>
		<title>后台管理</title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<jsp:include page="common/script.jsp"></jsp:include>
		<script src="script/app/admin.js"></script>
		<script src="script/controller/admin.js"></script>
		<link href="style/app-admin.css" type="text/css" rel="stylesheet" media="screen" />
	</head>
	<body>
		<jsp:include page="common/header.jsp"></jsp:include>
		<div ui-view></div>
		<div class="footer-always-on-bottom">
			<jsp:include page="common/footer.jsp"></jsp:include>
		</div>
	</body>
</html>