<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags"
          prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>题目</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container" oninput="satisfySubmit()">
    <div class="page-header">
        <h1>题目
            <small>对题目进行增删改查</small>
        </h1>
    </div>
    <p class="text-warning">注意：题目未增加之前，不可增加测试用例；增加题目之后，选择相关题目，方可增加测试用例。</p>
    <p>请选择题目：</p>
    <table class="table .table-striped">
        <tr>
            <th>题号</th>
            <th>题名</th>
            <th>描述</th>
            <th>所属题库</th>
        </tr>
        <c:forEach var="question" items="${questions}">
            <tr>
                <td>${question.qid}</td>
                <td><a href="${pageContext.request.contextPath}/testManager/question/${question.id}">${question.name}</a></td>
                <td>${question.description}</td>
                <td>${question.category.name}</td>
            </tr>
        </c:forEach>
    </table>
    <a class="btn btn-info" href="${pageContext.request.contextPath}/testManager/question">增加一个题目</a>
</div>
<%@include file="../common/footer.jspf" %>
</body>
</html>