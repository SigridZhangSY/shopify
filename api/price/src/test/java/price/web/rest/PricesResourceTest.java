package price.web.rest;

import price.domain.Price;
import price.repository.PriceRepository;
import price.support.ApiTest;
import price.support.TestHelper;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PricesResourceTest extends ApiTest {

    @MockBean
    private PriceRepository mockPriceRepository;

    @Test
    public void should_201_when_create_price() throws Exception {
        Map<String, Object> priceMap = TestHelper.priceMap(20.0f);

        String productId = "product-id";
        Price price = new Price(productId, 20.0f);
        when(mockPriceRepository.save(any())).thenReturn(price);

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
        when(mockPriceRepository.save(any())).thenReturn(price);

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
}
