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
function updatePassword() {
    xmlhttp = new XMLHttpRequest();
    if (xmlhttp != null) {
        var password = document.getElementById("password").value;
        var csrf = document.getElementsByName("_csrf")[0].value;
        xmlhttp.onreadystatechange = stateChange;
        xmlhttp.open("POST", window.location.pathname, true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("password=" + password + "&_csrf=" + csrf);
    }
}

function stateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            // window.location.go(-1); //刷新上一页
            success.setAttribute("class", "text-success");
            success.innerHTML = "重置成功";
        } else if (xmlhttp.status == 403) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "过期的验证，请重新操作";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}