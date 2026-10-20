<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    



<!DOCTYPE html>
<html>
<head>
<title>Simple Web Application</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>




<header>
    <h2>
        <a href="${pageContext.request.contextPath}/"> Simple web project</a>
    </h2>

    <nav>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/register">Register</a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/displayUsers">Display Users</a>
            </li>
        </ul>
    </nav>
</header>


<h1>Welcome to Simple Web Application</h1>


<hr>


</body>
</html>