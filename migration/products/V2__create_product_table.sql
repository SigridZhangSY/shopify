CREATE TABLE products (
  id VARCHAR(255) PRIMARY KEY,
  store_id VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  product_name VARCHAR(255) NOT NULL,
  FOREIGN KEY (store_id) REFERENCES stores(id)
);