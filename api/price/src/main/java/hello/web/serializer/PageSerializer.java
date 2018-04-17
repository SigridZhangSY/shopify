package hello.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PageSerializer extends StdSerializer<Page<? extends Record>> {

    public PageSerializer() {
        this(null);
    }

    public PageSerializer(Class<Page<? extends Record>> t) {
        super(t);
    }

    @Override
    public void serialize(Page<? extends Record> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        List<? extends Record> entries = value.getEntries();
        gen.writeStartObject();
        gen.writeObjectField("items",entries.stream().map((record) -> record.toRefJson()).collect(toList()));
        gen.writeEndObject();
    }
}
