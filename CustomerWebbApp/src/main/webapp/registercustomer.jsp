<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Εγγραφή Νέου Πελάτη</title>
<style><%@include file="WEB-INF/css/style.css"%></style>
</head>


<body>




<header>
    <h2 id="left_h2"><a href="index.jsp"> Test project for customer</a> </h2>
    <nav>
      
        <ul>
            <li><a href="index.jsp"> Αρχική</a></li>
            <li> <a href="register">Εγγραφή Πελάτη</a> </li>
            <li> <a href="loginpage">Σύνδεση Πελάτη</a></li>
        </ul>
    </nav>
 </header>

<p class="error">${message}</p>    

 <h1 class="dotted">
 	<c:if test="${customer != null}">Επεξεργασια Πελατη</c:if>
	<c:if test="${customer == null}">Προσθήκη Νέου Πελάτη</c:if>
 </h1>


	<!-- σε περιπτωση που δεν εκανα τον ελεγχο μεσα στο φορμ θα επρεπε να διαχωρισω τον κωδικα ετσι
  	<c:if test="${customer != null}"> <form class="register_form" name="myform" action="${pageContext.request.contextPath}/update" method="post" ></c:if>
	<c:if test="${customer == null}"> <form class="register_form" name="myform" action="${pageContext.request.contextPath}/insert" method="post" ></c:if> 
    -->
    <!-- Επισης χρησιμοποιω το pageContext request contextPath προκειμενου να παιρνω κατευθειαν το μονοπατι 
     /CustomerWebApp και να μην χρειαζεται να το βαζω καθε φορα εγω καθως αν αλλαξω ονομα το προτζεκτ 
     θα θελει παντου αλλαγη, επισης αν δεν το χρησιμοποιησω και γυρισω κατευθειαν το /insert δεν θα παιρνω 
     το σωστο path και αθ βγαινει εκτος εφαρμογης, τελος ειναι καθαρο jstl και δεν χρησιμοποιουμε καθολυ java -->
    
      <form class="register_form" name="myform" action="${pageContext.request.contextPath}/${customer == null ? 'insert' : 'update'}" method="post">
      
        <label for="name">  <b>Όνομα:*</b>    </label> 
         <input type="text"   value="${customer != null ? customer.name : ''}" placeholder="Τοποθετήστε το όνομα" name="name" id="name" required maxlength="30" pattern="[A-Za-z]{3,30}"  oninvalid="this.setCustomValidity('Please Enter Your Name(Only Characters Allowed, between 3-30 characters)')" oninput="this.setCustomValidity('')">
                        
        <label for="surname"><b>Επίθετο:*</b></label>
         <input type="text"   value="${customer != null ? customer.surname : ''}"  placeholder="Τοποθετήστε το επίθετο" name="surname" id="surname" required maxlength="30" pattern="[A-Za-z]{3,30}" oninvalid="this.setCustomValidity('Please Enter Your Surname(Only Characters Allowed, between 3-30 characters)')" oninput="this.setCustomValidity('')">
        
        <label for="gender"><b>Φύλο:*</b></label>
         <select name="gender"  id="gender" required oninvalid="this.setCustomValidity('Please Enter Your Gender')" oninput="this.setCustomValidity('')">
          <option></option>
          <option value="Male" ${customer != null && customer.gender == 'Male' ? 'selected' : ''}>Male</option>
		  <option value="Female"  ${customer != null && customer.gender == 'Female' ? 'selected' : ''}>Female</option>
         </select>
                        
        <label for="datepicker"><b>Ημερομηνία Γέννησης:*</b></label>
        <input type="Date" value="${customer != null ? customer.birthdate : ''}" onkeypress="return false;" name="birthdate" id="datepicker" max="today" oninvalid="this.setCustomValidity('Please Enter Your Birthdate')" oninput="this.setCustomValidity('')" required>
                        
       <label for="afm"><b>ΑΦΜ:*</b></label>
       <input type="text" value="${customer != null ? customer.afm : ''}" placeholder="Τοποθετήστε το ΑΦΜ " name="afm"  id="afm" required maxlength="9" pattern="[0-9]{9}" oninvalid="this.setCustomValidity('Please Enter Your AFM(Exactly 9 Digits)')">
        
        <!-- χρησιμοποιω τον ελεγχο προκειμενου οταν εχω nested object να μην υπαρχει η περιπτωση για silent 
    bug πχ αν καταλαθος ειχα κανει στο back end customer.setWorkAddress(null) απλα φαινεται κενο χωρις
     να δειχνει το bug επισης δεν καταλαβαινεις αν λειπει data καταλαθος καθως φαινεται κενο αλλα δεν β
     γαζει ερρορ επισης μεχρι και λαθος field name να βαλουμε και να μην καλουμε το σωστο του entity θα
      φαινεται κενο χωρις να δειχνει το τι φταει-->
        <label for="workAddressName"><b>Διεύθυνση Εργασίας:</b></label>
        <textarea placeholder="Διευθυνση Εργασίας" name="workAddressName" id="workAddressName" required rows="4" cols="50">${customer != null && customer.workAddress != null ? customer.workAddress.name : ''}</textarea>

        <label for="workPostcode"><b>ΤΚ Εργασίας:</b></label>
        <input type="text"  value="${customer != null && customer.workAddress != null ? customer.workAddress.postcode : ''}" name="workPostcode" id="workPostcode" required  pattern="[0-9]{5}"> 
         
         
        <label for="homeAddressName"><b>Διεύθυνση Κατοικίας:</b></label>
        <textarea placeholder="Διευθυνση Κατοικίας" name="homeAddressName" id="homeAddressName" required  rows="4" cols="50">${customer != null && customer.homeAddress != null? customer.homeAddress.name : ''}</textarea>
         
         <label for="homePostcode"><b>TK Σπιτιου:</b></label>
         <input type="text"  value="${customer != null && customer.homeAddress != null ? customer.homeAddress.postcode : ''}" placeholder="Enter your home address" name="homePostcode" id="homePostcode" required pattern="[0-9]{5}" > 
         
        <label for="moreInfo"><b>Λοιπές Πληροφορίες:</b></label>
        <textarea placeholder="Λοιπές Πληροφορίες" name="moreInfo" id="moreInfo" rows="4" cols="50">${customer != null ? customer.moreInfo : ''}</textarea>

        <label for="username">  <b>Username:*</b></label>
        <input type="text"  value="${customer != null ? customer.username : ''}" placeholder="Τοποθετήστε το username" name="username" id="username"   maxlength="40" pattern=".{4,30}" required  oninvalid="this.setCustomValidity('Please Enter Your Username')" oninput="this.setCustomValidity('')">

        <label for="password">  <b>Password:*</b> </label>
        <input type="password"   value="${customer != null ? customer.password : ''}" placeholder="Τοποθετήστε το password" name="password" id="password" required  maxlength="30" pattern=".{8,30}"  oninvalid="this.setCustomValidity('Please Enter Your Password')"oninput="this.setCustomValidity('')">

       
         <input type="submit" class="submit" value="Submit">
        
    </form>




</body>
</html>