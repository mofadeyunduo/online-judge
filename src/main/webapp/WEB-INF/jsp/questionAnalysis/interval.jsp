<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>成绩区间</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="page-header">
        <h1>成绩区间
            <small>每题的成绩区间</small>
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
    <table class="table">
        <tbody>
        <tr>
            <td>区间</td>
            <td>人数</td>
        </tr>
        <tr>
            <td>90+</td>
            <td id="interval90"></td>
        </tr>
        <tr>
            <td>80-89</td>
            <td id="interval80"></td>
        </tr>
        <tr>
            <td>70-79</td>
            <td id="interval70"></td>
        </tr>
        <tr>
            <td>60-69</td>
            <td id="interval60"></td>
        </tr>
        <tr>
            <td>60-</td>
            <td id="interval0"></td>
        </tr>
        </tbody>
    </table>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/questionAnalysis/interval.js"></script>
</body>
</html>