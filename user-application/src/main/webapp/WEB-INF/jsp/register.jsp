<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ page isELIgnored="false" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Register New User</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
          
         <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
		<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
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

<script>
  $( function() {
	    $( "#datepicker" ).datepicker({
	      dateFormat: "yy-mm-dd",
	      changeMonth: true,
	      changeYear: true,
	      showButtonPanel: false,
          maxDate: new Date(),
          yearRange: "-100:+0",
          onSelect: function(dateText) {
              this.setCustomValidity("");
          }
          
        	 
	    });
	 
  } );
 </script>
 
 
 <script> 
 $(function () {
    $('#datepicker').datepicker().focus(function () {
        $(".ui-datepicker-next").hide();
        $(".ui-datepicker-prev").hide();
    });
    
});
</script>

<body>

<h1>Register Form</h1>

<form:form cssClass="register_form" modelAttribute="userRequest" name="myform" action="${pageContext.request.contextPath}/register" method="post">

    <!-- NAME -->

    <label for="name"> <b>Name:*</b> </label>

    <form:input
        path="name"
        id="name"
        type="text"
        placeholder="Enter your name"
        required="required"
        minlength="3"
        maxlength="30"
        pattern="[A-Za-z]{3,30}"
        oninvalid="this.setCustomValidity('Please Enter Your Name (Only Characters Allowed, between 3-30 characters)')"
        oninput="this.setCustomValidity('')" />

    <form:errors path="name" cssClass="error" />


    <!-- SURNAME -->

    <label for="surname">
        <b>Surname:*</b>
    </label>

    <form:input
        path="surname"
        id="surname"
        type="text"
        placeholder="Enter your surname"
        required="required"
        minlength="3"
        maxlength="30"
        pattern="[A-Za-z]{3,30}"
        oninvalid="this.setCustomValidity('Please Enter Your Surname (Only Characters Allowed, between 3-30 characters)')"
        oninput="this.setCustomValidity('')" />


    <!-- GENDER -->

    <label for="gender"> <b>Gender:*</b> </label>

    <form:select
        path="gender"
        id="gender"
        required="required"
        oninvalid="this.setCustomValidity('Please Enter Your Gender')"
        onchange="this.setCustomValidity('')">

        <form:option value="" label="Select gender" />
        <form:option value="Male" label="Male" />
        <form:option value="Female" label="Female" />

    </form:select>

   


    <!-- BIRTHDATE -->

    <label for="datepicker"> <b>Birth date:*</b> </label>

    <form:input
        path="birthdate"
        id="datepicker"
        type="date"
        required="required"
        onfocus="this.max=new Date().toISOString().split('T')[0]"
        oninvalid="this.setCustomValidity('Please Enter Your Birthdate')"
        oninput="this.setCustomValidity('')" />

    

    <!-- WORK ADDRESS -->

    <label for="workAddressName"> <b>Work Address:*</b> </label>

    <form:textarea
        path="workAddress.name"
        id="workAddressName"
        required="required"
        placeholder="Enter your work address"
        rows="4"
        cols="50" 
        oninvalid="this.setCustomValidity('Work address is required and must contain only letters, numbers and spaces')"
   		oninput="this.setCustomValidity('')"/>

   


    <!-- WORK POSTCODE -->

    <label for="workPostcode"> <b>Work Post-Code:</b> </label>

    <form:textarea
        path="workAddress.postcode"
        id="workPostcode"
        required="required"
        placeholder="Enter your work postcode"
        rows="4"
        cols="50" 
        minlength="5"
        maxlength="5"
        inputmode="numeric"
        oninvalid="this.setCustomValidity('Postcode must contain exactly 5 digits')"
    	oninput="this.setCustomValidity('')" />

    

    <!-- HOME ADDRESS -->

    <label for="homeAddressName"> <b>Home Address:</b> </label>

    <form:textarea
        path="homeAddress.name"
        id="homeAddressName"
        required="required"
        placeholder="Enter your home address"
        rows="4"
        cols="50"
        oninvalid="this.setCustomValidity('Please enter your home address')"
   	    oninput="this.setCustomValidity('')" />

    


    <!-- HOME POSTCODE -->

    <label for="homePostcode"> <b>Home Post-Code:</b> </label>

    <form:textarea
        path="homeAddress.postcode"
        id="homePostcode"
        required="required"
        placeholder="Enter your home postcode"
        rows="4"
        cols="50"
        minlength="5"
        maxlength="5"
        inputmode="numeric"
        oninvalid="this.setCustomValidity('Postcode must contain exactly 5 digits')"
    	oninput="this.setCustomValidity('')"  />
	
	
	<form:errors path="name" cssClass="error" />
	<form:errors path="surname" cssClass="error" />
	<form:errors path="gender" cssClass="error" />
	<form:errors path="birthdate" cssClass="error" />
	<form:errors path="workAddress.name" cssClass="error" />
	<form:errors path="workAddress.postcode" cssClass="error" />
	<form:errors path="homeAddress.name" cssClass="error" />
    <form:errors path="homeAddress.postcode" cssClass="error" />


    <!-- SUBMIT -->

    <div class="submit_buttom_container">

        <input
            type="submit"
            class="submit"
            value="Submit" />

    </div>

</form:form>

</body>
</html>