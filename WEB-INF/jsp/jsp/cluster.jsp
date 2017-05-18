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
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<h4>聚类分析</h4>
			</div>
			<div class="panel-body">
				<c:choose>
					<c:when test="${cluster != null}">
						<p>${cluster}</p>
					</c:when>
					<c:otherwise>
						<form action="/teachingTestManager/homework/cluster" method="POST">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<table class="table .table-striped">
								<tr>
									<td>输入题号</td>
									<td><select name="qidDotName"><c:forEach
												var="question" items="${questions}">
												<option>${question.qid}.${question.name}</option>
											</c:forEach></select></td>
								</tr>
							</table>
							<button class="btn btn-success" name="submit" type="submit">提交</button>
						</form>
					</c:otherwise>
				</c:choose>
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