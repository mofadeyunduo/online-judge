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
				<h4>题库：${course.name}</h4>
			</div>
			<c:choose>
				<c:when test="${course != null}">
					<c:set var="action"
						value="/teachingTestManager/course/updateCourse" />
				</c:when>
				<c:otherwise>
					<c:set var="action"
						value="/teachingTestManager/course/insertCourse" />
				</c:otherwise>
			</c:choose>
			<div class="panel-body">
				<form role="form" action="${action}" method="POST" enctype="application/x-www-form-urlencoded;charset=utf-8">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="oldName"
						value="${course.name}" />
					<table class="table .table-striped">
						<tr>
							<td>名称</td>
							<td><input type="text" name="newName" value="${course.name}"></td>
						</tr>
						<tr>
							<td>描述</td>
							<td><input type="text" name="newDescription"
								value="${course.description}"></td>
						</tr>
					</table>
					<button class="btn btn-success" type="submit">提交修改过的课程</button>
					<c:if test="${course != null}">
						<a class="btn btn-danger"
							href="/teachingTestManager/course/deleteCourse/${course.name}">删除课程</a>
					</c:if>
				</form>
			</div>
		</div>
		<div>
			<c:if test="${course != null}">
				<div class="panel panel-default">
					<!-- Default panel contents -->
					<div class="panel-heading">
						<h4>章节列表：${courseName}</h4>
					</div>
					<!-- Table -->
					<div class="panel-body">
						<h4>点击相关名称，可以查看、修改或删除。</h4>
						<table class="table .table-striped">
							<tr>
								<th>序号</th>
								<th>名称</th>
								<th>描述</th>
							</tr>
							<c:forEach var="chapter" items="${chapters}">
								<tr>
									<td>${chapter.no}</td>
									<td><a href="/teachingTestManager/chapter/${chapter.no}">${chapter.name}</a></td>
									<td>${chapter.description}</td>
								</tr>
							</c:forEach>
						</table>
						<a class="btn btn-info" href="/teachingTestManager/chapter/0">增加一个章节</a>
					</div>
				</div>
			</c:if>
		</div>
		<c:if test="${course != null}">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>为该课程导入学生（重复导入将覆盖：1、该课程之前的平时、期末成绩。2、学生初始密码。）</h4>
				</div>
				<div class="panel-body">
					<p>每行格式：学号,姓名</p>
					<form role="form"
						action="/teachingTestManager/uploadStudents/${course.name}"
						method="POST">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="form-group">
							<textarea name="students" class="form-control" rows="15"></textarea>
						</div>
						<p>下面是已经导入的学生：</p>
						<div class="form-group">
							<textarea name="existingStudents" class="form-control" rows="15"
								readonly>${students}</textarea>
						</div>
						<input class="btn btn-success" type="submit" value="上传" />
					</form>
				</div>
			</div>
		</c:if>
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