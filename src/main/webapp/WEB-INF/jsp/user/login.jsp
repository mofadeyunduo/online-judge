<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>登录</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>登录</h1>
    </div>
    <form class="form-signin" method="POST" action="${pageContext.request.contextPath}/user/login"
          oninput="satisfySubmit()">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="row">
            <div class="col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">邮箱</span>
                    <input type="email" id="username" name="username" class="form-control" placeholder="邮箱"
                           aria-describedby="basic-addon1" maxlength="40" required oninput="showEmailInputSuggestion()">
                </div>
            </div>
            <div class="col-md-6">
                <p id="usernameError" class="text-danger"></p>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon2">密码</span>
                    <input type="password" id="password" name="password" class="form-control"
                           placeholder="密码"
                           aria-describedby="basic-addon1" minlength="6" maxlength="20" required
                           oninput="showPasswordInputSuggestion()">
                </div>
            </div>
            <div class="col-md-6">
                <p id="passwordError" class="text-danger"></p>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-md-6">
                <input id="remember-me" name="remember-me" type="checkbox"/>一天内记住我
            </div>
            <div class="col-md-6">
                <p id="repaswordError" class="text-danger"></p>
            </div>
        </div><!-- /.row -->
        <br>
        <div class="row">
            <div class="col-md-6">
                <input class="btn btn-danger" id="submit" name="submit" type="button" value="不能登陆，检查邮箱和密码格式" disabled
                       onclick="authorize()"/>
                <a class="btn btn-info" href="${pageContext.request.contextPath}/user/register">没有账户？点此注册</a>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/password">忘记密码</a>
            </div>
        </div>
        <br>
        <p id="success"></p>
    </form>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/user/user.js"></script>
<script src="${pageContext.request.contextPath}/js/user/login.js"></script>
</body>
</html>