
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
         
         <%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Login</title>
</head>


<body>

    <h2>Σύνδεση πελάτη</h2>
    
    
     <%-- Μήνυμα αποτυχημένου login --%>
    <c:if test="${param.error != null}">
        <p style="color: red;">
            Λανθασμένο username ή password.
        </p>
    </c:if>

    <%-- Μήνυμα επιτυχημένου logout --%>
    <c:if test="${param.logout != null}">
        <p style="color: green;">
            Αποσυνδέθηκες επιτυχώς.
        </p>
    </c:if>
    
    <%--Μηνυμα αλλαγης username --%>
	 <c:if test="${param.usernameChanged != null}">
	    <p class="success-message">
	        Το username άλλαξε επιτυχώς.
	        Συνδέσου ξανά με το νέο username.
	    </p>
	</c:if>
	
	<%--Μηνυμα επιτυχημενης διαγραφης χρηστη --%>
	<c:if test="${param.accountDeleted != null}">
	    <p class="success-message">
	        Ο λογαριασμός σου διαγράφηκε επιτυχώς.
	    </p>
	</c:if>

    <form action="${pageContext.request.contextPath}/login" method="post">

        
          <label for="username">Username:</label>
            <input type="text"
                   id="username"
                   name="username"
                   required>
        

       
            <label for="password">Password:</label>
            <input type="password"
                   id="password"
                   name="password"
                   required>
        

        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}">

        <button type="submit">Σύνδεση</button>

    </form>

    <p>
        Δεν έχεις λογαριασμό;
        <a href="${pageContext.request.contextPath}/register">Εγγραφή</a>
    </p>

</body>
</html>