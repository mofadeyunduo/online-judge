<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags"
          prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>题库</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container" oninput="satisfySubmit()">
    <div class="page-header">
        <h1>测试用例
            <small>导入测试用例</small>
        </h1>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <p>已存在的测试用例：</p>
    <textarea class="form-control" rows="15" disabled>${exitedTestDatas}</textarea>
    <br>
    <p class="text-warning">导入测试用例会删除之前的测试用例，请谨慎操作！</p>
    <p>需要导入的测试用例，每行为一组输入、输出，以分隔符 $。多组测试用例按行分割：</p>
    <textarea id="inputOutputs" name="inputOutputs" class="form-control" rows="15"></textarea>
    <br>
    <button id="updateButton" class="btn btn-success" onclick="importTestData()">导入题库</button>
    <br>
    <p id="success"></p>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/testManager/testData.js"></script>
<script src="${pageContext.request.contextPath}/js/testManager/testManager.js"></script>
</body>
</html>