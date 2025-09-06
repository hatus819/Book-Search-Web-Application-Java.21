-- Create database
CREATE DATABASE IF NOT EXISTS booksearch;

-- Use the database
USE booksearch;

-- Create books table
CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    published_year INT,
    isbn VARCHAR(20)
);

-- Insert sample data
INSERT INTO books (title, author, genre, published_year, isbn) VALUES
('The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 1925, '978-0-7432-7356-5'),
('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 1960, '978-0-06-112008-4'),
('1984', 'George Orwell', 'Dystopian', 1949, '978-0-452-28423-4'),
('Pride and Prejudice', 'Jane Austen', 'Romance', 1813, '978-0-14-143951-8'),
('The Catcher in the Rye', 'J.D. Salinger', 'Fiction', 1951, '978-0-316-76948-0');
