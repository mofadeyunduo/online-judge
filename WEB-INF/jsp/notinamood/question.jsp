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
<title>题目 —— Java 教学评测系统</title>
</head>
<body>
	<%@include file="../common/userNavbar.jspf"%>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3>${question.qid}<c:out value="." />
							${question.name}
						</h3>
					</div>
					<div class="panel-body">
						<p>${question.description}</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">排名（同排名以提交时间较早为先）</div>
					<!-- Table -->
					<table class="table">
						<tbody>
							<tr>
								<td>姓名</td>
								<td>正确率</td>
							</tr>
							<c:forEach var="rank" items="${ranks}">
								<tr>
									<td>${rank.name}</td>
									<td>${rank.correctRate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<hr>
		<span><security:authentication var="sid"
				property="principal.username" /></span>
		<form role="form"
			action="/teachingTest/question/${sid}/${question.qid}/submit"
			method="POST">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<p>语言：Java，输入输出流：System.In 和 System.Out，主类：Main，函数：标准 main 函数。</p>
			<div class="form-group">
				<textarea name="code" class="form-control" rows="15"><%@include
						file="defaultCode.txt"%></textarea>
			</div>
			<button class="btn btn-success" type="submit">提交代码</button>
		</form>
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