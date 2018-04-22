CREATE TABLE orders (
  id VARCHAR(255) PRIMARY KEY,
  user_id VARCHAR(255) NOT NULL,
  created_at TIMESTAMP
);

CREATE TABLE order_items (
  id VARCHAR(255) PRIMARY KEY,
  order_id VARCHAR(255) NOT NULL,
  amount INT NOT NULL,
  product_price VARCHAR(255) NOT NULL,
  created_at TIMESTAMP,
  FOREIGN KEY (order_id) REFERENCES orders(id)
);