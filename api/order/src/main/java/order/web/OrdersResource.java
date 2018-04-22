package order.web;

import order.domain.Order;
import order.domain.OrderItem;
import order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Component
@Path("orders")
public class OrdersResource {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Routes routes;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(Map<String, Object> info){
        if(!info.containsKey("userId") || !info.containsKey("orderItems")) {
            throw new BadRequestException("order map should contain userId and orderItems");
        }
        String userId = info.get("userId").toString();
        List<Map> orderItemsMap = (List<Map>) info.get("orderItems");

        if(orderItemsMap.size() > 0) {
            Order order = new Order(userId);
            orderItemsMap.stream().forEach(itemMap -> {
                if(!itemMap.containsKey("productPriceUrl") || !itemMap.containsKey("amount")) {
                    throw new BadRequestException("orderItem map should contain productPriceUrl and amount");
                }
                String priceUrl = itemMap.get("productPriceUrl").toString();
                int amount = Integer.parseInt(itemMap.get("amount").toString());
                OrderItem orderItem = new OrderItem(order, priceUrl, amount);
                order.addOrderItem(orderItem);
            });
            Order fetch = orderRepository.save(order);
            return Response.created(routes.orderUrl(fetch)).build();
        } else {
            throw new BadRequestException("order items should more than zero");
        }
    }
}
