package store.support;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;

public class TestHelper {
    public static Map<String, Object> productMap(String description) {
        return new HashMap<String, Object>() {{
            put("description", description);
        }};
    }

    public static Map<String, Object> headerMap(String location) {
        return new HashMap<String, Object>() {{
            put("Location", containsString(location));
        }};
    }
}
