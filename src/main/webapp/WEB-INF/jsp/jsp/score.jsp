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
<title>题目列表 —— Java 教学评测系统</title>
</head>
<body>
	<%@include file="navbar.jspf"%>
	<div class="container">
		<div class="row" id="DescriptionContent">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>课程：${score.name}</h4>
				</div>
				<div class="panel-body">
					<form role="form"
						action="/teachingTestManager/homework/updateScore/${score.sid}/${score.name}"
						method="POST">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<table class="table .table-striped">
							<tr>
								<td>学号（不可修改）</td>
								<td><input type="number" name="sid" value="${score.sid}"
									readonly></td>
							</tr>
							<tr>
								<td>平时成绩</td>
								<td><input type="text" name=usualScore
									value="${score.usualScore}"></td>
							</tr>
							<tr>
								<td>测试成绩（不可修改）</td>
								<td><input type="text" name="practiceScore"
									value="${score.practiceScore}" readonly></td>
							</tr>
							<tr>
								<td>期末成绩</td>
								<td><input type="text" name="finalScore"
									value="${score.finalScore}"></td>
							</tr>
						</table>
						<button class="btn btn-success" type="submit">提交修改过的成绩</button>
					</form>
				</div>
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