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
<title>Loged in Customer</title>
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
 
 

    
     <div class="details_table_container">
        <table>
              <caption><strong>Πληροφορίες Πελάτη</strong></caption>
          <thead>
            <tr>
              <th>Ονομα</th>
              <th>Eπώνυμο</th>
              <th>ΦΥΛΟ</th>
              <TH>Γενεθλια</TH>
              <th>Αφμ</th>
              <th>Διευθυνση Κατοικίας</th>
              <th>ΤΚ Κατοικίας</th>
              <th>Διευθυνση Εργασίας</th>
              <th>ΤΚ Εργασίας</th>
              <th>Λοιπές Πληροφορίες</th>
              <th>Username</th>
              <th>Password</th>
              <TH colspan="2">Ενέρεγειες</TH>
            </tr>
          </thead>
          
          <tbody>
            <tr>
              <td><c:out value="${customer.name}" /></td>
              <TD><c:out value="${customer.surname}" /></TD>
              <TD><c:out value="${customer.gender}" /></TD>
              <TD><c:out value="${customer.birthdate}" /></TD>
              <TD><c:out value="${customer.afm}" /></TD>
              <TD><c:out value="${customer.homeAddress.name}" /></TD>
              <TD><c:out value="${customer.homeAddress.postcode}" /></TD>
              <!-- Η jstl καλει μονη της getter/setter  -->
              <TD><c:out value="${customer.workAddress.name}" /></TD>
              <TD><c:out value="${customer.workAddress.postcode}" /></TD>
              <TD><c:out value="${customer.moreInfo}" /></TD>
              <td> <c:out value="${customer.username}" /></td>
              <td>********</td>
              <td> <a id="href_edit" href="${pageContext.request.contextPath}/editCustomer?customerId=${customer.id}"><b>Επεξεργασία</b></a></td>
              <td> <a id="href_delete" href="${pageContext.request.contextPath}/delete?customerId=${customer.id}"><b>Διαγραφη</b></a>  </td>
            </tr>
          </tbody>
          
          
       </table>
          
       </div>


	<div class="work_table_container">
<div class="work_table">

<table>

   <caption><strong>Λίστα Ανατεθέντων Έργων</strong></caption>
      <thead>
          <tr>
             <th>Είδος Έργου</th>
             <th>Διεύθυνση</th>
             <th>ΤΚ</th>
             <th>Συνολικό Κόστος</th>
             <th>Καταβληθέν Ποσό</th>
             <th>Υπολοιπόμενο Ποσό</th>
             <th>Λοιπές Πληροφορίες</th>
             <th colspan="2">Ενέργειες</th>
          </tr>
       
      </thead>

      <tbody>
      
      <!-- επειδη εχω λιστα με works δεν μπορω να κανω customer.work.name καθως το getter που εχω 
      ειναι για την λιστα οποτε τραβαω την λιστα με customer.worklist και μετα χρησιμοποιω το  work της λιστας  -->
       	<c:forEach items="${customer.worklist}" var="work" >
      
        <tr>
          <td><c:out value="${work.workType}"/></td>
          <td><c:out value="${work.constructionAddress.name}" /></td>
          <td><c:out value="${work.constructionAddress.postcode}" /></td>
          <td><c:out value="${work.chargeCost}" /></td>
          <td><c:out value="${work.paidCharge}" /></td>
          <td><c:out value="${work.chargeCost-work.paidCharge}" /></td>
          <td><c:out value="${work.moreInfo}" /></td>
          <td> <a id="href_edit" href="${pageContext.request.contextPath}/editWork?workId=${work.id}&customerId=${customer.id}"><b>Επεξεργασία</b></a></td>
          <td> <a id="href_delete" href="${pageContext.request.contextPath}/deleteWork?workId=${work.id}&customerId=${customer.id}"><b>Διαγραφη</b></a>  </td>
        </tr>
        
         </c:forEach>
        
      </tbody>

</table>
<div id="divaddWork"> 
<b><a href="${pageContext.request.contextPath}/addWork?customerId=${customer.id}" id="addWork">Προσθήκη Έργου</a></b>
</div> 
</div>

</div>









</body>
</html>