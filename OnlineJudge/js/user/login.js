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
    if (isNullOrEmpty("username") || !checkInputPattern("^.+@.+$", "username")) return false;
    return true;
}

function showEmailInputSuggestion() {
    var emailError = document.getElementById("usernameError");
    if (isNullOrEmpty("username") == true) emailError.innerHTML = "邮箱为空";
    else if (checkInputPattern("^.+@.+$", "username") == false) emailError.innerHTML = "邮箱为格式有误";
    else emailError.innerHTML = "";
}

function authorize() {
    xmlhttp = new XMLHttpRequest();
    if (xmlhttp != null) {
        var email = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var csrf = document.getElementsByName("_csrf")[0].value;
        xmlhttp.onreadystatechange = stateChange;
        xmlhttp.open("POST", window.location.pathname + "/test", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("email=" + email + "&password=" + password + "&_csrf=" + csrf);
    }
}

function stateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            // window.location.go(-1); //刷新上一页
            success.setAttribute("class", "text-success");
            success.innerHTML = "登录成功";
            var click = document.getElementById("submit");
            click.setAttribute("type", "submit");
            click.removeAttribute("onclick");
            click.click();
        } else if (xmlhttp.status == 401) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "登录失败，邮箱或者密码错误";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}