function cancelUpdate() {
    location.reload(true);
}

function satisfySubmit() {
    var submit = document.getElementById("saveUpdate");
    if (isEmailValid() && isPasswordValid() && isRepasswordValid()) {
        submit.setAttribute("value", "提交修改");
        submit.setAttribute("class", "btn btn-warning");
        submit.removeAttribute("disabled")
    } else {
        submit.setAttribute("value", "不能保存修改，请检查相关项填写是否正确");
        submit.setAttribute("class", "btn btn-danger");
        submit.setAttribute("disabled", "");
    }
}

function setTagForUpdate() {
    var inputs = document.getElementsByTagName("input");
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].removeAttribute("disabled");
    }
    document.getElementById("repasswordTr").removeAttribute("style");
    document.getElementById("saveUpdate").setAttribute("type", "button");
    document.getElementById("saveUpdate").setAttribute("disabled", "");
    document.getElementById("cancel").setAttribute("type", "button");
    document.getElementById("logout").setAttribute("disabled", "");
    document.getElementById("update").setAttribute("disabled", "");
    showRepasswordInputSuggestion();
}

function saveInformation() {
    xmlhttp = new XMLHttpRequest();
    if (xmlhttp != null) {
        var id = document.getElementById("id").value;
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
        xmlhttp.send("id=" + id + "&email=" + email + "&password=" + password + "&name=" + name + "&sex=" + sex + "&enabled=" + enabled + "&role=" + role + "&_csrf=" + csrf);
    }
}

function stateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            success.setAttribute("class", "text-success");
            success.innerHTML = "更新信息成功";
            location.reload(true);
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}