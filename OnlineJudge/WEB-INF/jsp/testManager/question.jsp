<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags"
          prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>题目</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container" oninput="satisfySubmit()">
    <div class="page-header">
        <h1>题目
            <small>
                <c:choose>
                    <c:when test="${question == null}">增加一个题目</c:when>
                    <c:otherwise>修改，删除一个题目</c:otherwise>
                </c:choose>
            </small>
        </h1>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table class="table .table-striped">
        <tr>
            <td class="col-md-4">题号</td>
            <td class="col-md-4">
                <input type="text" step="1" id="qid" name="qid" value="${question.qid}" oninput="showQidSuggestion()">
            </td>
            <td id="qidError" class="text-danger"></td>
        </tr>
        <tr>
            <td>题名</td>
            <td>
                <input type="text" id="name" name="name" value="${question.name}" maxlength="45"
                       oninput="showNameSuggestion()">
            </td>
            <td id="nameError" class="text-danger"></td>
        </tr>
        <tr>
            <td>描述</td>
            <td>
                <input type="text" id="description" name="description" value="${question.description}">
            </td>
            <td id="descriptionError" class="text-danger"></td>
        </tr>
        <tr>
            <td>所属题库</td>
            <td>
                <select style="height: 23px" id="cid" name="cid">
                    <c:forEach var="category" items="${categories}">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td id="cidError" class="text-danger"></td>
        </tr>
    </table>
    <c:choose>
        <c:when test="${question != null}">
            <button id="updateButton" class="btn btn-success" onclick="updateQuestion()">提交题目</button>
            <button id="deleteButton" class="btn btn-info" onclick="deleteQuestion()">删除题目</button>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/testManager/testData/${question.id}">增加测试用例</a>
        </c:when>
        <c:otherwise>
            <button id="insertButton" class="btn btn-danger" onclick="insertQuestion()" disabled>不能提交题目，请检查相关项填写是否正确
            </button>
        </c:otherwise>
    </c:choose>
    <p id="success"></p>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/testManager/question.js"></script>
<script src="${pageContext.request.contextPath}/js/testManager/testManager.js"></script>
</body>
</html>