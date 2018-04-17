package store.dto;

import store.web.serializer.Record;

import java.util.HashMap;
import java.util.Map;

public class StoreDTO implements Record{
    private String id;
    private String ownerId;
    private String storeName;

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

    @Override
    public Map<String, Object> toRefJson() {
        return toJson();
    }

    @Override
    public Map<String, Object> toJson() {
        return new HashMap<String, Object>(){{
            put("name", storeName);
            put("self", "/stores/" + id);
            put("products", "/stores/" + id +"/products");
        }};
    }
}
