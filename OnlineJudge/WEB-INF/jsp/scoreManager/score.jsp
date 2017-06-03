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
        <h1>成绩修改
            <small>修改平时成绩</small>
        </h1>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table class="table .table-striped" oninput="satisfySubmit()">
        <tr>
            <td class="col-md-4">姓名</td>
            <td class="col-md-4">${score.user.name}</td>
            <td id="nameError" class="text-danger"></td>
        </tr>
        <tr>
            <td class="col-md-4">邮箱</td>
            <td class="col-md-4">${score.user.email}</td>
            <td id="emailScoreError" class="text-danger"></td>
        </tr>
        <tr>
            <td class="col-md-4">平时成绩</td>
            <td class="col-md-4"><input type="number" id="usualScore" name="usualScore" value="${score.usualScore}" oninput="showUsualScoreInputSuggestion()"></td>
            <td id="usualScoreError" class="text-danger"></td>
        </tr>
    </table>
    <button class="btn btn-success" id="submit" type="submit" onclick="updateUsualScore()">提交修改过的成绩</button>
    <p id="success" class="text-danger"></p>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/scoreManager/score.js"></script>
</body>
</html>