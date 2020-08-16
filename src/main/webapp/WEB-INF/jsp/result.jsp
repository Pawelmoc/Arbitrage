<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Arbitrage</h2>
    <p>${time}</p>
    <div class="table-responsive">
        <table>
            <table class="table">
                <thead>
                <tr>
                    <th>Symbol</th>
                    <th>Buy Exchange</th>
                    <th>Buy Price USD</th>
                    <th>Sell Exchange</th>
                    <th>Sell Price USD</th>
                    <th>Profit %</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="resultLine" items="${resultLines}">
                <tr>
                    <td>${resultLine.symbol}</td>
                    <td>${resultLine.buyer.exchange}</td>
                    <td>${resultLine.buyer.price}</td>
                    <td>${resultLine.seller.exchange}</td>
                    <td>${resultLine.seller.price}</td>
                    <td><font color="green">${resultLine.profit}</td>
                </tr>
                </tbody>
                </c:forEach>
            </table>
    </div>
</div>
</body>
</html>