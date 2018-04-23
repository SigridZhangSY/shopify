package inventory.domain;


import inventory.web.serializer.Record;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "inventory")
public class Inventory implements Record {
    @Id
    private String id;

    private int amount;

    private String productId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_request_id", nullable = false)
    private InventoryRequest inventoryRequest;

    @Column(name = "created_at",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Inventory(String productId, int amount, InventoryRequest inventoryRequest) {
        this.productId = productId;
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.amount = amount;
        this.inventoryRequest = inventoryRequest;
    }

    public Inventory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public InventoryRequest getInventoryRequest() {
        return inventoryRequest;
    }

    public void setInventoryRequest(InventoryRequest inventoryRequest) {
        this.inventoryRequest = inventoryRequest;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return toJson();
    }

    @Override
    public Map<String, Object> toJson() {
        return new HashMap<String, Object>(){{
            put("amount", amount);
            put("created_at", createdAt);
            put("links", new HashMap<String, Object>(){{
                put("self", "/products/" + productId + "/inventory-list/" + id);
                put("inventory-request", "/products/" + productId + "/inventory-requests/" + inventoryRequest.getId());
            }});
        }};
    }
}
