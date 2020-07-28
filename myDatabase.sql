CREATE DATABASE IF NOT EXISTS ebookstore;

USE ebookstore;

CREATE TABLE IF NOT EXISTS books(
 id INT NOT NULL AUTO_INCREMENT,
 title VARCHAR(50),
 author VARCHAR(50),
 qty int,
 PRIMARY KEY (id));
 
INSERT INTO BOOKS(title, author, qty) VALUES ("A Tale of Two Cities", "Charles Dickens", 30),
 ("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 40),
 ("The Lion, the Witch and the Wardrobe", "C. S. Lewis", 25),
 ("The Lord of the Rings", "J.R.R Tolkien", 37),
 ("Alice in Wonderland", "Lewis Carroll", 12);
 