function selectAllScores() {
    var id = document.getElementById("id").value;
    var csrf = document.getElementsByName("_csrf")[0].value;

    xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = selectStateChange;
    xmlhttp.open("POST", window.location.pathname, true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("&_csrf=" + csrf + "&id=" + id);
}

function selectStateChange() {
    var success = document.getElementById("success");
    if (xmlhttp.readyState == 4) { // 4 = "loaded"
        if (xmlhttp.status == 200) { // 200 = "OK"
            success.setAttribute("class", "text-success");
            success.innerHTML = "查询成功";
            var lines = xmlhttp.responseText.split("\n");
            var table = document.getElementById("table");
            while(table.rows.length > 1) {
                table.deleteRow(1);
            }
            for(var i = 0; i < lines.length - 1; i++) {
                var row = table.insertRow();
                var attributes = lines[i].split(";");
                row.insertCell(0).innerHTML = attributes[0];
                row.insertCell(1).innerHTML = attributes[1];
                row.insertCell(2).innerHTML = attributes[2];
                row.insertCell(3).innerHTML = attributes[3];
            }
        } else if (xmlhttp.status == 404) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "该题库不存在";
        } else if (xmlhttp.status == 500) {
            success.setAttribute("class", "text-danger");
            success.innerHTML = "服务器可能出现了问题";
        }
    }
}
