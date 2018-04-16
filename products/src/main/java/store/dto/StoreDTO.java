package store.dto;

import store.web.handler.Record;

import java.util.HashMap;
import java.util.Map;

public class StoreDTO implements Record{
    private String id;
    private String ownerId;
    private String url;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public String getUrl() {
        return "/stores/" + id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public Map<String, Object> toRefJson() {
        return toJson();
    }

    @Override
    public Map<String, Object> toJson() {
        return new HashMap<String, Object>(){{
            put("self", "/stores/" + id);
            put("products", "/stores/" + id +"/products");
        }};
    }
}
