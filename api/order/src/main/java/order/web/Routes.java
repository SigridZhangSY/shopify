package order.web;

import order.domain.Order;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class Routes {

    public URI orderUrl(Order order) {
        return URI.create(String.format("/orders/%s", order.getId()));
    }
}
