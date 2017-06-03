<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>成绩管理</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="page-header">
        <h1>成绩管理
            <small>点击邮箱，修改成绩</small>
        </h1>
    </div>
    选择题库：
    <select id="id" items="${categories}">
        <c:forEach items="${categories}" var="category">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select>
    <button class="btn btn-success" onclick="selectAllScores()">查询</button>
    <p id="success" class="text-danger"></p>
    <p class="text-warning">若想查看用户成绩，请先导入相关用户，即使学生已有成绩，未导入的学生成绩不会显示。</p>
    <table id="table" class="table .table-striped">
        <tr>
            <th>姓名</th>
            <th>邮箱</th>
            <th>平时成绩</th>
            <th>实践成绩</th>
        </tr>
    </table>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/scoreManager/scores.js"></script>
</body>
</html>