package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookSearchServlet extends HttpServlet {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/booksearch?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Hatus"; // Change this to your MySQL root password

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<Book> books = new ArrayList<>();

        if (query != null && !query.trim().isEmpty()) {
            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
                String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR genre LIKE ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    String likeQuery = "%" + query + "%";
                    stmt.setString(1, likeQuery);
                    stmt.setString(2, likeQuery);
                    stmt.setString(3, likeQuery);

                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            Book book = new Book(
                                    rs.getInt("id"),
                                    rs.getString("title"),
                                    rs.getString("author"),
                                    rs.getString("genre"),
                                    rs.getInt("published_year"),
                                    rs.getString("isbn")
                            );
                            books.add(book);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("{\"error\":\"Database error occurred.\"}");
                return;
            }
        }

        String json = toJson(books);
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
    }

    private String toJson(List<Book> books) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            sb.append("{");
            sb.append("\"id\":").append(b.getId()).append(",");
            sb.append("\"title\":\"").append(escapeJson(b.getTitle())).append("\",");
            sb.append("\"author\":\"").append(escapeJson(b.getAuthor())).append("\",");
            sb.append("\"genre\":\"").append(escapeJson(b.getGenre())).append("\",");
            sb.append("\"published_year\":").append(b.getPublishedYear()).append(",");
            sb.append("\"isbn\":\"").append(escapeJson(b.getIsbn())).append("\"");
            sb.append("}");
            if (i < books.size() - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }

    private static class Book {
        private final int id;
        private final String title;
        private final String author;
        private final String genre;
        private final int publishedYear;
        private final String isbn;

        public Book(int id, String title, String author, String genre, int publishedYear, String isbn) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.publishedYear = publishedYear;
            this.isbn = isbn;
        }

        public int getId() { return id; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getGenre() { return genre; }
        public int getPublishedYear() { return publishedYear; }
        public String getIsbn() { return isbn; }
    }
}
