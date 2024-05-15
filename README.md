# Library Management System Documentation

Welcome to the documentation for the Library Management System. This system is implemented using Spring Boot and SQL database, providing functionalities for managing books, patrons, and users (librarians and administrators).

---

## Installation

To set up the project locally, follow these steps:

1. **Clone the repository to your local machine:**
    ```bash
    git clone <repository_url>
    ```

2. **Configure the SQL database:**
    - Create a new database for the library management system.
    - Update the database configuration in the `application.properties` file with your database credentials.

3. **Build and run the application using Maven:**
    ```bash
    mvn spring-boot:run
    ```

---

## Usage

### Initial Login (Admin)

- Admin can log in using the default credentials:
    - Username: admin
    - Password: admin
- Upon successful login, the API returns a JWT token in the header response, which should be included in subsequent API requests for authentication purposes. This token should be stored securely.
- It's recommended to change the admin password after the first login.

### User Management (Admin)

- Admin can create new users and assign roles (admin or librarian).
- Each user is provided with unique login credentials.

### Patron and Book Management

- Librarians can add new patrons and books to the library catalog.
- **Patrons:** Add details such as name, contact information.
- **Books:** Include details such as title, author, genre, ISBN, and quantity.

### Library Operations

1. **Borrow Operation:**
    - Librarians can record borrow operations for patrons.
    - Specify the patron borrowing a book and the book being borrowed.
    - Update the availability status of the book.

2. **Return Operation:**
    - Librarians can record return operations when patrons return borrowed books.
    - Update the availability status of the book.

### Access Control

- Role-based access control:
    - **Admin:** Full access to user management, patron and book management, and library operations.
    - **Librarian:** Limited access to patron and book management, and performing library operations.

### Example API Requests (For Developers)

- Refer to the example API requests provided in the documentation for authentication, user creation, patron and book management, and library operations.

---

## Swagger Documentation

For detailed API documentation, developers can access the Swagger UI at:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

This concludes the documentation for the Library Management System. If you have any questions or encounter any issues, please feel free to reach out for assistance. Happy managing your library resources efficiently with our system!
