<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>

 <title>Details of Users</title>
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



<div class="details_table_container">
<table>
    <caption><strong>Details of Users</strong></caption>
<thead>
  <tr>
    <th> Name</th>
    <th>Surname</th>
    <th>Gender</th>
    <th>Birth-date</TH>
    <th>Work Address</th>
    <th>Work Post-Codeς</th>
    <th>Home Address</th>
    <th>Home Post-code</th>
    
    
  </tr>
</thead>

<tbody>

					

						<tr>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.surname}" /></td>
							<td><c:out value="${user.gender}" /></td>
							<td><c:out value="${user.birthdate}" /></td>
							<td><c:out value="${workAddress.name}" /></td>
							<td><c:out value="${workAddress.postcode}" /></td>
							<td><c:out value="${homeAddress.name}" /></td>
							<td><c:out value="${homeAddress.postcode}" /></td>
						
						</tr>
				
					
				</tbody>

			</table>

	</div>













</body>
</html>