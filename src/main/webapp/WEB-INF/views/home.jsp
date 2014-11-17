<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html ng-app="home">
	<head>
		<title>秀才谷</title>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		<jsp:include page="../../JSP-INF/script.jsp"></jsp:include>
		<script src="script/app/home.js"></script>
		<script src="script/controller/home.js"></script>
		<link href="style/app-home.css" type="text/css" rel="stylesheet" media="screen" />
	</head>
	<body>
		<jsp:include page="../../JSP-INF/header.jsp"></jsp:include>
		<div class="home">
			<div ng-view></div>
		</div>
		<jsp:include page="../../JSP-INF/footer.jsp"></jsp:include>
	</body>
</html>