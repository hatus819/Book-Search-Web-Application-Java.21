# Book Search Web Application Prototype

## Overview
This is a prototype of a Book Search Web Application built using modern Java technologies and web standards. The application allows users to search for books by title, author, or genre from a MySQL database and displays the results in a responsive, user-friendly interface.

## Features
- Search books by title, author, or genre with partial matching.
- Responsive frontend design using HTML, CSS, and JavaScript.
- Backend implemented with Java 21 Servlets handling search queries.
- MySQL database integration for storing and retrieving book data.
- JSON-based communication between frontend and backend.
- Simple and clean UI with a search bar and results table.

## Technology Stack
- **Backend:** Java 21, Jakarta Servlets
- **Frontend:** HTML5, CSS3, JavaScript (Vanilla)
- **Database:** MySQL
- **Build Tool:** Maven
- **Servlet Container:** Jetty (via Maven plugin)
- **Version Control:** Git, GitHub

## Project Structure
- `src/main/java/com/example/BookSearchServlet.java` - Java servlet handling search requests.
- `src/main/webapp/index.html` - Main HTML page with search UI.
- `src/main/webapp/style.css` - Styling for the frontend.
- `src/main/webapp/script.js` - JavaScript for handling search and displaying results.
- `src/main/webapp/WEB-INF/web.xml` - Servlet configuration.
- `books.sql` - SQL script to create the database schema and sample data.
- `pom.xml` - Maven project configuration.

## How to Run
1. Set up a MySQL database and run `books.sql` to create the schema and sample data.
2. Update the database credentials in `BookSearchServlet.java`.
3. Build the project using Maven: `mvn clean package`.
4. Run the Jetty server using Maven: `mvn jetty:run`.
5. Access the application at `http://localhost:8080/booksearch`.

## Notes for Recruiters
- This prototype demonstrates backend and frontend integration using Java Servlets and modern web technologies.
- The project showcases skills in Java 21, servlet programming, RESTful API design, frontend development, and database interaction.
- The application is designed to be simple, extensible, and easy to deploy.
- Further enhancements can include advanced search filters, pagination, user authentication, and deployment automation.

---

Thank you for reviewing this project prototype. Please feel free to reach out for any questions or further demonstrations.
