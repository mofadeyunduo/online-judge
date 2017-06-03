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
        <h1>导入结果
            <small>导入的结果</small>
        </h1>
    </div>
    <c:choose>
        <c:when test="${success != null}">
            <p>下列用户导入成功：</p>
            <pre>${success}</pre>
        </c:when>
        <c:otherwise>
            <p>用户导入失败，全部回滚，原因是：</p>
            <pre>${failure}</pre>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="../common/footer.jspf" %>
</body>
</html>