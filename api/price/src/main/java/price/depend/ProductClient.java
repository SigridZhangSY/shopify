package price.depend;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import price.dto.ProductDto;

import java.util.Map;

@FeignClient("PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping(value = "/products/{product-id}")
    Map getProduct(@PathVariable("product-id")String productId);

}
