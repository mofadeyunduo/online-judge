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
        <h1>题库
            <small>
                <c:choose>
                    <c:when test="${category == null}">增加一个题库</c:when>
                    <c:otherwise>修改，删除一个题库</c:otherwise>
                </c:choose>
            </small>
        </h1>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table class="table .table-striped">
        <tr>
            <td>名称</td>
            <td><input type="text" id="name" name="name" value="${category.name}" maxlength="45"
                       oninput="showNameSuggestion()"></td>
            <td id="nameError" class="text-danger"></td>
        </tr>
        <tr>
            <td>描述</td>
            <td><input type="text" id="description" name="description" value="${category.description}"></td>
            <td id="descriptionError" class="text-danger"></td>
        </tr>
    </table>
    <c:choose>
        <c:when test="${category != null}">
            <button id="updateButton" class="btn btn-success" onclick="updateCategory()">提交题库</button>
            <button id="deleteButton" class="btn btn-info" onclick="deleteCategory()">删除题库</button>
        </c:when>
        <c:otherwise>
            <button id="insertButton" class="btn btn-danger" onclick="insertCategory()" disabled>不能提交题库，请检查相关项填写是否正确
            </button>
        </c:otherwise>
    </c:choose>
    <p></p>
    <p id="success"></p>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/testManager/category.js"></script>
<script src="${pageContext.request.contextPath}/js/testManager/testManager.js"></script>
</body>
</html>