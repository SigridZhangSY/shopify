package inventory.web;

import inventory.domain.InventoryRequest;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class Routes {
    public URI inventoryRequestUrl(InventoryRequest inventoryRequest) {
        return URI.create(String.format("/products/%s/inventory-requests/%s", inventoryRequest.getProductId(), inventoryRequest.getId()));
    }
}
