<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags"
          prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>登录</title>
</head>
<body>
<%@include file="../common/userNavbar.jspf" %>
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
                <p id="emailError" class="text-danger"></p>
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
        <p id="login"></p>
    </form>
</div>
<%@include file="../common/footer.jspf" %>
<script>
    function satisfySubmit() {
        var submit = document.getElementById("submit");
        if (isEmailValid() && isPasswordValid()) {
            submit.setAttribute("value", "登录");
            submit.setAttribute("class", "btn btn-success");
            submit.removeAttribute("disabled")
        } else {
            submit.setAttribute("value", "不能登录，检查邮箱和密码格式");
            submit.setAttribute("class", "btn btn-danger");
            submit.setAttribute("disabled", "");
        }
    }

    function isEmailValid() {
        return !(isNullOrEmpty("username") || !checkInputPattern("^.+@.+$", "username"));
    }

    function showEmailInputSuggestion() {
        var emailError = document.getElementById("emailError");
        if (isNullOrEmpty("username") == true) emailError.innerHTML = "邮箱为空";
        else if (checkInputPattern("^.+@.+$", "username") == false) emailError.innerHTML = "邮箱为格式有误";
        else emailError.innerHTML = "";
    }

    function isPasswordValid() {
        return !(isNullOrEmpty("password") || !checkInputPattern("^[\\w\\d]{6,20}$", "password"));
    }

    function showPasswordInputSuggestion() {
        var passwordError = document.getElementById("passwordError");
        if (isNullOrEmpty("password") == true) passwordError.innerHTML = "密码为空";
        else if (checkInputPattern("^[\\w\\d]{6,20}$", "password") == false) passwordError.innerHTML = "密码过短或过长，或者包含非字母数字";
        else passwordError.innerHTML = "";
    }

    function isNullOrEmpty(id) {
        var idDocument = document.getElementById(id);
        return idDocument.value == "";
    }

    function checkInputPattern(regexString, id) {
        var regexp = new RegExp(regexString);
        return regexp.test(document.getElementById(id).value);
    }

    var xmlhttp;

    // TODO: update to HTTPS to make sure password uploading is safe
    function authorize() {
        xmlhttp = new XMLHttpRequest();
        if (xmlhttp != null) {
            var email = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var csrf = document.getElementsByName("_csrf")[0].value;
            xmlhttp.onreadystatechange = authorizeStateChange;
            xmlhttp.open("POST", "${pageContext.request.contextPath}/user/login/test", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("email=" + email + "&password=" + password + "&_csrf=" + csrf);
        }
    }

    function authorizeStateChange() {
        if (xmlhttp.readyState == 4) { // 4 = "loaded"
            if (xmlhttp.status == 200) { // 200 = "OK"
                var login = document.getElementById("login");
                if (xmlhttp.responseText == "true") {
                    // window.location.go(-1); //刷新上一页
                    login.setAttribute("class", "text-success");
                    login.innerHTML = "登录成功";
                    var click = document.getElementById("submit");
                    click.setAttribute("type", "submit");
                    click.removeAttribute("onclick");
                    click.click();
                }
                else {
                    login.setAttribute("class", "text-danger");
                    login.innerHTML = "登录失败，邮箱或者密码错误";
                }
            }
        }
    }
</script>
</body>
</html>