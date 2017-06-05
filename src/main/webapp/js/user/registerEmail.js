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

function forgetPasswordCheck() {
    document.getElementById("success").innerHTML = "正在处理，请等待......"
    xmlhttp = new XMLHttpRequest();
    if (xmlhttp != null) {
        var email = document.getElementById("email").value;
        var csrf = document.getElementsByName("_csrf")[0].value;
        xmlhttp.onreadystatechange = stateChange;
        xmlhttp.open("POST", window.location.pathname, true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("email=" + email + "&_csrf=" + csrf);
    }
}

function stateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            // window.location.go(-1); //刷新上一页
            success.setAttribute("class", "text-success");
            success.innerHTML = "操作成功，请检查邮箱链接注册用户";
        } else if (xmlhttp.status == 409) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "邮箱已注册";
        } else if (xmlhttp.status == 503) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}