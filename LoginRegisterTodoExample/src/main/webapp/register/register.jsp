<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Insert title here</title>

       
    </head>

    </head>

    <body>
        <jsp:include page="../META-INF/common/header.jsp"></jsp:include>
        <div class="container">

            <h2>User Register Form</h2>
            <div class="col-md-6 col-md-offset-3">
                <div class="alert alert-success center" role="alert">
                    <p>${NOTIFICATION}</p>
                </div>

                <form action="<%=request.getContextPath()%>/register" method="post">

                    <div class="form-group">
                        <label for="uname">First Name:</label> <input type="text" class="form-control" id="uname" placeholder="First Name" name="firstName" required>
                    </div>

                    <div class="form-group">
                        <label for="uname">Last Name:</label> <input type="text" class="form-control" id="uname" placeholder="last Name" name="lastName" required>
                    </div>

                    <div class="form-group">
                        <label for="uname">User Name:</label> <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required>
                    </div>

                    <div class="form-group">
                        <label for="uname">Password:</label> <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>

                </form>
            </div>
        </div>
        <jsp:include page="../META-INF/common/footer.jsp"></jsp:include>
    </body>

    </html>