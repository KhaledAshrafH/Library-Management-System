# Library Management System

This project is a Library Management System API built using Spring Boot. It allows librarians to manage books, patrons, and borrowing records efficiently.

## Project Description

The system provides entities for managing books, patrons, and borrowing records. It includes RESTful endpoints for various operations like adding, updating, and deleting books and patrons, as well as handling book borrowings and returns.

## Features

- **API Endpoints:** Implement CRUD operations for books and patrons, along with borrowing and return functionalities.
- **Data Storage:** Utilize the H2 in-memory database for persistent storage.
- **Validation and Error Handling:** Implement input validation and error handling for API requests.
- **Transaction Management:** Ensure data integrity using Spring's @Transactional annotation.
- **Testing:** Unit testing functionality will be added soon using frameworks like JUnit and Mockito.
- **Documentation:** Provide clear instructions on running the application, interacting with APIs, and any authentication mechanisms.

## Getting Started

Follow these steps to get the project up and running on your local machine:

1. Clone the repository.
2. Configure your database connection in `application.properties` for the H2 database.
3. Run the application using your IDE or `mvn spring-boot:run`.

## API Endpoints

- **Books:**
  - `GET /api/books`: Retrieve all books.
  - `GET /api/books/{id}`: Retrieve details of a specific book.
  - `POST /api/books`: Add a new book.
  - `PUT /api/books/{id}`: Update an existing book.
  - `DELETE /api/books/{id}`: Remove a book.

- **Patrons:**
  - `GET /api/patrons`: Retrieve all patrons.
  - `GET /api/patrons/{id}`: Retrieve details of a specific patron.
  - `POST /api/patrons`: Add a new patron.
  - `PUT /api/patrons/{id}`: Update an existing patron.
  - `DELETE /api/patrons/{id}`: Remove a patron.

- **Borrowing:**
  - `POST /api/borrow/{bookId}/patron/{patronId}`: Allow a patron to borrow a book.
  - `PUT /api/return/{bookId}/patron/{patronId}`: Record the return of a borrowed book.

## Contributing

Feel free to contribute to this project by forking it and submitting a pull request with your changes.

## Future Enhancements

In the future, I plan to add more features and extend the project to enhance its functionality.


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.
