<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ page isELIgnored="false" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Edit User</title>

    <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/style.css">
     <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	 <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
</head>

<body>

<header>

    <h2>
        <a href="${pageContext.request.contextPath}/"> Simple web project
        </a>
    </h2>

    <nav>
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/register"> Register </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/displayUsers">  Display Users </a>
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
<h1>Edit User</h1>



    <form:form cssClass="register_form" modelAttribute="userRequest" action="${pageContext.request.contextPath}/edit/${userId}" method="post">

     
            <label for="name">Name:</label>

            <form:input
                path="name"
                id="name"
                type="text"
                required="required"
                minlength="3"
                maxlength="30"
                pattern="[A-Za-z]{3,30}" />

            
     
            <label for="surname">Surname:</label>

            <form:input
                path="surname"
                id="surname"
                type="text"
                required="required"
                minlength="3"
                maxlength="30"
                pattern="[A-Za-z]{3,30}" />

               
      
            <label for="gender">Gender:</label>

            <form:select path="gender" id="gender" required="required">
                <form:option value="" label="Select gender" />
                <form:option value="Male" label="Male" />
                <form:option value="Female" label="Female" />   
            </form:select>

           
        
         <label for="datepicker"> <b>Birth date:*</b> </label>

  			 <form:input
			        path="birthdate"
			        id="datepicker"
			        type="date"
			        required="required"
			        onfocus="this.max=new Date().toISOString().split('T')[0]"
			        oninvalid="this.setCustomValidity('Please Enter Your Birthdate')"
			        oninput="this.setCustomValidity('')" />
			        
         

            <label for="homeAddressName">Home Address:</label>

            <form:input
                path="homeAddress.name"
                id="homeAddressName"
                type="text"
                required="required"
                pattern="[\p{L}\p{N} ]+" 
                oninvalid="this.setCustomValidity('Work address is required and must contain only letters, numbers and spaces')"
   				oninput="this.setCustomValidity('')"/>

        
            <label for="homePostcode">Home Postcode:</label>

            <form:input
                path="homeAddress.postcode"
                id="homePostcode"
                type="text"
                required="required"
                pattern="[0-9]{5}"
                maxlength="5"
                inputmode="numeric"
                oninvalid="this.setCustomValidity('Postcode must contain exactly 5 digits')"
    			oninput="this.setCustomValidity('')" />

        
            <label for="workAddressName">Work Address:</label>

            <form:input
                path="workAddress.name"
                id="workAddressName"
                type="text"
                required="required"
                pattern="[\p{L}\p{N} ]+"
                oninvalid="this.setCustomValidity('Please enter your home address')"
   	    		oninput="this.setCustomValidity('')" />

      
            <label for="workPostcode">Work Postcode:</label>

            <form:input
                path="workAddress.postcode"
                id="workPostcode"
                type="text"
                required="required"
                pattern="[0-9]{5}"
                maxlength="5"
                inputmode="numeric"
                oninvalid="this.setCustomValidity('Postcode must contain exactly 5 digits')"
    			oninput="this.setCustomValidity('')" />

			<form:errors path="name" cssClass="error" />
			<form:errors path="surname" cssClass="error" />
			<form:errors path="gender" cssClass="error" />
			<form:errors path="birthdate" cssClass="error" />
			<form:errors path="homeAddress.name" cssClass="error" />
			<form:errors path="homeAddress.postcode" cssClass="error" />
			<form:errors path="workAddress.name" cssClass="error" />
            <form:errors path="workAddress.postcode" cssClass="error" />

           

        <div class="submit_buttom_container">
            <button type="submit">Update User</button>
        </div>

    </form:form>



</body>
</html>