CREATE TABLE inventory_requests (
  id VARCHAR(255) PRIMARY KEY,
  amount INT NOT NULL,
  request_type VARCHAR(255) NOT NULL,
  order_item_url VARCHAR(255),
  product_id VARCHAR(255) NOT NULL,
  created_at TIMESTAMP
);

CREATE TABLE inventory (
  id VARCHAR(255) PRIMARY KEY,
  amount INT NOT NULL,
  inventory_request_id VARCHAR(255) NOT NULL,
  created_at TIMESTAMP,
  product_id VARCHAR(255) NOT NULL,
  FOREIGN KEY (inventory_request_id) REFERENCES inventory_requests(id)
);

