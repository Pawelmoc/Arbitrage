<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <a href="/arbitrage">Arbitrage</a>
    <h2>Add User</h2>

        <form:form method="post"
                   modelAttribute="user">
            First Name<br>
            <form:input path="firstName"/><br>
            <form:errors path="firstName" element="div"/>

            Last Name<br>
            <form:input path="lastName"/><br>
            <form:errors path="lastName" element="div"/>

            Email:<br>
            <form:input path="email"/><br>
            <form:errors path="email" element="div"/><br>

            <input type="submit" value="Save">
        </form:form>


</div>
</body>
</html>