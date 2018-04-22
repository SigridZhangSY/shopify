package price.domain;

import price.web.serializer.Record;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "prices")
public class Price implements Record{
    @Id
    private String id;

    private String productId;

    private float priceValue;

    @Column(name = "created_at",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Price(String productId, float priceValue) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.productId = productId;
        this.priceValue = priceValue;
    }

    public Price(String productId, float priceValue, Timestamp createdAt) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.productId = productId;
        this.priceValue = priceValue;
        this.createdAt = createdAt;
    }

    public Price() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public float getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(float priceValue) {
        this.priceValue = priceValue;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public Map<String, Object> toRefJson() {
        return toJson();
    }

    @Override
    public Map<String, Object> toJson() {
        return new HashMap<String, Object>(){{
            put("value", priceValue);
            put("createdAt", createdAt);
            put("links", new HashMap<String, Object>(){{
                put("self", "/products/" + productId + "/price-list/" + id);
                put("product", "/products/" + productId);
            }});
        }};
    }
}
