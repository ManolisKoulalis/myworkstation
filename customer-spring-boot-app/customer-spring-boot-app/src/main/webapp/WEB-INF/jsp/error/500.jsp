
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>500 - Σφάλμα Διακομιστή | CustomerApp</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="error-page">

    <!-- Navigation Bar -->
    <nav class="dashboard-nav">

        <a class="nav-brand"
           href="${pageContext.request.contextPath}/login">
            CustomerApp
        </a>

    </nav>

    <main class="error-container">

        <section class="card error-card">

            <div class="error-code" aria-hidden="true">
                500
            </div>

            <h1>Παρουσιάστηκε κάποιο πρόβλημα</h1>

            <p class="error-description">
                Δεν ήταν δυνατή η ολοκλήρωση
                του αιτήματός σας.
            </p>

            <p class="error-help">
                Παρακαλώ δοκιμάστε ξανά αργότερα.
                Αν το πρόβλημα συνεχιστεί,
                επικοινωνήστε με τον διαχειριστή
                της εφαρμογής.
            </p>

            <div class="form-actions">

                <a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/login">
                    Επιστροφή στη Σύνδεση
                </a>

            </div>

        </section>

    </main>

</body>
</html>
