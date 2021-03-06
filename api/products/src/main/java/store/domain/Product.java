package store.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private String id;

    private String productName;

    private String description;

    private String storeId;

    public Product(String productName, String storeId, String description) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.description = description;
        this.storeId = storeId;
        this.productName = productName;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
