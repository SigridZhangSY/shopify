package price.support;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;

public class TestHelper {
    public static Map<String, Object> priceMap(float priceValue) {
        return new HashMap<String, Object>() {{
            put("priceValue", priceValue);
        }};
    }

    public static Map<String, Object> headerMap(String location) {
        return new HashMap<String, Object>() {{
            put("Location", containsString(location));
        }};
    }
}

