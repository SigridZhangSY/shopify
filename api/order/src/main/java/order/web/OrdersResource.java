package order.web;

import order.depend.InventoryClient;
import order.depend.PriceClient;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Path("orders")
public class OrdersResource {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Routes routes;

    @Autowired
    private PriceClient priceClient;

    @Autowired
    private InventoryClient inventoryClient;

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
                if(!itemMap.containsKey("productId") || !itemMap.containsKey("count")) {
                    throw new BadRequestException("orderItem map should contain productId and count");
                }
                String productId = itemMap.get("productId").toString();

                Map currentPrice = priceClient.getCurrentPriceOfProduct(productId);
                Float priceValue = Float.valueOf(currentPrice.get("value").toString());
                String priceUrl = ((Map) currentPrice.get("links")).get("self").toString();

                Map currentInventory = inventoryClient.getCurrentInventoryOfProduct(productId);
                int inventory = Integer.parseInt(currentInventory.get("amount").toString());
                int count = Integer.parseInt(itemMap.get("count").toString());

                if( inventory < count) {
                    throw new BadRequestException("shortage of inventory");
                }

                OrderItem orderItem = new OrderItem(order, priceUrl, priceValue, count);
                HashMap<String, Object> inventoryRequestMap = new HashMap<String, Object>() {{
                    put("amount", count);
                    put("type", "REDUCE");
                    put("orderItemUrl", routes.orderItemUrl(orderItem));
                }};
                inventoryClient.createInventoryRequestOfProduct(productId, inventoryRequestMap);

                order.addOrderItem(orderItem);
            });

            Float totalAmount = order.getOrderItems().stream().map(orderItem -> orderItem.getProductPrice() * orderItem.getProductCount())
                    .reduce(0f, (sum, amount) -> sum + amount);
            order.setAmount(totalAmount);
            Order fetch = orderRepository.save(order);
            return Response.created(routes.orderUrl(fetch)).build();
        } else {
            throw new BadRequestException("order items should more than zero");
        }
    }
}
