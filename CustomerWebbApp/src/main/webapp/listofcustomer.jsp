<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Web Application</title>
<style><%@include file="WEB-INF/css/style.css"%></style>
</head>
<body>



<header>
    <h2 id="left_h2"><a href="index.jsp"> Test project for customer</a> </h2>
    <nav>
      
        <ul>
            <li><a href="index.jsp"> Αρχική</a></li>
            <li> <a href="register">Εγγραφή Πελάτη</a> </li>
            <li> <a href="loginpae">Σύνδεση Πελάτη</a></li>
        </ul>
    </nav>
 </header>




<div class="details_table_container">


<table>

   <caption><strong>Λίστα Πελατών</strong></caption>
      <thead>
          <tr>
             <th>Ονομα</th>
             <th>Eπώνυμο</th>
          </tr>
      </thead>

      <tbody>
      
      	<c:forEach items="${customerlist}" var="customer" >
      
        <tr>
        
          <td><c:out value="${customer.name}" /></td>
          <td><c:out value="${customer.surname}" /></td>
        </tr>
        
        </c:forEach>
        
      </tbody>


</table>

</div>

</body>
</html>