# CustomerSpringBootApp

### Customer & Work Management System | Java Full-Stack Portfolio Project

CustomerSpringBootApp is a full-stack web application for managing customer accounts, personal information, addresses, and customer-related work records. It was developed as a hands-on Java learning project and evolved into a complete portfolio application.

The application uses **Spring Boot and Spring MVC** on the server, **JSP** to render pages, **Spring Security** for authentication, and **JPA/Hibernate with MySQL** for data persistence. Its user interface is built with **HTML, CSS, JavaScript, and jQuery UI**.

Unlike a REST-based single-page application, CustomerSpringBootApp follows a **server-rendered MVC architecture**: controllers process requests and return JSP views, while the service and persistence layers handle application logic and database operations.

## Features

### Public pages and authentication

- **Welcome page:** A landing page at `/` with navigation to sign in or create an account.
- **Customer registration:** A registration form for customer information, credentials, date of birth, and addresses.
- **Form validation:** Server-side validation of submitted data, with validation feedback displayed in the UI.
- **Secure login:** Form-based authentication through Spring Security.
- **Password protection:** Passwords are hashed using BCrypt rather than stored as plain text.
- **Logout:** Authenticated users can end their session securely.
- **Protected pages:** Customer-specific functionality is available only after authentication.

### Customer profile management

After signing in, a customer is directed to their profile, where they can:

- View their stored personal details.
- View home and work address information.
- Edit their customer information.
- Delete their account.
- See the work records associated with their profile.

### Work record management

The application supports the full lifecycle of customer-related work records:

- **Create:** Add a new work record.
- **Read:** View existing records in the customer's profile.
- **Update:** Open a dedicated edit form and update a record.
- **Delete:** Remove a record.

Work records are associated with customers through the application's persistence model. Dedicated request and response DTOs are used to transfer work data between application layers.

### User interface

- Responsive layouts for desktop, tablet, and mobile screens.
- Reusable UI styling for navigation, cards, tables, forms, buttons, and feedback messages.
- A customized **jQuery UI Datepicker** for the birth-date field, styled to match the application's lilac theme.
- Month and year selection in the datepicker.
- Prevention of future birth-date selection in the datepicker, alongside server-side validation.
- Styled error and success messages, as well as dedicated error-page views.

## Technology Stack

| Area | Technologies |
| --- | --- |
| Programming language | Java 21 |
| Application framework | Spring Boot 4.0.8 |
| Web layer | Spring MVC, JSP, JSP tag libraries |
| Authentication and authorization | Spring Security 7, BCrypt |
| Data access | Spring Data JPA, Hibernate |
| Database | MySQL 8 |
| Frontend | HTML5, CSS3, JavaScript, jQuery, jQuery UI |
| Build and dependency management | Maven |
| Packaging | WAR |
| Application server | Embedded Apache Tomcat |

## How the Application Works

### 1. Registration

A visitor opens `/register` and completes the registration form. The Spring MVC controller receives the submitted data through a **customer request DTO**. Validation runs before the customer is saved. On successful registration, the user is redirected to the login page.

### 2. Login

The customer submits their username and password using the login form. Spring Security handles the authentication request, loads account details through the application's user-details service, and checks the supplied password against the stored BCrypt hash. After successful authentication, the customer is redirected to `/customers/profile`.

### 3. Profile display

The profile controller retrieves the relevant customer information and work records and provides them to the JSP view. JSP renders the profile details and a table of associated work records on the server.

### 4. Updating customer information

The customer opens an edit form, updates their information, and submits it. The request is validated and passed to the application's business logic and persistence layer before the updated profile is displayed.

### 5. Managing work records

The customer uses dedicated forms to add or edit work records. Work identifiers are used to address existing records for editing and deletion. The application persists these changes through JPA/Hibernate and displays the updated data on the profile page.

## Architecture

The project follows a layered Spring MVC design:

```text
Browser
  |
  | HTTP requests / form submissions
  v
Spring Security Filter Chain
  |
  v
Spring MVC Controllers
  |
  | Request DTOs / validation
  v
Service Layer
  |
  v
Persistence Layer (JPA / Hibernate)
  |
  v
MySQL Database

Controllers -- model data --> JSP Views
JSP Views -- HTML/CSS/JavaScript --> Browser
```

### Responsibilities of each layer

- **Controllers** handle routes, form submissions, validation results, redirects, and view selection.
- **DTOs** define the data transferred into and out of web-layer operations, rather than exposing persistence entities directly in every interaction.
- **Services** coordinate application operations and business logic.
- **Entities and JPA/Hibernate** map Java objects and their relationships to database records.
- **Spring Security** handles login, logout, password verification, and access to protected routes.
- **JSP views** generate server-rendered pages, while CSS and JavaScript control presentation and client-side interaction.

