<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags"
          prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>注册</title>
</head>
<body>
<%@include file="../common/userNavbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>注册</h1>
    </div>
    <form class="form-signin" method="POST" action="${pageContext.request.contextPath}/user/register"
          oninput="satisfySubmit()">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="row">
            <div class="col-md-8">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">邮箱*</span>
                    <input type="email" id="email" name="email" class="form-control" placeholder="邮箱长度不超过40个字符"
                           aria-describedby="basic-addon1" maxlength="40" required oninput="showEmailInputSuggestion()">
                </div>
            </div>
            <div class="col-md-4">
                <p id="emailError" class="text-danger"></p>
            </div>
        </div>
        <br/>
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
        <br/>
        <div class="row">
            <div class="col-md-8">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon4">姓名</span>
                    <input type="text" name="name" class="form-control" placeholder="你的姓名，可以很酷，不过最多只能有20个字符（中英皆可）"
                           aria-describedby="basic-addon1" maxlength="20">
                </div>
            </div>
            <div class="col-md-4">
                <p></p>
            </div>
        </div>
        <br/>
        <div class="row">
            <div class="col-md-8">
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon5">性别</span>
                    <div class="form-control">
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="sex" id="optionsRadios1" value="MALE">
                                男
                            </label>
                        </div>
                        <div class="radio-inline">
                            <label>
                                <input type="radio" name="sex" id="optionsRadios2" value="FEMALE">
                                女
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <p></p>
            </div>
        </div>
        <br/>
        <input type="hidden" name="enabled" value="true"/>
        <input type="hidden" name="role" value="user"/>
        <input id="submit" type="button" value="不能注册，请检查相关项填写是否正确" class="btn btn-danger" disabled>
    </form>
</div>
<%@include file="../common/footer.jspf" %>
<script>
    function satisfySubmit() {
        var submit = document.getElementById("submit");
        if (isEmailValid() && isPasswordValid() && isRepasswordValid()) {
            submit.setAttribute("type", "submit");
            submit.setAttribute("value", "提交注册");
            submit.setAttribute("class", "btn btn-success");
            submit.removeAttribute("disabled")
        } else {
            submit.setAttribute("type", "button");
            submit.setAttribute("value", "不能注册，请检查相关项填写是否正确");
            submit.setAttribute("class", "btn btn-danger");
            submit.setAttribute("disabled", "");
        }
    }

    function isEmailValid() {
        if (isNullOrEmpty("email") || !checkInputPattern("^.+@.+$", "email")) return false;
        return true;
    }

    function showEmailInputSuggestion() {
        var emailError = document.getElementById("emailError");
        if (isNullOrEmpty("email") == true) emailError.innerHTML = "邮箱为空";
        else if (checkInputPattern("^.+@[^@]+$", "email") == false) emailError.innerHTML = "邮箱为格式有误";
        else emailError.innerHTML = "";

    }

    function isPasswordValid() {
        if (isNullOrEmpty("password") || !checkInputPattern("^[\\w\\d]{6,20}$", "password")) return false;
        else return true;
    }

    function showPasswordInputSuggestion() {
        var passwordError = document.getElementById("passwordError");
        if (isNullOrEmpty("password") == true) passwordError.innerHTML = "密码为空";
        else if (checkInputPattern("^[\\w\\d]{6,20}$", "password") == false) passwordError.innerHTML = "密码过短或过长，或者包含非字母数字";
        else passwordError.innerHTML = "";
    }

    function isRepasswordValid() {
        if (isNullOrEmpty("repassword") || !checkIfPasswordIsSame()) return false;
        else return true;
    }

    function showRepasswordInputSuggestion() {
        var repasswordError = document.getElementById("repasswordError");
        if (isNullOrEmpty("repassword") == true) repasswordError.innerHTML = "请确认密码";
        else if (checkIfPasswordIsSame() == false) repasswordError.innerHTML = "密码不一致";
        else repasswordError.innerHTML = "";
    }

    function showAllPasswordSuggestion() {
        showPasswordInputSuggestion();
        showRepasswordInputSuggestion()
    }

    function checkIfPasswordIsSame() {
        if (document.getElementById("password").value == document.getElementById("repassword").value) return true;
        else return false;
    }

    function isNullOrEmpty(id) {
        var idDocument = document.getElementById(id);
        if (idDocument.value == "") return true;
        else return false;
    }

    function checkInputPattern(regexString, id) {
        var regexp = new RegExp(regexString);
        return regexp.test(document.getElementById(id).value);
    }
</script>
</body>
</html>