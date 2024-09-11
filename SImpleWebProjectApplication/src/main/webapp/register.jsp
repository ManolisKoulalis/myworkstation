<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<title>Register New User</title>

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

				
	<h1>Register Form</h1>


<p class="error">${message}</p>    
<form class="register_form" name="myform" action="<%=request.getContextPath()%>/insert" method="post" >

<label for="name">  <b>Name:*</b>    </label> 
<input type="text" placeholder="Enter your name" name="name" id="name" required maxlength="30" pattern="[A-Za-z]{3,30}" oninvalid="this.setCustomValidity('Please Enter Your Name')">
                
<label for="surname"><b>Surname:*</b></label>
<input type="text" placeholder="Enter your surname" name="surname" id="surname" required  maxlength="30" pattern="[A-Za-z]{3,30}" oninvalid="this.setCustomValidity('Please Enter Your Surname')" oninput="setCustomValidity('')">

<label for="gender"><b>Gender:*</b></label>
<select name="gender" id="gender" required oninvalid="this.setCustomValidity('Please Enter Your Gender')" oninput="setCustomValidity('')">
  <option></option>
  <option value="Male">Male</option>
  <option value="Female">Female</option>
</select>
                
<label for="datepicker"><b>Birthdate:*</b></label>
<input type="date"  name="birthdate" id="datepicker" onfocus="this.max=new Date().toISOString().split('T')[0]" oninvalid="this.setCustomValidity('Please Enter Your Birthdate')" oninput="setCustomValidity('')" required>
                
<label for="workAddressName"><b>Work Address:</b></label>
	<textarea placeholder="Enter your work address" name="workAddressName" id="workAddressName" rows="4" cols="50"> </textarea>
                
<label for="workPostcode"><b>Work Post-Code:</b></label>
    <textarea placeholder="Enter your work address" name="workPostcode" id="workPostcode" rows="4" cols="50"> </textarea>
                
<label for="homeAddressName"><b>Home Address:</b></label>
    <textarea placeholder="Enter your home address" name="homeAddressName" id="homeAddressName" rows="4" cols="50"> </textarea>	
                
 <label for="homePostcode"><b>Home Post-code:</b></label>
	 <textarea placeholder="Enter your home address" name="homePostcode" id="homePostcode" rows="4" cols="50"> </textarea>		 	  
                 
<div class="submit_buttom_container">
<input type="submit" class="submit" value="Submit">
</div>

</form>
		




</body>
</html>