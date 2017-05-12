<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/resource/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="shortcut icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resource/img/icedCoffee.ico"
	media="screen" />
<title>测试结果 —— Java 教学评测系统</title>
</head>
<body>
	<%@include file="../common/userNavbar.jspf"%>
	<div class="container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">运行结果</div>
			<!-- Table -->
			<table class="table .table-striped">
				<tr>
					<th>系统输入</th>
					<th>程序输出</th>
					<th>正确性</th>
				</tr>
				<c:forEach var="testRecord" items="${test.testRecords}">
					<tr>
						<td>${testRecord.input}</td>
						<td>${testRecord.output}</td>
						<td>${testRecord.correct}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<%@include file="../common/footer.jspf"%>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="${pageContext.request.contextPath}/resource/js/jquery-3.1.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
</body>
</html>