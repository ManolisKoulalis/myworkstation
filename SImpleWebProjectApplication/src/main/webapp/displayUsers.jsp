<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		
		<title>Display Users of Web Application</title>
		<style><%@include file="WEB-INF/css/style.css"%></style>
	</head>

<body>
	
<header>
    <h2 ><a href="homepage.jsp">Simple web project</a></h2>
    
<nav>
   <ul>
    <li> <a href="register.jsp" >Register </a></li> 
    <li> <a href="displayUsers">Display Users</a></li>
   </ul>
</nav>

</header>




	<div class="listofusers_container">
<table width="400px">
  
    <caption><strong>LIST OF USERS</strong> </caption>

    <thead>
<tr>
    <th>Name</th>
    <th>Surname</th>
    <th colspan="2">Actions</th>
   
</tr>
</thead>




				<tbody>
					
					<c:forEach var="user" items="${userlist}">

						<tr>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.surname}" /></td>
								
							<td> <a  id="href_delete" href="delete?id=<c:out value='${user.id}' />"><b>Delete</b></a></td>
       						<td> <a id="href_details" href="details?id=<c:out value='${user.id}' />"><b>Details</b></a> </td>
								
							
						</tr>
					</c:forEach>
					
				</tbody>

			</table>

	</div>







</body>
</html>