package inventory.support;

import inventory.domain.InventoryRequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;

public class TestHelper {
   public static Map<String, Object> increaseInventoryRequestMap (int amount) {
       return new HashMap<String, Object>(){{
           put("amount", amount);
           put("type", InventoryRequestType.INCREASE);
       }};
   }

    public static Map<String, Object> headerMap(String location) {
        return new HashMap<String, Object>() {{
            put("Location", containsString(location));
        }};
    }
}
