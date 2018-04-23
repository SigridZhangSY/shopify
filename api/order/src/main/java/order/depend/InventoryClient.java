package order.depend;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("INVENTORY-SERVICE")
public interface InventoryClient {
    @GetMapping(value = "/products/{product-id}/current-inventory")
    Map getCurrentInventoryOfProduct(@PathVariable("product-id")String productId);

    @PostMapping(value = "/products/{product-id}/inventory-requests")
    Map createInventoryRequestOfProduct(@PathVariable("product-id")String productId, @RequestBody Map<String, Object> info);
}
