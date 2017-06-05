<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../common/header.jspf" %>
	<title>题目分析</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
	<div class="container">
        <div class="page-header">
            <h1>题目分析
                <small>各种题目分析</small>
            </h1>
        </div>
		<div class="jumbotron">
			<p>1、根据题目进行<a href="${pageContext.request.contextPath}/testManager/interval">准确率区间分析</a>。</p>
			<p>2、查看某一题目的<a href="${pageContext.request.contextPath}/testManager/cluster">聚类分析</a>。</p>
		</div>
	</div>
	<%@include file="../common/footer.jspf"%>
</body>
</html>