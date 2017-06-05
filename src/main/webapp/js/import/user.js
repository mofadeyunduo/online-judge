function satisfySubmit() {
    var submit = document.getElementById("submit");
    if (!isNullOrEmpty("usersFile") && checkInputPattern("^.+\\.(xls|xlsx)$","usersFile")) {
        submit.setAttribute("value", "上传");
        submit.setAttribute("class", "btn btn-success");
        submit.removeAttribute("disabled")
    } else {
        submit.setAttribute("value", "不能上传，请选择文件或者检查文件格式");
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