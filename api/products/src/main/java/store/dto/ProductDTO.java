package store.dto;

import store.web.serializer.Record;

import java.util.HashMap;
import java.util.Map;

public class ProductDTO implements Record {
    private String id;

    private String productName;

    private String description;

    private String storeId;


    @Override
    public Map<String, Object> toRefJson() {
        return new HashMap<String, Object>(){{
            put("name", productName);
            put("links", new HashMap<String, Object>(){{
                put("self", "/products/" + id);
                put("currentPrice", "/products/" + id + "/current-price");
                put("store", "/stores/" + storeId);
            }});
        }};
    }

    @Override
    public Map<String, Object> toJson() {
        Map map = toRefJson();
        map.put("description", description);
        return map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
