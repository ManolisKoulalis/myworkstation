<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ page isELIgnored="false" %>

<%@ taglib prefix="form"
    uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Register New User</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
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

<h1>Register Form</h1>

<form:form cssClass="register_form" modelAttribute="userRequest" name="myform" action="${pageContext.request.contextPath}/register" method="post">

    <!-- NAME -->

    <label for="name"> <b>Name:*</b> </label>

    <form:input
        path="name"
        id="name"
        type="text"
        placeholder="Enter your name"
        required
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
        required
        minlength="3"
        maxlength="30"
        pattern="[A-Za-z]{3,30}"
        oninvalid="this.setCustomValidity('Please Enter Your Surname (Only Characters Allowed, between 3-30 characters)')"
        oninput="this.setCustomValidity('')" />

    <form:errors path="surname" cssClass="error" />


    <!-- GENDER -->

    <label for="gender"> <b>Gender:*</b> </label>

    <form:select
        path="gender"
        id="gender"
        required
        oninvalid="this.setCustomValidity('Please Enter Your Gender')"
        onchange="this.setCustomValidity('')">

        <form:option value="" label="Select gender" />
        <form:option value="Male" label="Male" />
        <form:option value="Female" label="Female" />

    </form:select>

    <form:errors path="gender" cssClass="error" />


    <!-- BIRTHDATE -->

    <label for="datepicker"> <b>Birth date:*</b> </label>

    <form:input
        path="birthdate"
        id="datepicker"
        type="date"
        required
        onfocus="this.max=new Date().toISOString().split('T')[0]"
        oninvalid="this.setCustomValidity('Please Enter Your Birthdate')"
        oninput="this.setCustomValidity('')" />

    <form:errors path="birthdate" cssClass="error" />


    <!-- WORK ADDRESS -->

    <label for="workAddressName"> <b>Work Address:*</b> </label>

    <form:textarea
        path="workAddress.name"
        id="workAddressName"
        required
        placeholder="Enter your work address"
        rows="4"
        cols="50" 
        oninvalid="this.setCustomValidity('Please enter your work address')"
   		oninput="this.setCustomValidity('')"/>

    <form:errors path="workAddress.name" cssClass="error" />


    <!-- WORK POSTCODE -->

    <label for="workPostcode"> <b>Work Post-Code:</b> </label>

    <form:textarea
        path="workAddress.postcode"
        id="workPostcode"
        required
        placeholder="Enter your work postcode"
        rows="4"
        cols="50" 
        oninvalid="this.setCustomValidity('Postcode must contain exactly 5 digits')"
    	oninput="this.setCustomValidity('')" />

    <form:errors path="workAddress.postcode" cssClass="error" />


    <!-- HOME ADDRESS -->

    <label for="homeAddressName"> <b>Home Address:</b> </label>

    <form:textarea
        path="homeAddress.name"
        id="homeAddressName"
        required
        placeholder="Enter your home address"
        rows="4"
        cols="50"
        oninvalid="this.setCustomValidity('Please enter your home address')"
   	    oninput="this.setCustomValidity('')" />

    <form:errors path="homeAddress.name" cssClass="error" />


    <!-- HOME POSTCODE -->

    <label for="homePostcode"> <b>Home Post-Code:</b> </label>

    <form:textarea
        path="homeAddress.postcode"
        id="homePostcode"
        required
        placeholder="Enter your home postcode"
        rows="4"
        cols="50"
        oninvalid="this.setCustomValidity('Postcode must contain exactly 5 digits')"
    	oninput="this.setCustomValidity('')" 
    	oninvalid="this.setCustomValidity('Postcode must contain exactly 5 digits')"
    	oninput="this.setCustomValidity('')"/>

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