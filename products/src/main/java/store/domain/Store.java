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

    public Store(String ownerId) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.ownerId = ownerId;
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
}
