function satisfySubmit() {
    var submit = document.getElementById("updateButton");
    if(submit == null) submit = document.getElementById("insertButton");
    if (!isNullOrEmpty("name")) {
        submit.innerHTML = "提交题库";
        submit.setAttribute("class", "btn btn-success");
        submit.removeAttribute("disabled")
    } else {
        submit.innerHTML = "不能提交题库，请检查相关项填写是否正确";
        submit.setAttribute("class", "btn btn-danger");
        submit.setAttribute("disabled", "");
    }
}

function showNameSuggestion() {
    var passwordError = document.getElementById("nameError");
    if (isNullOrEmpty("name") == true) passwordError.innerHTML = "输入为空";
    else passwordError.innerHTML = "";
}

function showDescriptionSuggestion() {
    var passwordError = document.getElementById("descriptionError");
    if (isNullOrEmpty("description") == true) passwordError.innerHTML = "输入为空";
    else passwordError.innerHTML = "";
}

function insertCategory() {
    var name = document.getElementById("name").value;
    var description = document.getElementById("description").value;
    var csrf = document.getElementsByName("_csrf")[0].value;

    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = insertStateChange;
    xmlhttp.open("POST", window.location.pathname + "/insert", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("&name=" + name + "&description=" + description + "&_csrf=" + csrf);
}

function insertStateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            success.setAttribute("class", "text-success");
            success.innerHTML = "增加题库成功";
        } else if (xmlhttp.status == 409) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "题库已存在";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}

function updateCategory() {
    var name = document.getElementById("name").value;
    var description = document.getElementById("description").value;
    var csrf = document.getElementsByName("_csrf")[0].value;

    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = updateStateChange;
    xmlhttp.open("POST", window.location.pathname + "/update", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("&name=" + name + "&description=" + description + "&_csrf=" + csrf);
}

function updateStateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            success.setAttribute("class", "text-success");
            success.innerHTML = "更新题库成功";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}

function deleteCategory() {
    var csrf = document.getElementsByName("_csrf")[0].value;
    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = deleteStateChange;
    xmlhttp.open("POST", window.location.pathname + "/delete", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("_csrf=" + csrf);
}

function deleteStateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            success.setAttribute("class", "text-success");
            success.innerHTML = "删除题库成功";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}