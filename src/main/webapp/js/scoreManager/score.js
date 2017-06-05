function satisfySubmit() {
    var submit = document.getElementById("submit");
    if (isUsualScoreValid()) {
        submit.setAttribute("value", "提交修改过的成绩");
        submit.setAttribute("class", "btn btn-success");
        submit.removeAttribute("disabled")
    } else {
        submit.setAttribute("value", "不能提交，检查成绩输入是否正确");
        submit.setAttribute("class", "btn btn-danger");
        submit.setAttribute("disabled", "");
    }
}

function isNullOrEmpty(id) {
    var idDocument = document.getElementById(id);
    return idDocument.value == "";
}

function checkInputPattern(regexString, id) {
    var regexp = new RegExp(regexString);
    return regexp.test(document.getElementById(id).value);
}

function isUsualScoreValid() {
    return !(isNullOrEmpty("usualScore") || !checkInputPattern("^\\d{1,2}(\\.\\d{1,2})?$", "usualScore"));
}

function showUsualScoreInputSuggestion() {
    var emailError = document.getElementById("usualScoreError");
    if (isNullOrEmpty("usualScore") == true) emailError.innerHTML = "成绩为空";
    else if (checkInputPattern("^\\d{1,2}(\\.\\d{1,2})?$", "usualScore") == false) emailError.innerHTML = "成绩限制在两位小数和0-100分之间";
    else emailError.innerHTML = "";
}

function updateUsualScore() {
    var usualScore = document.getElementById("usualScore").value;
    var csrf = document.getElementsByName("_csrf")[0].value;

    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = selectStateChange;
    xmlhttp.open("POST", window.location.pathname, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("&_csrf=" + csrf + "&usualScore=" + usualScore);
}

function selectStateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            success.setAttribute("class", "text-success");
            success.innerHTML = "更新成功";
        } else if (xmlhttp.status == 404) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "该用户成绩不存在";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}