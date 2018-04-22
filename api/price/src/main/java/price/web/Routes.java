package price.web;

import org.springframework.stereotype.Component;
import price.domain.Price;

import java.net.URI;

@Component
public class Routes {

    public URI priceUrl(Price price) {
        return URI.create(String.format("/products/%s/prices/%s", price.getProductId(), price.getId()));
    }
}
