function satisfySubmit() {
    var submit = document.getElementById("updateButton");
    if (submit == null) submit = document.getElementById("insertButton");
    if (!isNullOrEmpty("qid") && !isNullOrEmpty("name")  && checkInputPattern("^\\d+$", "qid")) {
        submit.innerHTML = "提交题库";
        submit.setAttribute("class", "btn btn-success");
        submit.removeAttribute("disabled")
    } else {
        submit.innerHTML = "不能提交题库，请检查相关项填写是否正确";
        submit.setAttribute("class", "btn btn-danger");
        submit.setAttribute("disabled", "");
    }
}

function showQidSuggestion() {
    var error = document.getElementById("qidError");
    if (isNullOrEmpty("qid") == true) error.innerHTML = "输入为空";
    else if (!checkInputPattern("^\\d+$", "qid")) error.innerHTML = "输入不为整数";
    else error.innerHTML = "";
}

function showNameSuggestion() {
    var error = document.getElementById("nameError");
    if (isNullOrEmpty("name") == true) error.innerHTML = "输入为空";
    else error.innerHTML = "";
}

function showDescriptionSuggestion() {
    var error = document.getElementById("descriptionError");
    if (isNullOrEmpty("description") == true) error.innerHTML = "输入为空";
    else error.innerHTML = "";
}

function insertQuestion() {
    var qid = document.getElementById("qid").value;
    var name = document.getElementById("name").value;
    var description = document.getElementById("description").value;
    var cid = document.getElementById("cid").value;
    var csrf = document.getElementsByName("_csrf")[0].value;

    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = insertStateChange;
    xmlhttp.open("POST", window.location.pathname + "/insert", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("&qid="+ qid + "&name=" + name + "&description=" + description + "&cid=" + cid + "&_csrf=" + csrf);
}

function insertStateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            success.setAttribute("class", "text-success");
            success.innerHTML = "增加题目成功";
        } else if (xmlhttp.status == 409) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "题号已存在";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}

function updateQuestion() {
    var qid = document.getElementById("qid").value;
    var name = document.getElementById("name").value;
    var description = document.getElementById("description").value;
    var cid = document.getElementById("cid").value;
    var csrf = document.getElementsByName("_csrf")[0].value;

    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = updateStateChange;
    xmlhttp.open("POST", window.location.pathname + "/update", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("&qid="+ qid + "&name=" + name + "&description=" + description + "&cid=" + cid + "&_csrf=" + csrf);
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

function deleteQuestion() {
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
//          window.location.href = window.location.href.substring(0,window.location.href.lastIndexOf("testManager") + 11) + "/questions";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}