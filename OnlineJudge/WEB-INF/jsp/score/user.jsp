<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/header.jspf" %>
    <title>成绩</title>
</head>
<body>
<%@include file="../common/navbar.jspf" %>
<div class="container">
    <div class="page-header">
        <h1>成绩</h1>
    </div>
    <p>排名：${sumScoreAndRank.rank}</p>
    <p>每题最大准确率平均值：${String.format("%.2f", sumScoreAndRank.sumCorrectRate / fn:length(allScores))}</p>
    <table class="table .table-striped">
        <tr>
            <th>题目</th>
            <th>最高准确率</th>
        </tr>
        <c:forEach var="score" items="${allScores}">
            <tr>
                <td>${score.qid}</td>
                <td>${String.format("%.2f",score.correctRate)}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="../common/footer.jspf" %>
</body>
</html>