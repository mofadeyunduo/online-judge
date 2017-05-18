function isEmailValid() {
    if (isNullOrEmpty("email") || !checkInputPattern("^.+@.+$", "email")) return false;
    return true;
}

function showEmailInputSuggestion() {
    var emailError = document.getElementById("emailError");
    if (isNullOrEmpty("email") == true) emailError.innerHTML = "邮箱为空";
    else if (checkInputPattern("^.+@[^@]+$", "email") == false) emailError.innerHTML = "邮箱为格式有误";
    else emailError.innerHTML = "";
}

function isPasswordValid() {
    if (isNullOrEmpty("password") || !checkInputPattern("^[\\w\\d]{6,20}$", "password")) return false;
    else return true;
}

function showPasswordInputSuggestion() {
    var passwordError = document.getElementById("passwordError");
    if (isNullOrEmpty("password") == true) passwordError.innerHTML = "密码为空";
    else if (checkInputPattern("^[\\w\\d]{6,20}$", "password") == false) passwordError.innerHTML = "密码过短或过长，或者包含非字母数字";
    else passwordError.innerHTML = "";
}

function isRepasswordValid() {
    if (isNullOrEmpty("repassword") || !checkIfPasswordIsSame()) return false;
    else return true;
}

function showRepasswordInputSuggestion() {
    var repasswordError = document.getElementById("repasswordError");
    if (isNullOrEmpty("repassword") == true) repasswordError.innerHTML = "请确认密码";
    else if (checkIfPasswordIsSame() == false) repasswordError.innerHTML = "密码不一致";
    else repasswordError.innerHTML = "";
}

function showAllPasswordSuggestion() {
    showPasswordInputSuggestion();
    showRepasswordInputSuggestion()
}

function checkIfPasswordIsSame() {
    if (document.getElementById("password").value == document.getElementById("repassword").value) return true;
    else return false;
}

function isNullOrEmpty(id) {
    var idDocument = document.getElementById(id);
    if (idDocument.value == "") return true;
    else return false;
}

function checkInputPattern(regexString, id) {
    var regexp = new RegExp(regexString);
    return regexp.test(document.getElementById(id).value);
}

function getContextPath() {
    var pathName = window.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0, index + 1);
    return result;
}