package order.web;

import io.restassured.http.ContentType;
import order.domain.Order;
import order.domain.OrderItem;
import order.repository.OrderRepository;
import order.support.ApiTest;
import order.support.TestHelper;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Map;

public class OrdersResourceTest extends ApiTest {
    @MockBean
    private OrderRepository mockOrderRepository;

    @Test
    public void should_201_when_create_order() throws Exception {
        String userId = "user-id";
        String productPriceUrl = "product-price-url";
        int amount = 5;
        Map<String, Object> orderMap = TestHelper.orderMap(userId, productPriceUrl, amount);
        Order order = new Order(userId);
        OrderItem orderItem = new OrderItem(order, productPriceUrl, amount);
        order.addOrderItem(orderItem);
        when(mockOrderRepository.save(any())).thenReturn(order);

        given()
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(orderMap)
                .when()
                .post("/orders")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .headers(TestHelper.headerMap("/orders/" + order.getId()));
    }
}
