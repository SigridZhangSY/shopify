CREATE TABLE prices (
  id VARCHAR(255) PRIMARY KEY,
  product_id VARCHAR(255) NOT NULL,
  price_value FLOAT NOT NULL,
  created_at TIMESTAMP
);