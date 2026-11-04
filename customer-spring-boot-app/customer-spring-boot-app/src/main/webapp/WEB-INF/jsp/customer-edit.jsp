
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"  content="width=device-width, initial-scale=1.0">

    <title>Επεξεργασία Στοιχείων - CustomerApp</title>

    <link rel="stylesheet"
      href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">

		<link rel="stylesheet"
     		 href="${pageContext.request.contextPath}/css/style.css">

		<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

		<script src="https://code.jquery.com/ui/1.14.1/jquery-ui.min.js"></script>
</head>

<body class="dashboard-page">

    <!-- Navigation Bar -->
    <nav class="dashboard-nav">

        <a class="nav-brand"  href="${pageContext.request.contextPath}/customers/profile">CustomerApp</a>

        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/customers/profile">Το Προφίλ μου</a>
            </li>

            <li>
                <a class="active"  aria-current="page"  href="${pageContext.request.contextPath}/customers/edit">Επεξεργασία Στοιχείων</a>
            </li>

            <li>
                <form action="${pageContext.request.contextPath}/logout" method="post">

                    <input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}">
                    
                    <button type="submit">Αποσύνδεση</button>

                </form>
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

    <main class="main-content edit-page">

        <!-- Επικεφαλίδα -->
        <header class="page-header">

            <div>
                <h1>Επεξεργασία Στοιχείων</h1>

                <p>
                    Ενημερώστε τα προσωπικά στοιχεία
                    και τις πληροφορίες του λογαριασμού σας.
                </p>
            </div>

        </header>

        <!-- Κάρτα επεξεργασίας -->
        <section class="card edit-card">

            <form:form modelAttribute="customer" action="${pageContext.request.contextPath}/customers/edit" method="post" cssClass="edit-form"
                novalidate="novalidate">

                <!-- Γενικά σφάλματα -->
                <form:errors path="" cssClass="form-error global-form-error" element="div"/>

                <!-- Προσωπικά στοιχεία -->
                <fieldset class="form-section">

                    <legend>Προσωπικά Στοιχεία</legend>

                    <div class="form-grid">

                        <div class="form-group">

                            <form:label path="name">Όνομα</form:label>
                            <form:input path="name"/>
                            <form:errors path="name" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="surname">Επώνυμο</form:label>
                            <form:input path="surname"/>
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
                            <form:input path="afm"/>
                            <form:errors cssClass="form-error" element="div"/>

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
                            <form:input path="homeAddress.streetName"/>
                            <form:errors path="homeAddress.streetName" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="homeAddress.postcode">Ταχυδρομικός Κώδικας</form:label>
                            <form:input path="homeAddress.postcode"/>
                            <form:errors path="homeAddress.postcode" cssClass="form-error" element="div"/>

                        </div>

                    </div>

                    <h3 class="form-subsection-title">
                        Διεύθυνση Εργασίας
                    </h3>

                    <div class="form-grid">

                        <div class="form-group">

                            <form:label path="workAddress.streetName">Οδός / Διεύθυνση</form:label>
                            <form:input path="workAddress.streetName"/>
                            <form:errors path="workAddress.streetName" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="workAddress.postcode">Ταχυδρομικός Κώδικας</form:label>
                            <form:input path="workAddress.postcode"/>
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
                            <form:input path="username" autocomplete="username"/>
                            <form:errors path="username" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="password">Νέο Password</form:label>
                            <form:password path="password" autocomplete="new-password" placeholder="Αφήστε κενό για να μην αλλάξει"/>
                            <form:errors path="password" cssClass="form-error" element="div"/>

                        </div>

                    </div>

                    <p class="form-help">
                        Αν δεν θέλετε να αλλάξετε τον κωδικό σας,
                        αφήστε το πεδίο «Νέο Password» κενό.
                    </p>

                </fieldset>

                <!-- Επιπλέον πληροφορίες -->
                <fieldset class="form-section">

                    <legend>Επιπλέον Πληροφορίες</legend>

                    <div class="form-group">

                        <form:label path="moreInfo">Παρατηρήσεις</form:label>
                        <form:textarea path="moreInfo" rows="4"/>
                        <form:errors path="moreInfo" cssClass="form-error" element="div"/>

                    </div>

                </fieldset>

                <!-- Επιβεβαίωση -->
                <fieldset class="form-section">

                    <legend>Επιβεβαίωση Αλλαγών</legend>

                    <p class="form-help">
                        Εισάγετε τον τρέχοντα κωδικό σας
                        για να αποθηκεύσετε τις αλλαγές.
                    </p>

                    <div class="form-group">

                        <label for="currentPassword">Τρέχον Password</label>

                        <input type="password"
                               id="currentPassword"
                               name="currentPassword"
                               autocomplete="current-password"
                               placeholder="Εισάγετε τον τρέχοντα κωδικό σας">

                    </div>

                </fieldset>

                <!-- Ενέργειες -->
                <div class="form-actions edit-actions">

                    <button type="submit" class="btn btn-primary">Αποθήκευση Αλλαγών</button>

                    <a class="btn btn-secondary"  href="${pageContext.request.contextPath}/customers/profile">Ακύρωση </a>

                </div>

            </form:form>

        </section>

    </main>

</body>
</html>
