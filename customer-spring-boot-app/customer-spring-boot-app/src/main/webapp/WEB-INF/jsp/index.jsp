<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="el">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>CustomerApp - Welcome</title>

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

            <header class="auth-header">
                <h1>Καλώς ήρθατε στο CustomerApp!</h1>

                <p>
                    Διαχειριστείτε τα προσωπικά σας στοιχεία
                    και τις εργασίες σας εύκολα και γρήγορα.
                </p>
            </header>

            <div class="form-actions">

                <a class="btn btn-primary"
                   href="${pageContext.request.contextPath}/login">
                    Σύνδεση
                </a>

                <a class="btn btn-secondary"
                   href="${pageContext.request.contextPath}/register">
                    Εγγραφή
                </a>

            </div>

        </section>

    </main>

</body>
</html>