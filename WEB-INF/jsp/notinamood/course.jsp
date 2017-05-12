<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>课程详情 —— Java 教学评测系统</title>
</head>
<body>
	<%@include file="../common/userNavbar.jspf"%>
	<div class="container">
		<div>
			<div class="panel panel-default">
				<!-- Default panel contents -->
				<div class="panel-heading">
					<h4>章节列表：${courseName}</h4>
				</div>
				<!-- Table -->
				<div class="panel-body">
					<h4>点击相关名称，查看相关信息。</h4>
					<table class="table .table-striped">
						<tr>
							<th>序号</th>
							<th>名称</th>
							<th>描述</th>
						</tr>
						<c:forEach var="chapter" items="${chapters}">
							<tr>
								<td>${chapter.no}</td>
								<td><a href="/teachingTest/chapter/${chapter.no}">${chapter.name}</a></td>
								<td>${chapter.description}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
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