CREATE TABLE orders (
  id VARCHAR(255) PRIMARY KEY,
  user_id VARCHAR(255) NOT NULL,
  amount FLOAT NOT NULL,
  created_at TIMESTAMP
);

CREATE TABLE order_items (
  id VARCHAR(255) PRIMARY KEY,
  order_id VARCHAR(255) NOT NULL,
  product_count INT NOT NULL,
  product_price FLOAT NOT NULL,
  product_price_url VARCHAR(255) NOT NULL,
  created_at TIMESTAMP,
  FOREIGN KEY (order_id) REFERENCES orders(id)
);