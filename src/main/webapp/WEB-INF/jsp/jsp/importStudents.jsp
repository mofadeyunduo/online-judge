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
	<%@include file="navbar.jspf"%>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4>为课程导入学生（重复导入将覆盖：1、该课程之前的平时、期末成绩。2、学生初始密码。）</h4>
			</div>
			<div class="panel-body">
				<form role="form"
					action="/teachingTestManager/uploadStudents"
					method="POST">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<p>选择课程：<select name="name"><c:forEach var="course" items="${courses}"><option>${course.name}</option></c:forEach></select></p>
					<p>每行格式：学号,姓名</p>
					<div class="form-group">
						<textarea name="students" class="form-control" rows="15"></textarea>
					</div>
					<input class="btn btn-success" type="submit" value="上传" />
				</form>
			</div>
		</div>
	</div>
	<%@include file="footer.jspf"%>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="${pageContext.request.contextPath}/resource/js/jquery-3.1.1.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="${pageContext.request.contextPath}/resource/js/bootstrap.min.js"></script>
</body>
</html>