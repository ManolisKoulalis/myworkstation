
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Το Προφίλ μου - CustomerApp</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

    <!-- Navigation bar -->
    <nav class="dashboard-nav">

        <a class="nav-brand"
           href="${pageContext.request.contextPath}/customers/profile">
            CustomerApp
        </a>

        <ul>
            <li>
                <a class="active"
                   href="${pageContext.request.contextPath}/customers/profile">
                    Το Προφίλ μου
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/customers/edit">
                    Επεξεργασία Στοιχείων
                </a>
            </li>

            <li>
                <form action="${pageContext.request.contextPath}/logout"
                      method="post">

                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}">

                    <button type="submit">
                        Αποσύνδεση
                    </button>

                </form>
            </li>
        </ul>

    </nav>

    <main class="main-content">

        <!-- Καλωσόρισμα -->
        <header class="page-header">

            <div>
                <h1>
                    Καλώς ήρθατε,
                    <c:out value="${customer.name}"/>
                    <c:out value="${customer.surname}"/>
                </h1>

                <p>
                    Επισκόπηση του λογαριασμού
                    και των εργασιών σας.
                </p>
            </div>

        </header>

        <!-- Μήνυμα επιτυχίας -->
        <c:if test="${not empty successMessage}">
            <div class="success-message">
                <c:out value="${successMessage}"/>
            </div>
        </c:if>

        <!-- Στοιχεία πελάτη και διευθύνσεις -->
        <section class="cards-grid">

            <!-- Κάρτα πελάτη -->
            <div class="card">

                <div class="card-header">

                    <h2>Στοιχεία Πελάτη</h2>

                    <a class="btn btn-success"
                       href="${pageContext.request.contextPath}/customers/edit">
                        Επεξεργασία
                    </a>

                </div>

                <dl class="details-list">

                    <div class="detail-row">
                        <dt>Ονοματεπώνυμο:</dt>
                        <dd>
                            <c:out value="${customer.name}"/>
                            <c:out value="${customer.surname}"/>
                        </dd>
                    </div>

                    <div class="detail-row">
                        <dt>Username:</dt>
                        <dd>
                            <c:out value="${customer.username}"/>
                        </dd>
                    </div>

                    <div class="detail-row">
                        <dt>Α.Φ.Μ.:</dt>
                        <dd>
                            <c:out value="${customer.afm}"/>
                        </dd>
                    </div>

                    <div class="detail-row">
                        <dt>Φύλο:</dt>
                        <dd>
                            <c:out value="${customer.gender}"/>
                        </dd>
                    </div>

                    <div class="detail-row">
                        <dt>Ημερομηνία Γέννησης:</dt>
                        <dd>
                            <c:out value="${customer.birthdate}"/>
                        </dd>
                    </div>

                    <div class="detail-row">
                        <dt>Επιπλέον Πληροφορίες:</dt>
                        <dd>
                            <c:out value="${customer.moreInfo}"
                                   default="-"/>
                        </dd>
                    </div>

                </dl>

            </div>

            <!-- Κάρτα διευθύνσεων -->
            <div class="card">

                <div class="card-header">
                    <h2>Διευθύνσεις</h2>
                </div>

                <div class="address-item">

                    <h3>Διεύθυνση Κατοικίας</h3>

                    <p>
                        <c:out value="${customer.homeAddress.streetName}"/>
                    </p>

                    <p>
                        Τ.Κ.
                        <c:out value="${customer.homeAddress.postcode}"/>
                    </p>

                </div>

                <div class="address-item">

                    <h3>Διεύθυνση Εργασίας</h3>

                    <p>
                        <c:out value="${customer.workAddress.streetName}"/>
                    </p>

                    <p>
                        Τ.Κ.
                        <c:out value="${customer.workAddress.postcode}"/>
                    </p>

                </div>

            </div>

        </section>

        <!-- Εργασίες -->
      <section class="card" id="works">

            <div class="card-header">

                <h2>Οι Εργασίες μου</h2>

                <a class="btn btn-success"
                   href="${pageContext.request.contextPath}/customers/works/new">
                    + Προσθήκη Νέας Εργασίας
                </a>

            </div>

            <c:choose>

                <c:when test="${empty works}">

                    <p class="empty-message">
                        Δεν έχετε καταχωρήσει ακόμη εργασίες.
                    </p>

                </c:when>

   <c:otherwise>

             <div class="table-container">

                        <table class="works-table">

                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Περιγραφή Εργασίας</th>
                                    <th>Διεύθυνση Κατασκευής</th>
                                    <th>Χρέωση</th>
                                    <th>Πληρωμή</th>
                                    <th>Υπόλοιπο</th>
                                    <th>Επιπλέον Πληροφορίες</th>
                                    <th>Ενέργειες</th>
                                </tr>
          					  </thead>

      <tbody>

              <c:forEach var="work" items="${works}">

                   <tr>

                      <td>	<c:out value="${work.id}"/></td>

                       <td> <c:out value="${work.workType}"/></td>

                        <td>
                            <c:out value="${work.constructionAddress.streetName}"/>
                             <br>
   							 Τ.Κ.
                             <c:out value="${work.constructionAddress.postcode}"/>
                        </td>

                         <td><c:out value="${work.chargeCost}"/> €</td>

                         <td><c:out value="${work.paidCharge}"/> €</td>

                         <td><c:out value="${work.remainingCharge}"/> €</td>

                         <td><c:out value="${work.moreInfo}" default="-"/></td>

                         <td>

                            <div class="table-actions">

                               <a class="btn btn-success" href="${pageContext.request.contextPath}/customers/works/${work.id}/edit">Επεξεργασία</a>

                               <form action="${pageContext.request.contextPath}/customers/works/${work.id}/delete" method="post">

                                	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                                	 <button class="btn btn-danger" type="submit">Διαγραφή</button>

                                  </form>

                              </div>

                         </td>

                    </tr>
				
				</c:forEach>

            </tbody>

         </table>

       </div>

    </c:otherwise>

  </c:choose>

</section>

        <!-- Διαγραφή λογαριασμού -->
        <section class="account-actions">

            <form action="${pageContext.request.contextPath}/customers/delete"
                  method="post">

                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}">

                <button class="btn btn-danger"
                        type="submit">
                    Διαγραφή Λογαριασμού
                </button>

            </form>

        </section>

    </main>

</body>
</html>
