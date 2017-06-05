function selectQuestion() {
    var id = document.getElementById("id").value;
    var csrf = document.getElementsByName("_csrf")[0].value;

    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = selectStateChange;
    xmlhttp.open("POST", window.location.pathname, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("&id=" + id +  "&_csrf=" + csrf);
}

function selectStateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            success.setAttribute("class", "text-success");
            success.innerHTML = "查询成功";
            var interval = xmlhttp.responseText.split(" ");
            document.getElementById("interval90").innerHTML = interval[4];
            document.getElementById("interval80").innerHTML = interval[3];
            document.getElementById("interval70").innerHTML = interval[2];
            document.getElementById("interval60").innerHTML = interval[1];
            document.getElementById("interval0").innerHTML = interval[0];
        } else if (xmlhttp.status == 404) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "题目ID不存在";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}