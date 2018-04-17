package store.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    private String id;

    private String ownerId;

    private String storeName;

    public Store(String ownerId, String storeName) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.ownerId = ownerId;
        this.storeName = storeName;
    }

    public Store() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
