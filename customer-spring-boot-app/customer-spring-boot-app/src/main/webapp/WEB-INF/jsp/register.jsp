
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"  content="width=device-width, initial-scale=1.0">

    <title>Εγγραφή - CustomerApp</title>

    <link rel="stylesheet"
      href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/style.css">

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script src="https://code.jquery.com/ui/1.14.1/jquery-ui.min.js"></script>
</head>

<body class="auth-page">

    <!-- Navigation Bar -->
    <nav class="dashboard-nav auth-nav">

        <a class="nav-brand"  href="${pageContext.request.contextPath}/">CustomerApp</a>

        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/login">Σύνδεση</a>
            </li>

            <li>
                <a class="active" aria-current="page" href="${pageContext.request.contextPath}/register">Εγγραφή</a>
            </li>
        </ul>

    </nav>
    
						  <script>
						  $(function () {
							    $("#birthdate").datepicker({
							        dateFormat: "yy-mm-dd",
							        changeMonth: true,
							        changeYear: true,
							        maxDate: 0,
							        yearRange: "-100:+0",
							        showButtonPanel: false
							    });
							});
								</script>

    <main class="auth-container register-container">

        <section class="auth-card register-card">

            <!-- Επικεφαλίδα -->
            <header class="auth-header">

                <div class="auth-icon" aria-hidden="true">
                    &#128100;
                </div>

                <h1>Δημιουργία Λογαριασμού</h1>

                <p>
                    Συμπληρώστε τα στοιχεία σας
                    για να εγγραφείτε στο CustomerApp.
                </p>

            </header>

            <!-- Φόρμα εγγραφής -->
            <form:form modelAttribute="customer" action="${pageContext.request.contextPath}/register" method="post" cssClass="register-form"
                novalidate="novalidate">

                <!-- Γενικά σφάλματα -->
                <form:errors path="" cssClass="form-error global-form-error" element="div"/>

                <!-- Προσωπικά στοιχεία -->
                <fieldset class="form-section">

                    <legend>Προσωπικά Στοιχεία</legend>

                    <div class="form-grid">

                        <div class="form-group">

                            <form:label path="name">Όνομα</form:label>
                            <form:input path="name" placeholder="Εισάγετε το όνομά σας"/>
                            <form:errors path="name" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="surname">Επώνυμο</form:label>
                            <form:input path="surname" placeholder="Εισάγετε το επώνυμό σας"/>
                            <form:errors path="surname" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="gender">Φύλο</form:label>
                            <form:select path="gender" cssClass="form-select">
	
		     					 <form:option value="" label="Επιλέξτε φύλο"/>
		     					 <form:option value="Male" label="Άνδρας"/>
								 <form:option value="Female" label="Γυναίκα"/>
								 <form:option value="Other" label="Άλλο"/>

    					   </form:select>
                            <form:errors path="gender" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="afm">Α.Φ.Μ.</form:label>
                           <form:input path="afm" placeholder="Εισάγετε το ΑΦΜ σας"/>
                            <form:errors path="afm" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="birthdate">Ημερομηνία Γέννησης</form:label>
                            <form:input  path="birthdate"
									      id="birthdate"
									      type="text"
									      autocomplete="off"
									      readonly="true" />
                            <form:errors path="birthdate" cssClass="form-error" element="div"/>

                        </div>

                    </div>

                </fieldset>

                <!-- Διευθύνσεις -->
                <fieldset class="form-section">

                    <legend>Διευθύνσεις</legend>

                    <h3 class="form-subsection-title">
                        Διεύθυνση Κατοικίας
                    </h3>

                    <div class="form-grid">

                        <div class="form-group">

                            <form:label path="homeAddress.streetName">Οδός / Διεύθυνση</form:label>
                            <form:input path="homeAddress.streetName" placeholder="Διεύθυνση κατοικίας"/>
                            <form:errors path="homeAddress.streetName" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="homeAddress.postcode">Ταχυδρομικός Κώδικας</form:label>
                            <form:input path="homeAddress.postcode" placeholder="Τ.Κ. κατοικίας"/>
                            <form:errors path="homeAddress.postcode" cssClass="form-error" element="div"/>

                        </div>

                    </div>

                    <h3 class="form-subsection-title">
                        Διεύθυνση Εργασίας
                    </h3>

                    <div class="form-grid">

                        <div class="form-group">

                            <form:label path="workAddress.streetName">Οδός / Διεύθυνση</form:label>
                            <form:input path="workAddress.streetName" placeholder="Διεύθυνση εργασίας"/>
                            <form:errors path="workAddress.streetName" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="workAddress.postcode">Ταχυδρομικός Κώδικας</form:label>
                            <form:input path="workAddress.postcode" placeholder="Τ.Κ. εργασίας"/>
                            <form:errors path="workAddress.postcode" cssClass="form-error" element="div"/>

                        </div>

                    </div>

                </fieldset>

                <!-- Στοιχεία λογαριασμού -->
                <fieldset class="form-section">

                    <legend>Στοιχεία Λογαριασμού</legend>

                    <div class="form-grid">

                        <div class="form-group">

                            <form:label path="username">Username</form:label>
                            <form:input path="username" autocomplete="username" placeholder="Επιλέξτε username"/>
                            <form:errors path="username" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="password">Password</form:label>
                            <form:password path="password" autocomplete="new-password" placeholder="Επιλέξτε κωδικό"/>
                            <form:errors path="password" cssClass="form-error" element="div"/>

                        </div>

                    </div>

                </fieldset>

                <!-- Επιπλέον πληροφορίες -->
                <fieldset class="form-section">

                    <legend>Επιπλέον Πληροφορίες</legend>

                    <div class="form-group">

                        <form:label path="moreInfo">Παρατηρήσεις</form:label>
                        <form:textarea path="moreInfo" rows="4" placeholder="Προαιρετικές πληροφορίες"/>
                        <form:errors path="moreInfo" cssClass="form-error" element="div"/>

                    </div>

                </fieldset>

                <!-- Κουμπί εγγραφής -->
                <div class="form-actions">
                    <button type="submit"   class="btn btn-primary auth-submit">Δημιουργία Λογαριασμού</button>
                </div>

            </form:form>

            <!-- Σύνδεσμος σύνδεσης -->
            <footer class="auth-footer">

                <p>
                    Έχετε ήδη λογαριασμό;
                    <a href="${pageContext.request.contextPath}/login">Σύνδεση</a>
                </p>

            </footer>

        </section>

    </main>

</body>
</html>
