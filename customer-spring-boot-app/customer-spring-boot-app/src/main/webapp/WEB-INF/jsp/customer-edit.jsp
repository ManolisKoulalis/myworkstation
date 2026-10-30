
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">
    <title>Επεξεργασία στοιχείων πελάτη</title>
</head>

<body>

    <main class="customer-edit-container">

        <h1>Επεξεργασία στοιχείων πελάτη</h1>

        <form:form action="${pageContext.request.contextPath}/customers/edit" method="post" modelAttribute="customer"
            novalidate="novalidate">

            <form:errors path="" cssClass="form-error"/>

            <!-- ΠΡΟΣΩΠΙΚΑ ΣΤΟΙΧΕΙΑ -->
            <section class="form-section">

                <h2>Προσωπικά στοιχεία</h2>

                <div class="form-group">
                    <label for="name">Όνομα:</label>
                    <form:input path="name" id="name"/>
                    <form:errors path="name" cssClass="form-error"/>
                </div>

                <div class="form-group">
                    <label for="surname">Επώνυμο:</label>
                    <form:input path="surname" id="surname"/>
                    <form:errors path="surname" cssClass="form-error"/>
                </div>

                <div class="form-group">
                    <label for="gender">Φύλο:</label>
                    <form:select path="gender" id="gender">
                        <form:option value="" label="Επιλέξτε"/>
                        <form:option value="Male" label="Άνδρας"/>
                        <form:option value="Female" label="Γυναίκα"/>
                    </form:select>
                    <form:errors path="gender" cssClass="form-error"/>
                </div>

                <div class="form-group">
                    <label for="afm">ΑΦΜ:</label>
                    <form:input path="afm" id="afm"/>
                    <form:errors path="afm" cssClass="form-error"/>
                </div>

                <div class="form-group">
                    <label for="birthdate">Ημερομηνία γέννησης:</label>
                    <form:input path="birthdate"
                                id="birthdate"
                                type="date"/>
                    <form:errors path="birthdate" cssClass="form-error"/>
                    <form:errors path="birthdateWithinAllowedRange"
                                 cssClass="form-error"/>
                </div>

            </section>

            <!-- ΔΙΕΥΘΥΝΣΗ ΚΑΤΟΙΚΙΑΣ -->
            <section class="form-section">

                <h2>Διεύθυνση κατοικίας</h2>

                <div class="form-group">
                    <label for="homeAddressName">Διεύθυνση:</label>
                    <form:input path="homeAddress.name"
                                id="homeAddressName"/>
                    <form:errors path="homeAddress.name"
                                 cssClass="form-error"/>
                </div>

                <div class="form-group">
                    <label for="homeAddressPostcode">Τ.Κ.:</label>
                    <form:input path="homeAddress.postcode"
                                id="homeAddressPostcode"/>
                    <form:errors path="homeAddress.postcode"
                                 cssClass="form-error"/>
                </div>

            </section>

            <!-- ΔΙΕΥΘΥΝΣΗ ΕΡΓΑΣΙΑΣ -->
            <section class="form-section">

                <h2>Διεύθυνση εργασίας</h2>

                <div class="form-group">
                    <label for="workAddressName">Διεύθυνση:</label>
                    <form:input path="workAddress.name"
                                id="workAddressName"/>
                    <form:errors path="workAddress.name"
                                 cssClass="form-error"/>
                </div>

                <div class="form-group">
                    <label for="workAddressPostcode">Τ.Κ.:</label>
                    <form:input path="workAddress.postcode"
                                id="workAddressPostcode"/>
                    <form:errors path="workAddress.postcode"
                                 cssClass="form-error"/>
                </div>

            </section>

            <!-- ΠΡΟΣΘΕΤΕΣ ΠΛΗΡΟΦΟΡΙΕΣ -->
            <section class="form-section">

                <h2>Πρόσθετες πληροφορίες</h2>

                <div class="form-group">
                    <form:textarea path="moreInfo" rows="4"/>
                    <form:errors path="moreInfo" cssClass="form-error"/>
                </div>

            </section>

            <!-- ΣΤΟΙΧΕΙΑ ΣΥΝΔΕΣΗΣ -->
            <section class="form-section">

                <h2>Στοιχεία σύνδεσης</h2>

                <div class="form-group">
                    <label for="username">Username:</label>
                    <form:input path="username" id="username"/>
                    <form:errors path="username" cssClass="form-error"/>
                </div>

                <div class="form-group">
                    <label for="password">Νέος κωδικός:</label>
                    <form:password path="password" value= "*********" id="password"/>
                    <form:errors path="password" cssClass="form-error"/>
                    <p>
                        Άφησε το πεδίο κενό αν δεν θέλεις
                        να αλλάξεις κωδικό.
                    </p>
                </div>

                <div class="form-group">
                    <label for="currentPassword">Τρέχων κωδικός:</label>
                    <input type="password"
                           id="currentPassword"
                           name="currentPassword"/>
                    <p>
                        Απαιτείται μόνο αν θέλεις
                        να αλλάξεις τον κωδικό σου.
                    </p>
                </div>

            </section>

            <!-- ΚΟΥΜΠΙΑ -->
            <div class="form-actions">

                <button type="submit">Αποθήκευση αλλαγών</button>

                <a href="${pageContext.request.contextPath}/customers/profile">Ακύρωση</a>

            </div>

        </form:form>

    </main>

</body>
</html>
