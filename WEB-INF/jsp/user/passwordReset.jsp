<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags"
          prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>重置密码</title>
</head>
<body>
<%@include file="../common/userNavbar.jspf" %>
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
        <p id="reset"></p>
    </form>
</div>
<%@include file="../common/footer.jspf" %>
<script>
    function satisfySubmit() {
        var submit = document.getElementById("submit");
        if (isRepasswordValid() && isPasswordValid()) {
            submit.setAttribute("value", "重置");
            submit.setAttribute("class", "btn btn-success");
            submit.removeAttribute("disabled")
        } else {
            submit.setAttribute("value", "不能重置密码，检查格式");
            submit.setAttribute("class", "btn btn-danger");
            submit.setAttribute("disabled", "");
        }
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
        return idDocument.value == "";
    }

    function checkInputPattern(regexString, id) {
        var regexp = new RegExp(regexString);
        return regexp.test(document.getElementById(id).value);
    }

    var xmlhttp;

    // TODO: update to HTTPS to make sure password uploading is safe
    function updatePassword() {
        xmlhttp = new XMLHttpRequest();
        if (xmlhttp != null) {
            var password = document.getElementById("password").value;
            var csrf = document.getElementsByName("_csrf")[0].value;
            xmlhttp.onreadystatechange = authorizeStateChange;
            xmlhttp.open("POST", window.location.pathname, true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("password=" + password + "&_csrf=" + csrf);
        }
    }

    function authorizeStateChange() {
        if (xmlhttp.readyState == 4) { // 4 = "loaded"
            if (xmlhttp.status == 200) { // 200 = "OK"
                var reset = document.getElementById("reset");
                if (xmlhttp.responseText == "true") {
                    // window.location.go(-1); //刷新上一页
                    reset.setAttribute("class", "text-success");
                    reset.innerHTML = "重置成功";
                }
                else {
                    reset.setAttribute("class", "text-danger");
                    reset.innerHTML = "重置失败";
                }
            }
        }
    }
</script>
</body>
</html>