## Main Application Routes

| HTTP method | Route | Description |
| --- | --- | --- |
| GET | `/` | Public welcome page |
| GET | `/register` | Display customer registration form |
| POST | `/register` | Validate and submit customer registration |
| GET | `/login` | Display login form |
| POST | `/login` | Spring Security login processing |
| GET | `/customers/profile` | Display authenticated customer profile |
| GET | `/customers/edit` | Display customer edit form |
| GET | `/customers/works/new` | Display new work form |
| GET | `/customers/works/{workId}/edit` | Display existing work edit form |

Additional form submissions handle customer updates, account deletion, work creation, work updates, and work deletion.

## Project Structure

The application's base Java package is `com.customers.customerapp`.

```text
CustomerSpringBootApp/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   └── com/customers/customerapp/
        │       ├── Controllers
        │       ├── Services
        │       ├── DTOs
        │       ├── Entities
        │       ├── Persistence components
        │       └── Security configuration
        ├── resources/
        │   ├── application.properties
        │   └── static/
        │       └── css/
        │           └── style.css
        └── webapp/
            └── WEB-INF/
                └── jsp/
                    ├── index.jsp
                    ├── login.jsp
                    ├── register.jsp
                    ├── profile.jsp
                    ├── customer-edit.jsp
                    ├── work-add.jsp
                    ├── work-edit.jsp
                    └── error views
```

The Java directory labels describe the logical application layers rather than asserting exact folder names.

## Security and Data Validation

Security is implemented with Spring Security and a database-backed user-details service.

- Public access is allowed for the landing page, registration, login, and required static resources.
- Profile and customer-management routes require authentication.
- Login is processed by Spring Security rather than by a manually implemented password comparison in a controller.
- BCrypt hashes protect stored passwords.
- Spring MVC form validation checks submitted customer data.
- The birth-date control disallows future dates in the browser, while backend validation provides an independent check.

## Getting Started

### Requirements

- **JDK 21**
- **Maven**
- **MySQL 8**
- A Java IDE such as Eclipse or IntelliJ IDEA (optional)

### 1. Obtain the project

Download or clone the repository using the **Code** menu on its GitHub page, then open a terminal in the directory containing `pom.xml`.

### 2. Prepare the database

Start MySQL and ensure the database referenced by the project's Spring datasource configuration exists and is accessible to the configured MySQL user.

The datasource connection is configured in:

```text
src/main/resources/application.properties
```

The database name, credentials, and schema-management settings must agree with the local MySQL installation. Database credentials should be kept out of public version control.

### 3. Build and run

From the project root, run:

```bash
mvn spring-boot:run
```

Alternatively, import the directory as an existing Maven project in Eclipse and run the Spring Boot application's main class.

### 4. Open the application

```text
http://localhost:8080/
```

Create a customer account through the registration page, sign in, and use the profile and work-management functionality.

**Note:** The URL above uses the default Spring Boot port. The database connection must be configured correctly before the application can use its persistence features.

## Manual Testing

The application has been run and manually tested through its main user workflows:

- Opening the welcome page and navigating to login or registration.
- Registering a customer account.
- Signing in and signing out.
- Viewing and editing customer information.
- Creating, viewing, editing, and deleting work records.
- Using the birth-date picker and form validation.
- Checking the application's page layouts and navigation.

An automated JUnit/Mockito test suite is not currently part of this project.

## Development Highlights

This project provided hands-on experience with challenges that arise when integrating multiple Java web technologies:

- Configuring Spring Security for custom login and JSP-based views.
- Understanding the interaction between Spring MVC controller routes, JSP forwarding, and security rules.
- Mapping customer/work relationships with Hibernate and JPA.
- Using DTOs to distinguish form input from data presented in the UI.
- Handling validation and displaying meaningful feedback to users.
- Integrating a JavaScript datepicker into a server-rendered registration form.
- Building a consistent responsive interface using CSS variables, Flexbox, and Grid.
- Debugging problems across the controller, security, persistence, and presentation layers.

## Project Status

**Completed portfolio project — locally tested.**

The project demonstrates a traditional, server-rendered Java full-stack application. It is not presented as a REST API, React application, or production-hosted service.

## Purpose

CustomerSpringBootApp was created to strengthen practical skills in **Java full-stack development**, especially Spring Boot, Spring MVC, authentication, relational persistence, and frontend integration. It is intended as a portfolio demonstration of building and completing an end-to-end web application.
