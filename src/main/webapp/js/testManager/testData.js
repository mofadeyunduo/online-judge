function satisfySubmit() {
    var submit = document.getElementById("updateButton");
    if (submit == null) submit = document.getElementById("insertButton");
    if (checkInputPatternByLine("^(.+\\$.+)*$", "inputOutputs")) {
        submit.innerHTML = "删除现有的测试用例，增加新的测试用例";
        submit.setAttribute("class", "btn btn-success");
        submit.removeAttribute("disabled")
    } else {
        submit.innerHTML = "不能更改测试用例，检查输入格式满足要求";
        submit.setAttribute("class", "btn btn-danger");
        submit.setAttribute("disabled", "");
    }
}

function checkInputPatternByLine(regexString, id) {
    var splits = document.getElementById(id).value.split("\n");
    for (var i = 0; i < splits.length; i++) {
        if(splits[i] == "") continue;
        var regexp = new RegExp(regexString);
        if (regexp.test(splits[i]) == false) return false;
    }
    return true;
}

function importTestData() {
    var inputOutputs = document.getElementById("inputOutputs").value;
    var csrf = document.getElementsByName("_csrf")[0].value;

    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = importTestDataStateChange;
    xmlhttp.open("POST", window.location.pathname, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("&inputOutputs=" + inputOutputs + "&_csrf=" + csrf);
}

function importTestDataStateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            success.setAttribute("class", "text-success");
            success.innerHTML = "更新测试用例成功";
            alert("更新测试用例成功");
            location.reload();
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}
