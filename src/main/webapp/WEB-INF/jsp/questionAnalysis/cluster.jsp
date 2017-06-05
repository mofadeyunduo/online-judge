<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../common/header.jspf" %>
	<title>抄袭检测</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<div class="page-header">
		<h1>抄袭检测
			<small>利用 K-means 聚类检测</small>
		</h1>
	</div>
	选择题目：
	<select id="id" items="${questions}">
		<c:forEach items="${questions}" var="question">
			<option value="${question.id}">${question.qid}.${question.name}</option>
		</c:forEach>
	</select>
	<button class="btn btn-success" onclick="selectQuestion()">查询</button>
	<p id="success" class="text-danger"></p>
	<pre id="cluster">暂无结果</pre>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/questionAnalysis/cluster.js"></script>
</body>
</html>