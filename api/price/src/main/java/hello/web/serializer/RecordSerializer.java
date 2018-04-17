package hello.web.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class RecordSerializer extends StdSerializer<Record> {

    public RecordSerializer() {
        this(null);
    }

    public RecordSerializer(Class<Record> t) {
        super(t);
    }

    @Override
    public void serialize(Record value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeObject(value.toJson());
    }
}
