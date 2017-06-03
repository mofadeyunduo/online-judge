<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>编译错误</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>编译错误</h1>
    </div>
    <p>源码：</p>
    <pre>${code}</pre>
    <p>错误：</p>
    <pre class="alert alert-danger" role="alert">${compileError}</pre>
</div>
</div>
<%@include file="../common/footer.jspf" %>
</body>
</html>