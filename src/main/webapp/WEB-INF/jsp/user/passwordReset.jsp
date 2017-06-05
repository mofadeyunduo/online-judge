<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>重置密码</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>重置密码</h1>
    </div>
    <form class="form-signin" method="POST" action="${pageContext.request.contextPath}/user/login"
          oninput="satisfySubmit()">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="row">
            <div class="col-md-8">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon2">密码*</span>
                    <input type="password" id="password" name="password" class="form-control"
                           placeholder="密码长度不少于6个字符，不多于20个字符，只能包括数字和字母"
                           aria-describedby="basic-addon1" minlength="6" maxlength="20" required
                           oninput="showAllPasswordSuggestion()">
                </div>
            </div>
            <div class="col-md-4">
                <p id="passwordError" class="text-danger"></p>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-8">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon3">确认密码*</span>
                    <input type="password" id="repassword" name="repassword" class="form-control" placeholder="再次输入密码"
                           aria-describedby="basic-addon1" minlength="6" maxlength="20" pattern="[\d\w]+" required
                           oninput="showAllPasswordSuggestion()">
                </div>
            </div>
            <div class="col-md-4">
                <p id="repasswordError" class="text-danger"></p>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-md-6">
                <input class="btn btn-danger" id="submit" name="submit" type="button" value="不能重置密码，检查格式" disabled
                       onclick="updatePassword()"/>
            </div>
        </div>
        <br>
        <p id="success"></p>
    </form>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/user/user.js"></script>
<script src="${pageContext.request.contextPath}/js/user/passwordReset.js"></script>
</body>
</html>