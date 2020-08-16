<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script>
        function confirmDelete(id) {
            if (confirm("Are you sure you want to delete?")) {
                window.location.href = "/user/delete/" + id;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <a href="/user/add">Back</a>
    <h2>Users</h2>
    <div class="table-responsive">
        <table>
            <table class="table">
                <thead>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Email</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>
                        <a href="#" onclick="confirmDelete(${user.id})">Delete</a>
                        <a href="/user/update/${user.id}">Edit</a>
                    </td>
                </tr>
                </tbody>
                </c:forEach>
            </table>
    </div>
</div>
</body>
</html>