
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Registration</title>
</head>
<body>

    <h2>Εγγραφή νέου πελάτη</h2>

    <form:form
        action="${pageContext.request.contextPath}/register"
        method="post"
        modelAttribute="customer"
        <%--με το novalidate ο browser δεν θα εμποδίζει την υποβολή βάσει αυτών(required, minlength, maxlength και pattern). Το validation θα γίνεται στον server --%>
        novalidate="novalidate">

        <%-- Γενικά σφάλματα --%>
        <form:errors path="" cssStyle="color:red;"/>

        <h3>Προσωπικά στοιχεία</h3>

        <%-- Όνομα --%>
        <div>
            <label for="name">Όνομα:</label>
            <form:input
                path="name"
                id="name"
                required="required"
                minlength="3"
                maxlength="30"
                pattern="[A-Za-zΑ-Ωα-ωΆΈΉΊΌΎΏάέήίόύώΪΫϊϋΐΰ]+"
                title="3 έως 30 γράμματα, ελληνικά ή λατινικά, χωρίς αριθμούς"/>
            <form:errors path="name" cssStyle="color:red;"/>
        </div>

        <%-- Επώνυμο --%>
        <div>
            <label for="surname">Επώνυμο:</label>
            <form:input
                path="surname"
                id="surname"
                required="required"
                minlength="3"
                maxlength="30"
                pattern="[A-Za-zΑ-Ωα-ωΆΈΉΊΌΎΏάέήίόύώΪΫϊϋΐΰ]+"
                title="3 έως 30 γράμματα, ελληνικά ή λατινικά, χωρίς αριθμούς"/>
            <form:errors path="surname" cssStyle="color:red;"/>
        </div>

        <%-- Φύλο --%>
        <div>
            <label for="gender">Φύλο:</label>
            <form:select path="gender"
                         id="gender"
                         required="required">

                <form:option value="" label="-- Επιλογή --"/>
                <form:option value="Male" label="Άνδρας"/>
                <form:option value="Female" label="Γυναίκα"/>
                <form:option value="Other" label="Άλλο"/>

            </form:select>
            <form:errors path="gender" cssStyle="color:red;"/>
        </div>

        <%-- ΑΦΜ --%>
        <div>
            <label for="afm">ΑΦΜ:</label>
            <form:input path="afm"
                id="afm"
                required="required"
                pattern="[0-9]{9}"
                maxlength="9"
                inputmode="numeric"
                title="Το ΑΦΜ πρέπει να περιέχει ακριβώς 9 ψηφία"/>
            <form:errors path="afm" cssStyle="color:red;"/>
        </div>

        <%-- Ημερομηνία γέννησης --%>
        <div>
            <label for="birthdate">Ημερομηνία γέννησης:</label>
            <form:input path="birthdate"
                id="birthdate"
                type="date"
                required="required"/>

            <form:errors path="birthdate"
                    cssStyle="color:red;"/>

            <form:errors path="birthdateWithinAllowedRange"
                cssStyle="color:red;"/>
        </div>

        <h3>Διεύθυνση κατοικίας</h3>

        <%-- Διεύθυνση κατοικίας --%>
        <div>
            <label for="homeName">Διεύθυνση:</label>
            <form:input path="homeAddress.name"
                id="homeName"
                required="required"
                minlength="3"
                maxlength="100"
                title="Η διεύθυνση πρέπει να έχει 3 έως 100 χαρακτήρες"/>
            <form:errors path="homeAddress.name"
                cssStyle="color:red;"/>
        </div>

        <%-- ΤΚ κατοικίας --%>
        <div>
            <label for="homePostcode"> Ταχυδρομικός κώδικας:</label>

            <form:input path="homeAddress.postcode"
                id="homePostcode"
                required="required"
                pattern="[0-9]{5}"
                maxlength="5"
                inputmode="numeric"
                title="Ο ταχυδρομικός κώδικας πρέπει να έχει 5 ψηφία"/>

            <form:errors path="homeAddress.postcode"
                cssStyle="color:red;"/>
        </div>

        <h3>Διεύθυνση εργασίας</h3>

        <%-- Διεύθυνση εργασίας --%>
        <div>
            <label for="workName">Διεύθυνση:</label>
            <form:input path="workAddress.name"
                id="workName"
                required="required"
                minlength="3"
                maxlength="100"
                title="Η διεύθυνση πρέπει να έχει 3 έως 100 χαρακτήρες"/>

            <form:errors path="workAddress.name"
                cssStyle="color:red;"/>
        </div>

        <%-- ΤΚ εργασίας --%>
        <div>
            <label for="workPostcode">Ταχυδρομικός κώδικας:</label>

            <form:input path="workAddress.postcode"
                id="workPostcode"
                required="required"
                pattern="[0-9]{5}"
                maxlength="5"
                inputmode="numeric"
                title="Ο ταχυδρομικός κώδικας πρέπει να έχει 5 ψηφία"/>

            <form:errors path="workAddress.postcode"
                cssStyle="color:red;"/>
        </div>

        <h3>Περισσότερες πληροφορίες</h3>

        <div>
            <label for="moreInfo">Πληροφορίες:</label>
            <form:textarea path="moreInfo"
                id="moreInfo"
                rows="4"
                maxlength="1000"/>

            <form:errors path="moreInfo"
                cssStyle="color:red;"/>
        </div>

        <h3>Στοιχεία σύνδεσης</h3>

        <%-- Username --%>
        <div>
            <label for="username">Username:</label>

            <form:input path="username"
                id="username"
                required="required"
                minlength="5"
                maxlength="30"
                pattern="[A-Za-z0-9]+([._][A-Za-z0-9]+)*"
                title="5 έως 30 χαρακτήρες: λατινικά γράμματα, αριθμοί και τελείες ή κάτω παύλες ανάμεσα σε ομάδες χαρακτήρων"/>

            <form:errors path="username" cssStyle="color:red;"/>
        </div>

        <%-- Password --%>
        <div>
            <label for="password">Password:</label>
            <form:password path="password"
                id="password"
                required="required"/>

            <form:errors path="password" cssStyle="color:red;"/>
        </div>

        <button type="submit">Εγγραφή</button>

    </form:form>

    <p>
        Έχεις ήδη λογαριασμό;
        <a href="${pageContext.request.contextPath}/login">Σύνδεση</a>
    </p>

</body>
</html>
