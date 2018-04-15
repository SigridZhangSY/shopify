package store.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StoreDTO {
    @JsonIgnore
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
}
