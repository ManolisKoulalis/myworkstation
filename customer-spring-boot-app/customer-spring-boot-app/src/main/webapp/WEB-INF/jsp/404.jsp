
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>404 - Η σελίδα δεν βρέθηκε | CustomerApp</title>

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
                404
            </div>

            <h1>Η σελίδα δεν βρέθηκε</h1>

            <p class="error-description">
                Η σελίδα ή η εγγραφή που αναζητάτε
                δεν υπάρχει ή έχει διαγραφεί.
            </p>

            <p class="error-help">
                Ελέγξτε τη διεύθυνση που πληκτρολογήσατε
                ή επιστρέψτε στην αρχική σελίδα.
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
