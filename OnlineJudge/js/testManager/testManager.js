function isNullOrEmpty(id) {
    var idDocument = document.getElementById(id);
    if (idDocument.value == "") return true;
    else return false;
}

function checkInputPattern(regexString, id) {
    var regexp = new RegExp(regexString);
    return regexp.test(document.getElementById(id).value);
}