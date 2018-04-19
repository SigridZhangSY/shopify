package price.depend;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

@FeignClient("PRODUCTS-SERVICE")
public interface ProductsClient {

    @GetMapping(value = "/products/{product-id}")
    Map getProduct(@PathVariable("product-id")String productId);

}
