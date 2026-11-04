
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Επεξεργασία Εργασίας - CustomerApp</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
                <a href="${pageContext.request.contextPath}/customers/edit">Επεξεργασία Στοιχείων</a>
            </li>

            <li>
                <form action="${pageContext.request.contextPath}/logout" method="post">

                    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}">

                    <button type="submit">Αποσύνδεση</button>

                </form>
            </li>
        </ul>

    </nav>

    <!-- Κεντρικό περιεχόμενο -->
    <main class="main-content work-page">

        <!-- Επικεφαλίδα -->
        <header class="page-header">

            <div>
                <h1>Επεξεργασία Εργασίας</h1>

                <p>
                    Ενημερώστε τα στοιχεία
                    της εργασίας σας.
                </p>
            </div>

        </header>

        <!-- Κάρτα φόρμας -->
        <section class="card work-form-card">

            <form:form modelAttribute="work" action="${pageContext.request.contextPath}/customers/works/${workId}/edit" method="post" cssClass="work-form"
                novalidate="novalidate">

                <!-- Γενικά σφάλματα -->
                <form:errors path="" cssClass="form-error global-form-error" element="div"/>

                <!-- Στοιχεία εργασίας -->
                <fieldset class="form-section">

                    <legend>Στοιχεία Εργασίας</legend>

                    <div class="form-group">

                        <form:label path="workType">Είδος / Περιγραφή Εργασίας</form:label>
                        <form:input path="workType" placeholder="π.χ. Ηλεκτρολογική εγκατάσταση"/>
                        <form:errors path="workType" cssClass="form-error" element="div"/>

                    </div>

                </fieldset>

                <!-- Διεύθυνση κατασκευής -->
                <fieldset class="form-section">

                    <legend>Διεύθυνση Κατασκευής</legend>

                    <div class="form-grid">

                        <div class="form-group">

                            <form:label path="constructionAddress.streetName"> Οδός / Διεύθυνση</form:label>
                            <form:input path="constructionAddress.streetName" placeholder="Διεύθυνση κατασκευής"/>
                            <form:errors path="constructionAddress.streetName" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="constructionAddress.postcode">Ταχυδρομικός Κώδικας</form:label>
                            <form:input path="constructionAddress.postcode" placeholder="π.χ. 54622"/>
                            <form:errors path="constructionAddress.postcode" cssClass="form-error" element="div"/>

                        </div>

                    </div>

                </fieldset>

                <!-- Οικονομικά στοιχεία -->
                <fieldset class="form-section">

                    <legend>Οικονομικά Στοιχεία</legend>

                    <div class="form-grid">

                        <div class="form-group">

                            <form:label path="chargeCost">Συνολική Χρέωση (€)</form:label>
                            <form:input path="chargeCost"
                                type="number"
                                min="0"
                                step="0.01"
                                placeholder="0.00"/>
                            <form:errors path="chargeCost" cssClass="form-error" element="div"/>

                        </div>

                        <div class="form-group">

                            <form:label path="paidCharge">Ποσό Πληρωμής (€)</form:label>
                            <form:input path="paidCharge"
                                type="number"
                                min="0"
                                step="0.01"
                                placeholder="0.00"/>
                            <form:errors path="paidCharge" cssClass="form-error" element="div"/>

                        </div>

                    </div>

                    <p class="form-help">
                        Το υπόλοιπο υπολογίζεται αυτόματα
                        από τη συνολική χρέωση μείον
                        το ποσό πληρωμής.
                    </p>

                </fieldset>

                <!-- Επιπλέον πληροφορίες -->
                <fieldset class="form-section">

                    <legend>Επιπλέον Πληροφορίες</legend>

                    <div class="form-group">

                        <form:label path="moreInfo">Παρατηρήσεις</form:label>
                        <form:textarea path="moreInfo" rows="4" placeholder="Προαιρετικές πληροφορίες για την εργασία"/>
                        <form:errors path="moreInfo" cssClass="form-error" element="div"/>

                    </div>

                </fieldset>

                <!-- Ενέργειες -->
                <div class="form-actions work-actions">

                    <button type="submit" class="btn btn-primary">Αποθήκευση Αλλαγών</button>

                    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/customers/profile">Ακύρωση</a>

                </div>

            </form:form>

        </section>

    </main>

</body>
</html>
