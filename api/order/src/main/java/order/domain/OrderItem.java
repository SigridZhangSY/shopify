package order.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private String productPriceUrl;

    private float productPrice;

    private int productCount;

    @Column(name = "created_at",insertable = false,updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public OrderItem(Order order, String productPriceUrl, float productPrice, int productCount) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.order = order;
        this.productPriceUrl = productPriceUrl;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }

    public OrderItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getProductPriceUrl() {
        return productPriceUrl;
    }

    public void setProductPriceUrl(String productPriceUrl) {
        this.productPriceUrl = productPriceUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }
}
