<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>用户导入</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>用户导入
            <small>导入用户信息，查看特定用户成绩</small>
        </h1>
    </div>
    <p>上传文件，导入用户，格式要求如下：</p>
    <ol>
        <li><strong>xls</strong> 或者 <strong>xlsx</strong> 格式。</li>
        <li>sheet 必须为<strong>第一个 sheet</strong>。</li>
        <li>列必须包括：<strong>用户邮箱</strong>。</li>
    </ol>
    <p>上传后，会发生如下情况：</p>
    <ol>
        <li>若用户<strong>不存在</strong>，则<strong>数据库自动创建该用户</strong>，<strong>初始密码 000000</strong>。</li>
        <li>若用户<strong>存在</strong>，<strong>保持原有账户</strong>。</li>
    </ol>
    <h2 class="text-danger">请仔细阅读上述内容再进行上传！</h2>
    <br>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/testManager/import/user" method="POST">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="file" id="usersFile" name="usersFile" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" onchange="satisfySubmit()">
        <br>
        <input class="btn btn-danger" id="submit" type="submit" value="不能上传，请选择文件或者检查文件格式" disabled/>
    </form>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/import/user.js"></script>
</body>
</html>