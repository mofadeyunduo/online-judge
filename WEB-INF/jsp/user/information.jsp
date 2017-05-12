<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags"
          prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>身份信息</title>
</head>
<body>
<%@include file="../common/userNavbar.jspf" %>
<div class="container">
    <div class="row">
        <div class="page-header">
            <h1>身份信息</h1>
        </div>
        <table class="table .table-striped" oninput="satisfySubmit()">
            <tr class="row">
                <td class="col-md-2">邮箱</td>
                <td class="col-md-5">
                    <input type="email" id="email" name="email" value="${user.email}" class="form-control"
                           placeholder="邮箱长度不超过40个字符"
                           aria-describedby="basic-addon1" maxlength="40" required disabled
                           oninput="showEmailInputSuggestion()">
                <td class="col-md-5"><p id="emailError" class="text-danger"></p></td>
            </tr>
            <tr class="row">
                <td class="col-md-2">密码</td>
                <td class="col-md-5">
                    <input type="password" id="password" name="password" value="${user.password}"
                           class="form-control"
                           placeholder="密码长度不少于6个字符，不多于20个字符，只能包括数字和字母"
                           aria-describedby="basic-addon1" minlength="6" maxlength="20" required disabled
                           oninput="showAllPasswordSuggestion()">
                <td class="col-md-5"><p id="passwordError" class="text-danger"></p></td>
            </tr>
            <tr class="row" id="repasswordTr" style="display: none">
                <td class="col-md-2">确认密码</td>
                <td class="col-md-5">
                    <input type="password" id="repassword" name="repassword" class="form-control" placeholder="再次输入密码"
                           aria-describedby="basic-addon1" minlength="6" maxlength="20" pattern="[\d\w]+" required
                           oninput="showAllPasswordSuggestion()">
                <td class="col-md-5"><p id="repasswordError" class="text-danger"></p></td>
            </tr>
            <tr class="row">
                <td class="col-md-2">姓名</td>
                <td class="col-md-5">
                    <input type="text" id="name" name="name" class="form-control"
                           placeholder="你的姓名，可以很酷，不过最多只能有20个字符（中英皆可）"
                           aria-describedby="basic-addon1" maxlength="20" value="${user.name}" disabled>
                </td>
                <td class="col-md-5"></td>
            </tr>
            <tr class="row">
                <td class="col-md-2">性别</td>
                <td class="col-md-5">
                    <div class="radio-inline">
                        <label>
                            <input type="radio" name="sex" id="optionsRadios1" value="MALE" disabled
                                   <c:if test="${user.sex.toString().equals(\"MALE\")}">checked</c:if>>
                            男
                        </label>
                    </div>
                    <div class="radio-inline">
                        <label>
                            <input type="radio" name="sex" id="optionsRadios2" value="FEMALE" disabled
                                   <c:if test="${user.sex.toString().equals(\"FEMALE\")}">checked</c:if>>
                            女
                        </label>
                    </div>
                </td>
                <td class="col-md-5"></td>
            </tr>
        </table>
    </div>
    <div class="row">
        <form method="post" action="${pageContext.request.contextPath}/user/logout">
            <input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" id="id" name="id" value="${user.id}"/>
            <input type="hidden" id="enabled" name="enabled" value="${user.enabled}"/>
            <input type="hidden" id="role" name="role" value="${user.role}"/>
            <input type="submit" id="logout" value="登出" name="logout" class="btn btn-success"/>
            <input type="button" id="update" value="修改" name="update" class="btn btn-info"
                   onclick="setTagForUpdate()"/>
            <input type="hidden" id="saveUpdate" value="不能保存修改，请检查相关项填写是否正确" name="saveUpdate" class="btn btn-danger"
                   onclick="saveInformation()" disabled/>
            <input type="hidden" id="cancel" value="取消修改" name="cancel" class="btn btn-primary"
                   onclick="cancelUpdate()"/>
        </form>
    </div>
    <div class="row">
        <p id="success"></p>
    </div>
</div>
<%@include file="../common/footer.jspf" %>
<script>
    function saveInformation() {
        xmlhttp = new XMLHttpRequest();
        if (xmlhttp != null) {
            var id = document.getElementById("id").value;
            var email = document.getElementById("email").value;
            var password = document.getElementById("password").value;
            var name = document.getElementById("name").value;
            var sexes = document.getElementsByName("sex");
            var sex;
            for(var i = 0; i < sexes.length; i++) {
                if(sexes[i].checked) sex = sexes[i].value.toUpperCase();
            }
            var enabled = document.getElementById("enabled").value;
            var role = document.getElementById("role").value;
            var csrf = document.getElementsByName("_csrf")[0].value;
            xmlhttp.onreadystatechange = authorizeStateChange;
            xmlhttp.open("POST", "${pageContext.request.contextPath}/user/information/", true);
            xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xmlhttp.send("id=" + id + "&email=" + email + "&password=" + password + "&name=" + name + "&sex=" + sex + "&enabled=" + enabled + "&role=" + role + "&_csrf=" + csrf);
        }
    }

    function authorizeStateChange() {
        if (xmlhttp.readyState == 4) { // 4 = "loaded"
            if (xmlhttp.status == 200) { // 200 = "OK"
                var success = document.getElementById("success");
                if (xmlhttp.responseText == "true") {
                    success.setAttribute("class", "text-success");
                    success.innerHTML = "更新信息成功";
                    location.reload(true);
                }
                else {
                    success.setAttribute("class", "text-danger");
                    success.innerHTML = "更新信息失败";
                }
            }
        }
    }
    
    function setTagForUpdate() {
        var inputs = document.getElementsByTagName("input");
        for (var i = 0; i < inputs.length; i++) {
            inputs[i].removeAttribute("disabled");
        }
        document.getElementById("repasswordTr").removeAttribute("style");
        document.getElementById("saveUpdate").setAttribute("type","button");
        document.getElementById("saveUpdate").setAttribute("disabled","");
        document.getElementById("cancel").setAttribute("type","button");

        showRepasswordInputSuggestion();
    }

    function cancelUpdate() {
        location.reload(true);
    }

    function satisfySubmit() {
        var submit = document.getElementById("saveUpdate");
        if (isEmailValid() && isPasswordValid() && isRepasswordValid()) {
            submit.setAttribute("value", "保存修改");
            submit.setAttribute("class", "btn btn-warning");
            submit.removeAttribute("disabled")
        } else {
            submit.setAttribute("value", "不能保存修改，请检查相关项填写是否正确");
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