<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%@ page isELIgnored="false" %>

<!DOCTYPE html>

<html>

<head>
    <meta charset="UTF-8">
    <title>Display Users</title>
    <link rel="stylesheet"href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

<header>

    <h2>
        <a href="${pageContext.request.contextPath}/">Simple web project</a>
    </h2>

    <nav>

        <ul>

            <li>
                <a href="${pageContext.request.contextPath}/register"> Register </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/displayUsers"> Display Users </a>
            </li>

        </ul>

    </nav>

</header>


<h1>List of Users</h1>


<div class="listofusers_container">

    <table>

        <thead>

            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Details</th>
                <th>Delete</th>
            </tr>

        </thead>


        <tbody>

            <c:forEach var="user" items="${userlist}">

                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td><a id="href_details"href="${pageContext.request.contextPath}/details/${user.id}">Details </a></td>
                    <-- χρησιμοποιω form καθως θελω το post method και οχι το get που μου προσφερει -->
                    <td> <form action="${pageContext.request.contextPath}/delete/${user.id}" method="post">
							<button type="submit" id="href_delete">Delete</button>
						</form> 
					</td>
                </tr>

            </c:forEach>

        </tbody>

    </table>

</div>


</body>

</html>