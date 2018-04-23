package order.depend;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient("PRICE-SERVICE")
public interface PriceClient {
    @GetMapping(value = "/products/{product-id}/current-price")
    Map getCurrentPriceOfProduct(@PathVariable("product-id")String productId);
}
