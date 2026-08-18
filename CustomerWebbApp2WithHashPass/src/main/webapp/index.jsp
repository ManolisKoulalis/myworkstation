<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>
 <%@ page isELIgnored="false" %>
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
            <li> <a href="${pageContext.request.contextPath}/register">Εγγραφή Πελάτη</a> </li>
            <li> <a href="${pageContext.request.contextPath}/loginpage">Σύνδεση Πελάτη</a></li>
        </ul>
    </nav>
 </header>
 
 
    <h1 class="dotted">Welcome to Test Project for Customer</h1>


  
<div id="container_first_page">
  <div id="createCustomer"><a id="href_createCustomer" href="${pageContext.request.contextPath}/register"><b>Εγγραφή Πελάτη</b></a> </div>

  <div id="loginCustomer"><a id="href_loginCustomer" href="${pageContext.request.contextPath}/loginpage"><b>Σύνδεση Πελάτη</b></a> </div>
  
  <div id="listofCustomer"><a id="href_listofCustomer" href="${pageContext.request.contextPath}/displayCustomers"><b>Λίστα Πελάτων</b></a></div>

 
</div>








</body>
</html>