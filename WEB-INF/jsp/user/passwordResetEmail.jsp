<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags"
          prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>忘记密码</title>
</head>
<body>
<%@include file="../common/userNavbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>忘记密码</h1>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="row">
        <div class="col-md-6">
            <div class="input-group" oninput="satisfySubmit()">
                <span class="input-group-addon" id="basic-addon1">邮箱</span>
                <input type="email" id="email" name="email" class="form-control" placeholder="邮箱"
                       aria-describedby="basic-addon1" maxlength="40" required oninput="showEmailInputSuggestion()">
            </div>
        </div>
        <div class="col-md-6">
            <p id="emailError" class="text-danger"></p>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-6">
            <input class="btn btn-danger" id="submit" name="submit" type="button" value="不能验证，检查邮箱格式" disabled
                   onclick="forgetPasswordCheck()"/>
        </div>
    </div>
    <br>
    <p id="forget"></p>
    </form>
</div>
<%@include file="../common/footer.jspf" %>
<script>
    function satisfySubmit() {
        var submit = document.getElementById("submit");
        if (isEmailValid()) {
            submit.setAttribute("value", "发送邮件，验证身份");
            submit.setAttribute("class", "btn btn-success");
            submit.removeAttribute("disabled")
        } else {
            submit.setAttribute("value", "不能验证，检查邮箱格式");
            submit.setAttribute("class", "btn btn-danger");
            submit.setAttribute("disabled", "");
        }
    }

    function isEmailValid() {
        return !(isNullOrEmpty("email") || !checkInputPattern("^.+@.+$", "email"));
    }

    function showEmailInputSuggestion() {
        var emailError = document.getElementById("emailError");
        if (isNullOrEmpty("email") == true) emailError.innerHTML = "邮箱为空";
        else if (checkInputPattern("^.+@.+$", "email") == false) emailError.innerHTML = "邮箱为格式有误";
        else emailError.innerHTML = "";
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
    function forgetPasswordCheck() {
        document.getElementById("forget").innerHTML = "正在处理，请等待......"
        xmlhttp = new XMLHttpRequest();
        if (xmlhttp != null) {
            var email = document.getElementById("email").value;
            var csrf = document.getElementsByName("_csrf")[0].value;
            xmlhttp.onreadystatechange = authorizeStateChange;
            xmlhttp.open("POST", "${pageContext.request.contextPath}/user/password", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("email=" + email + "&_csrf=" + csrf);
        }
    }

    function authorizeStateChange() {
        if (xmlhttp.readyState == 4) { // 4 = "loaded"
            if (xmlhttp.status == 200) { // 200 = "OK"
                var forget = document.getElementById("forget");
                if (xmlhttp.responseText == "true") {
                    // window.location.go(-1); //刷新上一页
                    forget.setAttribute("class", "text-success");
                    forget.innerHTML = "操作成功，请检查邮箱链接重置密码";
                } else if(xmlhttp.responseText == "notExisted") {
                    forget.setAttribute("class", "text-danger");
                    forget.innerHTML = "操作失败，邮箱未注册";
                } else {
                    forget.setAttribute("class", "text-danger");
                    forget.innerHTML = "操作失败";
                }
            }
        }
    }
</script>
</body>
</html>