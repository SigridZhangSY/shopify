package hello.web.serializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(using = PageSerializer.class)
public class Page<T> {
    private List<T> entries;

    public Page(List<T> entries) {
        this.entries = entries;
    }

    public List<T> getEntries() {
        return entries;
    }
}
