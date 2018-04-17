package hello.web.serializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;

@JsonSerialize(using = RecordSerializer.class)
public interface Record {
    Map<String, Object> toRefJson();

    Map<String, Object> toJson();
}
