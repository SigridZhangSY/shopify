package store.dto;

import store.web.serializer.Record;

import java.util.HashMap;
import java.util.Map;

public class ProductDTO implements Record {
    private String id;

    private String description;

    private String storeId;


    @Override
    public Map<String, Object> toRefJson() {
        return new HashMap<String, Object>(){{
            put("self", "/stores/" + storeId + "/products/" + id);
        }};
    }

    @Override
    public Map<String, Object> toJson() {
        return new HashMap<String, Object>(){{
            put("description", description);
            put("self", "/stores/" + storeId + "/products/" + id);
        }};
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
}
