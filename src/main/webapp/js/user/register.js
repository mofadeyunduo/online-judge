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

function registerUser() {
    xmlhttp = new XMLHttpRequest();
    if (xmlhttp != null) {
        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;
        var name = document.getElementById("name").value;
        var sexes = document.getElementsByName("sex");
        var sex;
        for (var i = 0; i < sexes.length; i++) {
            if (sexes[i].checked) sex = sexes[i].value.toUpperCase();
        }
        var enabled = document.getElementById("enabled").value;
        var role = document.getElementById("role").value;
        var csrf = document.getElementsByName("_csrf")[0].value;
        xmlhttp.onreadystatechange = stateChange;
        xmlhttp.open("POST", window.location.pathname, true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("&email=" + email + "&password=" + password + "&name=" + name + "&sex=" + sex + "&enabled=" + enabled + "&role=" + role + "&_csrf=" + csrf);
    }
}

function stateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            success.setAttribute("class", "text-success");
            success.innerHTML = "注册成功";
            alert("注册成功，点击确定进行登录")
            window.location.href = getContextPath() + "/user/information"
        } else if (xmlhttp.status == 409) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "用户邮箱已存在";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}