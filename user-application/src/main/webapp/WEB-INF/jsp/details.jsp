<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
            <td><c:out value="${user.id}" /></td>
        </tr>

        <tr>
            <th>Name</th>
            <td> <c:out value="${user.name}" /></td>
        </tr>

        <tr>
            <th>Surname</th>
            <td> <c:out value="${user.surname}" /></td>
        </tr>

        <tr>
            <th>Gender</th>
            <td> <c:out value="${user.gender}" /></td>
        </tr>

        <tr>
            <th>Birthdate</th>
            <td> <c:out value="${user.birthdate}" /></td>
        </tr>

		<tr>
            <th>Home address</th>
            <td><c:out value="${user.homeAddress.name}" /></td>
        </tr>
        
        <tr>
            <th>Home postcode</th>
            <td><c:out value="${user.homeAddress.postcode}" /></td>
        </tr>
        
        <tr>
            <th>Work address</th>
            <td><c:out value="${user.workAddress.name}" /></td>
        </tr>
        
        <tr>
            <th>Work postcode</th>
            <td><c:out value="${user.workAddress.postcode}" /></td>
        </tr>
         
        <tr>
        <th>Acions</th>
        <td><a href="${pageContext.request.contextPath}/edit/${user.id}">  Edit User </a></td>
        </tr>

    </table>
   

</div>


</body>

</html>