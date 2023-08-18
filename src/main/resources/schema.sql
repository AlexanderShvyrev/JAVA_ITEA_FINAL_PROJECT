CREATE TABLE customers (
  id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(255),
  contact VARCHAR(20)
);

CREATE TABLE orders (
   order_number INT PRIMARY KEY AUTO_INCREMENT,
   customer_id INT,
   additional_information VARCHAR(255),
   status VARCHAR(20),
   date DATE,
   FOREIGN KEY (customer_id) REFERENCES customers (id)
);