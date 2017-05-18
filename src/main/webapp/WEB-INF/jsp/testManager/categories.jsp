<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags"
          prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>题库管理</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>题库管理 <small>对题库进行增删改查</small></h1>
    </div>
    <p>请选择题库：</p>
    <table class="table .table-striped">
        <tr>
            <th>题库名</th>
            <th>描述</th>
        </tr>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td><a href="${pageContext.request.contextPath}/testManager/category/${category.id}">${category.name}</a></td>
                <td>${category.description}</td>
            </tr>
        </c:forEach>
    </table>
    <a class="btn btn-success" href="${pageContext.request.contextPath}/testManager/category">增加一个题库</a>
</div>
<%@include file="../common/footer.jspf" %>
</body>
</html>