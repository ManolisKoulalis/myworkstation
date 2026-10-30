
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">
    <title>Το προφίλ μου</title>
</head>

<body>

    <main class="profile-container">

        <h1>Το προφίλ μου</h1>
        
        <c:if test="${not empty successMessage}">
    		<p class="success-message">
       		 	<c:out value="${successMessage}"/>
    		</p>
		</c:if>

        <!-- ΠΡΟΣΩΠΙΚΑ ΣΤΟΙΧΕΙΑ -->
        <section class="profile-section">

            <h2>Προσωπικά στοιχεία</h2>

            <p>
                <strong>Όνομα:</strong>
                <c:out value="${customer.name}"/>
            </p>

            <p>
                <strong>Επώνυμο:</strong>
                <c:out value="${customer.surname}"/>
            </p>

            <p>
                <strong>Φύλο:</strong>
                <c:out value="${customer.gender}"/>
            </p>

            <p>
                <strong>ΑΦΜ:</strong>
                <c:out value="${customer.afm}"/>
            </p>

            <p>
                <strong>Ημερομηνία γέννησης:</strong>
                <c:out value="${customer.birthdate}"/>
            </p>

            <p>
                <strong>Username:</strong>
                <c:out value="${customer.username}"/>
            </p>

        </section>

        <!-- ΔΙΕΥΘΥΝΣΗ ΚΑΤΟΙΚΙΑΣ -->
        <section class="profile-section">

            <h2>Διεύθυνση κατοικίας</h2>

            <p>
                <strong>Διεύθυνση:</strong>
                <c:out value="${customer.homeAddress.name}"/>
            </p>

            <p>
                <strong>Ταχυδρομικός κώδικας:</strong>
                <c:out value="${customer.homeAddress.postcode}"/>
            </p>

        </section>

        <!-- ΔΙΕΥΘΥΝΣΗ ΕΡΓΑΣΙΑΣ -->
        <section class="profile-section">

            <h2>Διεύθυνση εργασίας</h2>

            <p>
                <strong>Διεύθυνση:</strong>
                <c:out value="${customer.workAddress.name}"/>
            </p>

            <p>
                <strong>Ταχυδρομικός κώδικας:</strong>
                <c:out value="${customer.workAddress.postcode}"/>
            </p>

        </section>

        <!-- ΠΡΟΣΘΕΤΕΣ ΠΛΗΡΟΦΟΡΙΕΣ -->
        <section class="profile-section">

            <h2>Πρόσθετες πληροφορίες</h2>

            <p>
                <c:out value="${customer.moreInfo}"/>
            </p>

        </section>

        <!-- ΕΠΕΞΕΡΓΑΣΙΑ ΠΕΛΑΤΗ -->
        <div class="profile-actions">

            <a href="${pageContext.request.contextPath}/customers/edit">
                Επεξεργασία στοιχείων πελάτη
            </a>

        </div>

        <hr>
        
        <form action="${pageContext.request.contextPath}/customers/delete" method="post">

		    <input type="hidden"
		           name="${_csrf.parameterName}"
		           value="${_csrf.token}">
		
		    <button type="submit"
		            onclick="return confirm('Θέλεις σίγουρα να διαγράψεις τον λογαριασμό σου; Η ενέργεια δεν αναιρείται.');">
		        Διαγραφή λογαριασμού
		    </button>

		</form>

        <!-- ΕΡΓΑΣΙΕΣ ΠΕΛΑΤΗ -->
        <section class="works-section">

            <h2>Οι εργασίες μου</h2>

            <p>
                <a href="${pageContext.request.contextPath}/customers/works/new">
                    Προσθήκη νέας εργασίας
                </a>
            </p>

            <table class="works-table">

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Είδος εργασίας</th>
                        <th>Διεύθυνση κατασκευής</th>
                        <th>Τ.Κ.</th>
                        <th>Χρέωση</th>
                        <th>Πληρωμένο ποσό</th>
                        <th>Υπόλοιπο</th>
                        <th>Πληροφορίες</th>
                        <th>Action</th>
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="work" items="${works}">

                        <tr>
                            <td>
                                <c:out value="${work.id}"/>
                            </td>

                            <td>
                                <c:out value="${work.workType}"/>
                            </td>

                            <td>
                                <c:out value="${work.constructionAddress.name}"/>
                            </td>

                            <td>
                                <c:out value="${work.constructionAddress.postcode}"/>
                            </td>

                            <td>
                                <c:out value="${work.chargeCost}"/>
                            </td>

                            <td>
                                <c:out value="${work.paidCharge}"/>
                            </td>

                            <td>
                                <strong>
                                    <c:out value="${work.remainingCharge}"/>
                                </strong>
                            </td>

                            <td>
                                <c:out value="${work.moreInfo}"/>
                            </td>

                            <td class="work-actions">

                                <!-- ΕΠΕΞΕΡΓΑΣΙΑ ΕΡΓΑΣΙΑΣ -->
                                <a href="${pageContext.request.contextPath}/customers/works/${work.id}/edit">
                                    Επεξεργασία
                                </a>

                                <!-- ΔΙΑΓΡΑΦΗ ΕΡΓΑΣΙΑΣ -->
                                <form
                                    action="${pageContext.request.contextPath}/customers/works/${work.id}/delete"
                                    method="post"
                                    class="delete-work-form">

                                    <input type="hidden"
                                           name="${_csrf.parameterName}"
                                           value="${_csrf.token}">

                                    <button type="submit"
                                            onclick="return confirm('Θέλεις σίγουρα να διαγράψεις αυτή την εργασία;');">
                                        Διαγραφή
                                    </button>

                                </form>

                            </td>
                        </tr>

                    </c:forEach>

                    <!-- ΟΤΑΝ ΔΕΝ ΥΠΑΡΧΟΥΝ ΕΡΓΑΣΙΕΣ -->
                    <c:if test="${empty works}">

                        <tr>
                            <td colspan="9">
                                Δεν υπάρχουν καταχωρημένες εργασίες.
                            </td>
                        </tr>

                    </c:if>

                </tbody>

            </table>

        </section>

        <hr>

        <!-- ΑΠΟΣΥΝΔΕΣΗ -->
        <div class="logout-section">

            <form action="${pageContext.request.contextPath}/logout"
                  method="post">

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}">

                <button type="submit">
                    Αποσύνδεση
                </button>

            </form>

        </div>

    </main>

</body>
</html>
