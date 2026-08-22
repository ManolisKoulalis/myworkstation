<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
 <%@ page isELIgnored="false" %>
<meta charset="UTF-8">
<title>Insert Work Page</title>
<style><%@include file="WEB-INF/css/style.css"%></style>
</head>
<body>

<header>
    <h2 id="left_h2"><a href="index.jsp"> Test project for customer</a> </h2>
    <nav>
      
        <ul>
            <li><a href="index.jsp"> Αρχική</a></li>
            <li> <a href="${pageContext.request.contextPath}/logout">Αποσύνδεση Πελάτη</a></li>
        </ul>
    </nav>
 </header>
 
 


<c:if test="${not empty message}">
    <p class="error">${message}</p>
</c:if>

 <h1 class="dotted">
 	<c:if test="${work != null}">Επεξεργασια Έργου</c:if>
	<c:if test="${work == null}">Προσθήκη Νέου Έργου</c:if>
 </h1>



<form class="addNewWork" name="myform" action="${pageContext.request.contextPath}/${work == null ? 'insertWork' : 'updateWork'}" method="post" >

 
 <input type="hidden" name="workId" value="${work.id}">
  

<label for="eidosErgou"> <B>Είδος Έργου: </B></label>
<input type="text" placeholder="Τοποθετήστε το Είδος Έργου" value="${work != null ? work.workType : ''}" name="workType" id="eidosErgou" required maxlength="30" pattern="[A-Za-zΑ-Ωα-ωΆ-Ώά-ώ\s]{3,30}"  oninvalid="this.setCustomValidity('Please Enter Your WorkOnly Characters Allowed, between 3-30 characters)')" oninput="this.setCustomValidity('')">

<label for="workAddressName"><b>Διευθυνση Εργου:</b></label>
<input type="text" placeholder="Τοποθετήστε την Διεύθυνση" name="constructionAddressName" id="workAddressName"  value="${work != null && work.constructionAddress != null ? work.constructionAddress.name : ''}" required maxlength="255"  oninvalid="this.setCustomValidity('Please Enter Your Work Address')" oninput="this.setCustomValidity('')">

<label for="TK"><b>TK:</b></label>
<input type="text" placeholder="Τοποθετήστε το ΤΚ" name="constructionPostcode" id="TK" maxlength="5" pattern="[0-9]{5}" value="${work != null && work.constructionAddress != null ? work.constructionAddress.postcode : ''}" oninvalid="this.setCustomValidity('Please Enter Your TK,Construction postcode must contain exactly 5 digits')" oninput="this.setCustomValidity('')" required>

<Label for="sum"><b>Συνολικό Κόστος:</b></LAbel>
<input type="number" value="${work != null ? work.chargeCost : ''}" placeholder="Τοποθετήστε το Συνολικό Κόστος" name="chargeCost" id="sum"   min="0"step="0.01" required  oninvalid="this.setCustomValidity('Please Enter Your Charge(Το ποσο δεν γινεται να ειναι αρνητικος αριθμος)')" oninput="this.setCustomValidity('')"> 

<Label for="paidcharge"><b>Καταβληθέν Ποσό:</b></LAbel>
<input type="number" value="${work != null ? work.paidCharge : ''}" placeholder="Τοποθετήστε το Καταβληθέν Ποσό" name="paidCharge" id="paidCharge"   min="0" step="0.01"  oninvalid="this.setCustomValidity('Please Enter Your Paid Charge(Το ποσο δεν γινεται να ειναι αρνητικος αριθμος)')" oninput="this.setCustomValidity('')" required> 

<Label for="remaingcharge"><b>Υπολοιπόμενο Ποσό:</b></LAbel>
<input type="number" value="${work != null ? work.remainingCharge : ''}" readonly placeholder="Εμφανηση υπολοιπόμενου ποσού" name="remaingcharge" id="remaingcharge" >

  <label for="moreInfo"><b>Λοιπές Πληροφορίες:</b></label>
   <textarea placeholder="Λοιπές Πληροφορίες" name="moreInfo" id="moreInfo" rows="4" cols="50">${work != null ? work.moreInfo : ''}</textarea>

 <input type="submit" class="submit" value="Submit">

</form>



<script>

function calculateRemaining() {

    let total = parseFloat(document.getElementById("sum").value) || 0;
    let paid = parseFloat(document.getElementById("paidCharge").value) || 0;

    document.getElementById("remaingcharge").value = total - paid;
}

document.getElementById("sum")
        .addEventListener("input", calculateRemaining);

document.getElementById("paidCharge")
        .addEventListener("input", calculateRemaining);



document.querySelector(".addNewWork")
        .addEventListener("submit", function(e){

    let total = parseFloat(document.getElementById("sum").value) || 0;
    let paid = parseFloat(document.getElementById("paidCharge").value) || 0;

    if (paid > total) {
        alert("Το καταβληθέν ποσό δεν μπορεί να είναι μεγαλύτερο από το συνολικό κόστος.");
        e.preventDefault();
    }
});

calculateRemaining();
</script>


</body>
</html>