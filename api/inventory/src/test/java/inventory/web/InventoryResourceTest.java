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
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class InventoryResourceTest extends ApiTest{
    @MockBean
    private ProductsClient productsClient;

    @MockBean
    private InventoryRequestRepository inventoryRequestRepository;

    @MockBean
    private InventoryRepository inventoryRepository;

    private String productId;

    private List<InventoryRequest> inventoryRequestList;

    private int amount;

    private InventoryRequest inventoryRequest;

    private Inventory inventory;

    @Before
    public void setUp() throws Exception {
        productId = "product001";
        Map productMap = new HashMap<String, Object>();
        when(productsClient.getProduct(eq(productId))).thenReturn(productMap);

        amount = 50;
        inventoryRequest = new InventoryRequest(productId, amount, InventoryRequestType.INCREASE);
        inventoryRequest.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        inventory = new Inventory(productId, amount, inventoryRequest);
        inventory.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        inventoryRequest.setInventory(inventory);
        inventoryRequestList = new ArrayList<InventoryRequest>(){{ add(inventoryRequest); }};
    }

    @Test
    public void should_201_when_create_inventory_request() throws Exception {
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
        when(inventoryRequestRepository.findByProductIdOrderByCreatedAtDesc(eq(productId))).thenReturn(inventoryRequestList);

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

    @Test
    public void should_200_when_get_current_inventory_of_product() throws Exception {
        when(inventoryRepository.findFirstByProductIdOrderByCreatedAtDesc(productId)).thenReturn(Optional.of(inventory));

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + productId + "/current-inventory")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("amount", is(50))
                .body("created_at", is(notNullValue()))
                .body("links.self", is("/products/" + productId + "/inventory-list/" + inventory.getId()))
                .body("links.inventory-request", is("/products/" + productId + "/inventory-requests/" + inventoryRequest.getId()));
    }
}
