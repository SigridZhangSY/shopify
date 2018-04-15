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

    private String description;

    public Product(String description) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.description = description;
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
}
