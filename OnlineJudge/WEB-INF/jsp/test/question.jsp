<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>题目</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>题目</h1>
    </div>
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>${question[0].name}</h3>
                </div>
                <div class="panel-body">
                    <p>${question[0].description}</p>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <span><security:authentication var="sid" property="principal.username"/></span>
    <form role="form" action="${pageContext.request.contextPath}/test/question/answer" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="qid" value="${question[0].id}">
        选择语言：<select id="languageName" name="languageName" onclick="switchLanguage()">
            <option value="java">Java</option>
            <option value="cpp">C++</option>
        </select>
        <p id="languageDescription">语言：Java，输入输出流：System.In 和 System.Out，主类：Main，函数：标准 main 函数。</p>
        <div class="form-group">
            <textarea id="code" name="code" class="form-control" rows="15"><%@include file="../../../js/test/defaultJavaCode.txt" %></textarea>
        </div>
        <pre id="javaCode" style="display: none"><%@include file="../../../js/test/defaultJavaCode.txt" %></pre>
        <pre id="cppCode" style="display: none"><%@include file="../../../js/test/defaultCppCode.txt" %></pre>
        <button class="btn btn-success" type="submit">提交代码</button>
    </form>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/test/question.js"></script>
</body>
</html>