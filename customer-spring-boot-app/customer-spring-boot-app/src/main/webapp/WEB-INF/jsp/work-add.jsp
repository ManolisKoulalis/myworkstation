
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">
    <title>Προσθήκη νέας εργασίας</title>
</head>

<body>

    <main class="work-form-container">

        <h1>Προσθήκη νέας εργασίας</h1>

        <form:form action="${pageContext.request.contextPath}/customers/works/new" method="post" modelAttribute="work"
            novalidate="novalidate">

            <form:errors path="" cssClass="form-error"/>

            <!-- ΣΤΟΙΧΕΙΑ ΕΡΓΑΣΙΑΣ -->
            <section class="form-section">

                <h2>Στοιχεία εργασίας</h2>

                <div class="form-group">

                    <label for="workType"> Είδος εργασίας:</label>
                    <form:input path="workType"
                        id="workType"/>
                    <form:errors path="workType"
                        cssClass="form-error"/>

                </div>

            </section>

            <!-- ΔΙΕΥΘΥΝΣΗ ΚΑΤΑΣΚΕΥΗΣ -->
            <section class="form-section">

                <h2>Διεύθυνση κατασκευής</h2>

                <div class="form-group">

                    <label for="constructionAddressName">Διεύθυνση:</label>
                    <form:input path="constructionAddress.name"
                        id="constructionAddressName"/>
                    <form:errors
                        path="constructionAddress.name"
                        cssClass="form-error"/>

                </div>

                <div class="form-group">

                    <label for="constructionAddressPostcode">Ταχυδρομικός κώδικας:</label>
                    <form:input path="constructionAddress.postcode"
                        id="constructionAddressPostcode"/>
                    <form:errors path="constructionAddress.postcode"
                        cssClass="form-error"/>

                </div>

            </section>

            <!-- ΟΙΚΟΝΟΜΙΚΑ ΣΤΟΙΧΕΙΑ -->
            <section class="form-section">

                <h2>Οικονομικά στοιχεία</h2>

                <div class="form-group">

                    <label for="chargeCost"> Συνολική χρέωση: </label>

                    <form:input path="chargeCost"
                        id="chargeCost"
                        type="number"
                        step="0.01"
                        min="0"/>
                    <form:errors path="chargeCost"
                        cssClass="form-error"/>

                </div>

                <div class="form-group">

                    <label for="paidCharge"> Πληρωμένο ποσό: </label>
                    <form:input path="paidCharge"
                        id="paidCharge"
                        type="number"
                        step="0.01"
                        min="0"/>
                    <form:errors path="paidCharge"
                        cssClass="form-error"/>

                </div>

            </section>

            <!-- ΠΡΟΣΘΕΤΕΣ ΠΛΗΡΟΦΟΡΙΕΣ -->
            <section class="form-section">

                <h2>Πρόσθετες πληροφορίες</h2>

                <div class="form-group">

                    <label for="moreInfo"> Παρατηρήσεις: </label>
                    <form:textarea path="moreInfo"
                        id="moreInfo"
                        rows="4"/>
                    <form:errors path="moreInfo" cssClass="form-error"/>

                </div>

            </section>

            <!-- ΚΟΥΜΠΙΑ -->
            <div class="form-actions">

                <button type="submit"> Αποθήκευση εργασίας </button>
                <a href="${pageContext.request.contextPath}/customers/profile">Ακύρωση</a>

            </div>

        </form:form>

    </main>

</body>
</html>
