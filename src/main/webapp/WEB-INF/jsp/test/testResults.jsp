<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>运行结果</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>运行结果</h1>
    </div>
    <p class="text">准确率：${String.format("%.2f", testInfo.correctRate)}</p>
    <p class="text">测评时间：${testInfo.submitTime}</p>
    代码：
    <pre>${testInfo.code}</pre>
    <table class="table .table-striped">
        <tr>
            <th>输入</th>
            <th>输出</th>
            <th>正确性</th>
        </tr>
        <c:forEach var="inputOutput" items="${testInfo.inputOutputs}">
            <tr>
                <td>${inputOutput.input}</td>
                <td>${inputOutput.output}</td>
                <td>${inputOutput.correct == true ? "正确" : "错误"}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</div>
<%@include file="../common/footer.jspf" %>
</body>
</html>