package order.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;

public class TestHelper {
    public static Map<String, Object> orderMap(String userId, String productPriceUrl, int amount){
        return new HashMap<String, Object>(){{
            put("userId", userId);
            put("orderItems", new ArrayList<Map>(){{
                add(new HashMap<String, Object>(){{
                    put("productPriceUrl", productPriceUrl);
                    put("amount", amount);
                }});
            }});
        }};
    }

    public static Map<String, Object> headerMap(String location) {
        return new HashMap<String, Object>() {{
            put("Location", containsString(location));
        }};
    }
}
