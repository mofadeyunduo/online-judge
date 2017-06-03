<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>注册</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>注册</h1>
    </div>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="row">
        <div class="col-md-6">
            <div class="input-group" oninput="satisfySubmit()">
                <span class="input-group-addon" id="basic-addon1">邮箱</span>
                <input type="email" id="email" name="email" class="form-control" placeholder="邮箱"
                       aria-describedby="basic-addon1" maxlength="40" required oninput="showEmailInputSuggestion()">
            </div>
        </div>
        <div class="col-md-6">
            <p id="emailError" class="text-danger"></p>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-6">
            <input class="btn btn-danger" id="submit" name="submit" type="button" value="不能验证，检查邮箱格式" disabled
                   onclick="forgetPasswordCheck()"/>
        </div>
    </div>
    <br>
    <p id="success"></p>
    </form>
</div>
<%@include file="../common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/js/user/user.js"></script>
<script src="${pageContext.request.contextPath}/js/user/registerEmail.js"></script>
</body>
</html>