
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>Σύνδεση - CustomerApp</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="auth-page">

	    <!-- Navigation Bar -->
    <nav class="dashboard-nav auth-nav">

        <a class="nav-brand" href="${pageContext.request.contextPath}/">CustomerApp</a>

        <ul>
            <li>
                <a class="active"  aria-current="page"
                   href="${pageContext.request.contextPath}/login">Σύνδεση</a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/register">Εγγραφή</a>
            </li>
        </ul>

    </nav>
	


    <main class="auth-container">

        <section class="auth-card">

            <!-- Επικεφαλίδα -->
            <header class="auth-header">

                <h1>CustomerApp</h1>

                <h2>Σύνδεση στον λογαριασμό σας</h2>

                <p>
                    Συμπληρώστε τα στοιχεία σας
                    για να συνεχίσετε.
                </p>

            </header>

            <!-- Λανθασμένα στοιχεία σύνδεσης -->
            <c:if test="${param.error != null}">
                <div class="error-message" role="alert">
                    Λανθασμένο username ή password.
                    Παρακαλώ προσπαθήστε ξανά.
                </div>
            </c:if>

            <!-- Επιτυχής αποσύνδεση -->
            <c:if test="${param.logout != null}">
				 <div class="success-message" role="status">
                    Αποσυνδεθήκατε επιτυχώς.
                </div>
            </c:if>

            <!-- Επιτυχής εγγραφή -->
            <c:if test="${not empty successMessage}">
                <div class="success-message" role="status">
                    <c:out value="${successMessage}"/>
                </div>
            </c:if>

            <!-- Αλλαγή username -->
            <c:if test="${param.usernameChanged != null}">
                <div class="success-message" role="status">
                    Το username σας άλλαξε επιτυχώς.
                    Συνδεθείτε ξανά με το νέο username.
                </div>
            </c:if>

            <!-- Διαγραφή λογαριασμού -->
            <c:if test="${param.accountDeleted != null}">
                <div class="success-message" role="status">
                    Ο λογαριασμός σας διαγράφηκε επιτυχώς.
                </div>
            </c:if>

            <!-- Φόρμα σύνδεσης -->
            <form class="auth-form"  action="${pageContext.request.contextPath}/login"  method="post">

                <div class="form-group">

                    <label for="username">Username</label>
                    <input type="text"
                           id="username"
                           name="username"
                           placeholder="Εισάγετε το username σας"
                           autocomplete="username"
                           required>
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password"
                           id="password"
                           name="password"
                           placeholder="Εισάγετε τον κωδικό σας"
                           autocomplete="current-password"
                           required>
                </div>

                <!-- CSRF Token -->
                <input type="hidden"  name="${_csrf.parameterName}" value="${_csrf.token}">

                <div class="form-actions">
                    <button class="btn btn-primary auth-submit" type="submit">Σύνδεση</button>
                </div>

            </form>

            <!-- Σύνδεσμος εγγραφής -->
            <footer class="auth-footer">

                <p>
                    Δεν έχετε λογαριασμό;
                    <a href="${pageContext.request.contextPath}/register">Δημιουργία λογαριασμού</a>
                </p>

            </footer>

        </section>

    </main>

</body>
</html>
