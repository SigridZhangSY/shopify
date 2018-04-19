package price.web.rest;

import price.depend.ProductsClient;
import price.domain.Price;
import price.repository.PriceRepository;
import price.support.ApiTest;
import price.support.TestHelper;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.*;

import static io.restassured.RestAssured.given;

public class PricesResourceTest extends ApiTest {

    @MockBean
    private PriceRepository mockPriceRepository;

    @MockBean
    private ProductsClient mockProductsClient;

    @Test
    public void should_201_when_create_price() throws Exception {
        Map<String, Object> priceMap = TestHelper.priceMap(20.0f);

        String productId = "product-id";
        Price price = new Price(productId, 20.0f);
        when(mockPriceRepository.save(any())).thenReturn(price);
        when(mockProductsClient.getProduct(eq(productId))).thenReturn(new HashMap());
        given()
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(priceMap)
                .when()
                .post("/products/" +  productId +"/price-list")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .headers(TestHelper.headerMap("products/" + productId + "/prices/" + price.getId()));
    }

    @Test
    public void should_400_when_create_price_with_invalid_parameter() throws Exception {
        String productId = "product-id";
        Price price = new Price(productId, 20.0f);

        given()
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(new HashMap<>())
                .when()
                .post("/products/" +  productId +"/price-list")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void should_200_when_get_price_list_of_product() throws Exception {
        String productId = "product-id";
        Price price = new Price(productId, 20.0f, new Timestamp(System.currentTimeMillis()));
        when(mockPriceRepository.findByProductId(eq(productId)))
                .thenReturn(new ArrayList<Price>(){{add(price);}});
        when(mockProductsClient.getProduct(eq(productId))).thenReturn(new HashMap());
        given()
                .port(port)
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + productId + "/price-list")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("items.value", hasItems(20.0f))
                .body("items.createdAt", hasItems(notNullValue()));
    }

    @Test
    public void should_200_when_get_product_current_price() throws Exception {
        String productId = "product-id";
        Price price = new Price(productId, 20.0f, new Timestamp(System.currentTimeMillis()));

        when(mockProductsClient.getProduct(eq(productId))).thenReturn(new HashMap());
        when(mockPriceRepository.findTopByProductIdOrderByCreatedAtDesc(productId)).thenReturn(Optional.of(price));

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + productId + "/current-price")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("value", is(20.0f))
                .body("createdAt", notNullValue());
    }

    @Test
    public void should_404_when_get_current_price_of_product_without_price() throws Exception {
        String productId = "product-id";

        when(mockPriceRepository.findTopByProductIdOrderByCreatedAtDesc(productId)).thenReturn(Optional.empty());

        given()
                .port(port)
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + productId + "/current-price")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
