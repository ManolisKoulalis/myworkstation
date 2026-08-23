# Customer Management Web Application

 A Java web application for managing customers and their assigned construction works.
 The project was developed as a learning project to practice Java Web Development, MVC architecture, Hibernate ORM, relational databases, authentication, session management and CRUD operations.

 ## Features

 ### Customer Management

 - Customer registration
- Customer login and logout
- Edit customer information
- Delete customer account
- Display customer list
- Server-side and client-side form validation
- Unique AFM and username validation
- Password hashing using BCrypt

 ### Work Management

  Authenticated customers can manage their assigned works:
  
- Add a new work
- Edit an existing work
- Delete a work
- View all assigned works
- Calculate remaining charge
- Validate that paid charge cannot exceed total charge


Each work contains information such as:
  
- Work type
- Construction address
- Postcode
- Total charge
- Paid amount
- Remaining amount
- Additional information

 ## Authentication

The application uses `HttpSession` for authentication.

After a successful login, the customer's ID is stored in the session:

```java
session.setAttribute("loggedCustomerId", customer.getId());
```
Protected operations verify that a valid logged-in customer exists before continuing.
The session is invalidated when the customer logs out or deletes their account.


## Authorization

Work operations include an ownership check to prevent one customer from accessing or modifying another customer's work.

The application verifies the relationship between Customer and Work before allowing operations such as:

- Edit Work
- Update Work
- Delete Work
  
The ownership check is performed through the DAO layer using the customer ID stored in the session and the requested work ID.

## Password Security

Passwords are not stored as plain text.
The application uses BCrypt to hash passwords before storing them in the database.

Example:

```java
String hashedPassword =BCrypt.hashpw(password, BCrypt.gensalt());
```

During login, the submitted password is verified against the stored hash using:

```java
BCrypt.checkpw(password, customer.getPassword());
```

This allows password verification without storing the original password.

## Technologies
- Java
- Java Servlets
- JSP
- JSTL
- Hibernate ORM
- JPA
- MySQL
- JDBC
- BCrypt
- HttpSession
- HTML
- CSS
- JavaScript
- jQuery / jQuery UI
- Apache Tomcat

## Architecture

The project separates responsibilities between different layers:

Browser -> JSP / HTML  -> Servlet Controllers -> DAO Layer -> Hibernate / JPA -> MySQL Database

## Model

Contains the application's entities:

- Customer
- Work
- Address

## Controller Layer

Servlet controllers handle HTTP requests, validation and application flow.

Examples:

- CustomerServletController
- WorkServletController

## DAO Layer

The DAO classes are responsible for database operations.

- CustomerDao
- WorkDao

They contain operations such as:

- Save
- Find by ID
- Update
- Delete
- Search
- Ownership verification

## Database Relationships

A customer can have multiple works.

The relationship is implemented using a @OneToMany association:

```java
@OneToMany(
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
@JoinTable(
    name = "Customer_Work",
    joinColumns = @JoinColumn(name = "Customer_Id"),
    inverseJoinColumns = @JoinColumn(name = "Work_Id")
)
private List<Work> worklist;
```

The intermediate table associates customers with their works: Customer_Work (Customer_Id,Work_Id)

Embedded Address objects are also used for customer home/work addresses and construction addresses.

## Validation

The application performs validation both on the frontend and backend.

Examples include:

- Required fields
- Name and surname validation
- AFM format
- Unique AFM
- Unique username
- Postcode validation
- Birthdate validation
- Password length
- Work type validation
- Non-negative monetary values
- Paid amount cannot exceed total charge

Backend validation is performed even when equivalent frontend validation exists, since frontend validation can be bypassed.

## Session Management

After login: Succesful Login -> HttpSession -> loggedCustomerId

Protected operations use the session customer ID rather than trusting a customer ID supplied through the browser.

This prevents users from changing a customerId URL/request parameter to access another customer's account.

## Work Ownership Security

A workId can still arrive through a request, for example: /editWork?workId=15

Therefore, the application verifies that the requested work belongs to the currently authenticated customer before allowing access.

This provides a distinction between:

Authentication
"Who is the user?"

Authorization
"Is this user allowed to access this Work?"

## Project Purpose

This project was created as a learning and portfolio project to demonstrate core Java web development concepts.






  
