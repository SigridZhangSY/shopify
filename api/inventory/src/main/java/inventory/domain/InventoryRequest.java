package inventory.domain;

import inventory.web.serializer.Record;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "inventory_requests")
public class InventoryRequest implements Record {
    @Id
    private String id;

    private int amount;

    private String orderItemUrl;

    private String productId;

    @Enumerated(EnumType.STRING)
    private InventoryRequestType requestType;

    @Column(name = "created_at",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "inventoryRequest")
    private Inventory inventory;

    public InventoryRequest(String productId, int amount, String orderItemUrl, InventoryRequestType inventoryRequestType) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.productId = productId;
        this.amount = amount;
        this.orderItemUrl = orderItemUrl;
        this.requestType = inventoryRequestType;
    }

    public InventoryRequest(String productId, int amount, InventoryRequestType inventoryRequestType) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.productId = productId;
        this.amount = amount;
        this.requestType = inventoryRequestType;
    }

    public InventoryRequest() {
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

    public String getOrderItemUrl() {
        return orderItemUrl;
    }

    public void setOrderItemUrl(String orderItemUrl) {
        this.orderItemUrl = orderItemUrl;
    }

    public InventoryRequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(InventoryRequestType inventoryRequestType) {
        this.requestType = inventoryRequestType;
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return toJson();
    }

    @Override
    public Map<String, Object> toJson() {
        return new HashMap<String, Object>(){{
            put("created_at", createdAt);
            put("type", requestType);
            put("amount", amount);
            put("links", new HashMap<String, Object>(){{
                put("self", "/products/" + productId + "/inventory-requests/" + id);
                if (orderItemUrl != null ) {
                    put("orderItem", orderItemUrl);
                }
            }});
        }};
    }
}
