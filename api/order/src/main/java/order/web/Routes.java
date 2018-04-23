package order.web;

import order.domain.Order;
import order.domain.OrderItem;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class Routes {

    public URI orderUrl(Order order) {
        return URI.create(String.format("/orders/%s", order.getId()));
    }

    public URI orderItemUrl(OrderItem orderItem) {
        return URI.create(String.format("/orders/%s/order-items/%s", orderItem.getOrder().getId(), orderItem.getId()));
    }
}
