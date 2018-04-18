package price.dto;

import price.web.serializer.Record;

import java.util.HashMap;
import java.util.Map;

public class ProductDto implements Record {
    private String id;

    private String description;

    private String storeId;


    @Override
    public Map<String, Object> toRefJson() {
        return new HashMap<String, Object>(){{
            put("self", "/products/" + id);
        }};
    }

    @Override
    public Map<String, Object> toJson() {
        return new HashMap<String, Object>(){{
            put("description", description);
            put("links", new HashMap<String, Object>(){{
                put("self", new HashMap<String, Object>(){{
                    put("href", "/products/" + id);
                }});
                put("store", new HashMap<String, Object>(){{
                    put("href", "/stores/" + storeId);
                }});
            }});

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
