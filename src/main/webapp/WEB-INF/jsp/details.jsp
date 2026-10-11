<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page isELIgnored="false" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>User Details</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<header>

    <h2>
        <a href="${pageContext.request.contextPath}/">Simple web project</a>
    </h2>

    <nav>
        <ul>

            <li>
                <a href="${pageContext.request.contextPath}/register"> Register</a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/displayUsers"> Display Users</a>
            </li>

        </ul>
    </nav>

</header>


<h1>User Details</h1>


<div class="details_table_container">

    <table>

        <tr>
            <th>ID</th>
            <td>${user.id}</td>
        </tr>

        <tr>
            <th>Name</th>
            <td>${user.name}</td>
        </tr>

        <tr>
            <th>Surname</th>
            <td>${user.surname}</td>
        </tr>

        <tr>
            <th>Gender</th>
            <td>${user.gender}</td>
        </tr>

        <tr>
            <th>Birthdate</th>
            <td>${user.birthdate}</td>
        </tr>

    </table>

</div>


</body>

</html>