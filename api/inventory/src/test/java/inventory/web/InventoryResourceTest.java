package inventory.web;

import inventory.depend.ProductsClient;
import inventory.domain.Inventory;
import inventory.domain.InventoryRequest;
import inventory.domain.InventoryRequestType;
import inventory.repository.InventoryRepository;
import inventory.repository.InventoryRequestRepository;
import inventory.support.ApiTest;
import inventory.support.TestHelper;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class InventoryResourceTest extends ApiTest{
    @MockBean
    private ProductsClient productsClient;

    @MockBean
    private InventoryRequestRepository inventoryRequestRepository;

    private String productId;

    @Before
    public void setUp() throws Exception {
        productId = "product001";
        Map productMap = new HashMap<String, Object>();
        when(productsClient.getProduct(eq(productId))).thenReturn(productMap);
    }

    @Test
    public void should_201_when_create_inventory_request() throws Exception {
        int amount = 50;
        InventoryRequest inventoryRequest = new InventoryRequest(productId, amount, InventoryRequestType.INCREASE);
        inventoryRequest.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        Inventory inventory = new Inventory(productId, amount, inventoryRequest);
        inventoryRequest.setInventory(inventory);
        List<InventoryRequest> inventoryRequestList = new ArrayList<InventoryRequest>(){{ add(inventoryRequest); }};
        when(inventoryRequestRepository.save(any())).thenReturn(inventoryRequestList);

        Map<String, Object> inventoryRequestMap = TestHelper.increaseInventoryRequestMap(amount);

        given()
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(inventoryRequestMap)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .headers(TestHelper.headerMap("/products/" + productId + "/inventory-requests/" + inventoryRequest.getId()));
    }

    @Test
    public void should_200_when_get_inventory_list_of_product() throws Exception {
        int amount = 50;
        InventoryRequest inventoryRequest = new InventoryRequest(productId, amount, InventoryRequestType.INCREASE);
        inventoryRequest.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        Inventory inventory = new Inventory(productId, amount, inventoryRequest);
        inventoryRequest.setInventory(inventory);
        List<InventoryRequest> inventoryRequestList = new ArrayList<InventoryRequest>(){{ add(inventoryRequest); }};
        when(inventoryRequestRepository.findByProductIdOrOrderByCreatedAtDesc(eq(productId))).thenReturn(inventoryRequestList);

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + productId + "/inventory-requests")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("items.amount", hasItems(50))
                .body("items.type", hasItems(InventoryRequestType.INCREASE.toString()))
                .body("items.created_at", hasItems(notNullValue()))
                .body("items.links.self", hasItems("/products/" + productId + "/inventory-requests/" + inventoryRequest.getId()));
    }
}
