INSERT INTO books (id, title, author, description, price, stock, cover_image_url)
VALUES (nextval('book_sequence'), 'Clean Code', 'Robert C. Martin', 'A Handbook of Agile Software Craftsmanship', 42.99, 5, 'http://example.com/cleancode.jpg'),
    (nextval('book_sequence'), 'Refactoring', 'Martin Fowler', 'Improving the Design of Existing Code', 47.99, 3, 'http://example.com/refactoring.jpg');

