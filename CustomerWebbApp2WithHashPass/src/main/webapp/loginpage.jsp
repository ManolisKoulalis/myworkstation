<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
 <%@ page isELIgnored="false" %>
<meta charset="UTF-8">
<title>Log In Page</title>
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

<c:if test="${not empty message}">
  <div style="color:red;">
    <c:out value="${message}" />
</div>
</c:if>

<h1 class="dotted">Σύνδεση Πελάτη</h1>
 <form class="login_form" name="login_form" action="loginCustomer" method="post">

	<label for="username">  <b>Username:*</b></label>
        <input type="text" placeholder="Τοποθετήστε το username" name="username" id="username"   maxlength="30" pattern=".{4,30}" required  oninvalid="this.setCustomValidity('Please Enter Your Username')" oninput="this.setCustomValidity('')">


   <label for="password">  <b>Password:*</b> </label>
        <input type="password" placeholder="Τοποθετήστε το password" name="password" id="password" required  maxlength="30" pattern=".{8,30}"  oninvalid="this.setCustomValidity('Please Enter Your Password')"oninput="this.setCustomValidity('')">

  <input type="submit" class="submit" value="Login">
</form>





</body>
</html>