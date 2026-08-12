<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 



<!DOCTYPE html>
<html>
<head>
 <%@ page isELIgnored="false" %>
<meta charset="UTF-8">
<title>Εγγραφή Νέου Πελάτη</title>
<style><%@include file="WEB-INF/css/style.css"%></style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<link rel="stylesheet"href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
</head>


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
    <p class="error">${message}</p>
</c:if>
 

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
    
      <form class="register_form" name="myform" action="${pageContext.request.contextPath}/${customer == null ? 'insert' : 'update'}" method="post"  >
      
        <input type="hidden" name="customerId" value="${customer.id}">
      
        <label for="name">  <b>Όνομα:*</b>    </label> 
         <input type="text"   value="${customer != null ? customer.name : ''}" placeholder="Τοποθετήστε το όνομα" name="name" id="name" required maxlength="30" pattern="[A-Za-zΑ-Ωα-ωΆ-Ώά-ώ\s]{3,30}"  oninvalid="this.setCustomValidity('Please Enter Your Name(Only Characters Allowed, between 3-30 characters)')" oninput="this.setCustomValidity('')">
                        
        <label for="surname"><b>Επίθετο:*</b></label>
         <input type="text"   value="${customer != null ? customer.surname : ''}"  placeholder="Τοποθετήστε το επίθετο" name="surname" id="surname" required maxlength="30" pattern="[A-Za-zΑ-Ωα-ωΆ-Ώά-ώ\s]{2,30}" oninvalid="this.setCustomValidity('Please Enter Your Surname(Only Characters Allowed, between 3-30 characters)')" oninput="this.setCustomValidity('')">
        
        <label for="gender"><b>Φύλο:*</b></label>
         <select name="gender"  id="gender" required oninvalid="this.setCustomValidity('Please Enter Your Gender')" oninput="this.setCustomValidity('')">
          <option></option>
          <option value="Male" ${customer != null && customer.gender == 'Male' ? 'selected' : ''}>Male</option>
		  <option value="Female"  ${customer != null && customer.gender == 'Female' ? 'selected' : ''}>Female</option>
         </select>
                        
        <label for="datepicker"><b>Ημερομηνία Γέννησης:*</b></label>
        <input type="text" value="${customer != null ? customer.birthdate : ''}" onkeypress="return false;" name="birthdate" id="datepicker" max="today" oninvalid="this.setCustomValidity('Please Enter Your Birthdate')" oninput="this.setCustomValidity('')" required>
                        
       <label for="afm"><b>ΑΦΜ:*</b></label>
       <input type="text" value="${customer != null ? customer.afm : ''}" placeholder="Τοποθετήστε το ΑΦΜ " name="afm"  id="afm" required maxlength="9" pattern="[0-9]{9}" oninvalid="this.setCustomValidity('Please Enter Your AFM(Exactly 9 Digits)')" oninput="this.setCustomValidity('')">
        
        <!-- χρησιμοποιω τον ελεγχο προκειμενου οταν εχω nested object να μην υπαρχει η περιπτωση για silent 
    bug πχ αν καταλαθος ειχα κανει στο back end customer.setWorkAddress(null) απλα φαινεται κενο χωρις
     να δειχνει το bug επισης δεν καταλαβαινεις αν λειπει data καταλαθος καθως φαινεται κενο αλλα δεν β
     γαζει ερρορ επισης μεχρι και λαθος field name να βαλουμε και να μην καλουμε το σωστο του entity θα
      φαινεται κενο χωρις να δειχνει το τι φταει-->
        <label for="workAddressName"><b>Διεύθυνση Εργασίας:</b></label>
        <textarea placeholder="Διευθυνση Εργασίας" name="workAddressName" id="workAddressName" required oninvalid="this.setCustomValidity('Work address is required, Work Address characters should not exceed limit (255)')"rows="4" cols="50">${customer != null && customer.workAddress != null ? customer.workAddress.name : ''}</textarea>

        <label for="workPostcode"><b>ΤΚ Εργασίας:</b></label>
        <input type="text"  value="${customer != null && customer.workAddress != null ? customer.workAddress.postcode : ''}" name="workPostcode" id="workPostcode" placeholder="Τοποθετηστε το ΤΚ" oninvalid="this.setCustomValidity('Work postcode must contain exactly 5 digits')" required  pattern="[0-9]{5}"> 
         
         
        <label for="homeAddressName"><b>Διεύθυνση Κατοικίας:</b></label>
        <textarea placeholder="Διευθυνση Κατοικίας" name="homeAddressName" id="homeAddressName" required oninvalid="this.setCustomValidity('Home address is required, Home Address characters should not exceed limit (255)')"  rows="4" cols="50">${customer != null && customer.homeAddress != null? customer.homeAddress.name : ''}</textarea>
         
         <label for="homePostcode"><b>TK Σπιτιου:</b></label>
         <input type="text"  value="${customer != null && customer.homeAddress != null ? customer.homeAddress.postcode : ''}" placeholder="Τοποθετηστε το ΤΚ" name="homePostcode" id="homePostcode" oninvalid="this.setCustomValidity('Home postcode must contain exactly 5 digits')" required pattern="[0-9]{5}" > 
         
        <label for="moreInfo"><b>Λοιπές Πληροφορίες:</b></label>
        <textarea placeholder="Λοιπές Πληροφορίες" name="moreInfo" id="moreInfo" oninvalid="this.setCustomValidity('Information  exceed limit (255)')"rows="4" cols="50">${customer != null ? customer.moreInfo : ''}</textarea>

        <label for="username">  <b>Username:*</b></label>
        <input type="text"  value="${customer != null ? customer.username : ''}" placeholder="Τοποθετήστε το username" name="username" id="username"   maxlength="20" pattern=".{4,20}" required  oninvalid="this.setCustomValidity('Please Enter Your Username,Username must be between 4 and 20 characters')" oninput="this.setCustomValidity('')">

        <label for="password">  <b>Password:*</b> </label>
        <input type="password"   value="${customer != null ? customer.password : ''}" placeholder="Τοποθετήστε το password" name="password" id="password" required  maxlength="30" pattern=".{8,30}"  oninvalid="this.setCustomValidity('Please Enter Your Password,password must be at least 8 characters')"oninput="this.setCustomValidity('')">

       
         <input type="submit" class="submit" value="Submit">
        
    </form>




</body>
</html>