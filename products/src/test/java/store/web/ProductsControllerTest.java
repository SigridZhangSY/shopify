package store.web;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import store.domain.Product;
import store.domain.Store;
import store.repository.ProductRepository;
import store.repository.StoreRepository;
import store.support.ApiUnitTest;
import store.support.TestHelper;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class ProductsControllerTest extends ApiUnitTest {

    @Mock
    private StoreRepository mockStoreRepository;

    @Mock
    private ProductRepository mockProductRepository;

    @InjectMocks
    private ProductsController productsController;

    @Before
    public void setUp() throws Exception {
        setUpApi(productsController);
    }

    @Test
    public void should_201_when_create_product() throws Exception {
        Map<String, Object> productMap = TestHelper.productMap("Detective Fic");
        Store store = new Store("owner-id", "book store");
        String storeId = store.getId();
        Product product = new Product("Detective Fic", storeId);

        when(mockStoreRepository.findById(eq(storeId))).thenReturn(Optional.of(store));
        when(mockProductRepository.save(any())).thenReturn(product);

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(productMap)
                .when()
                .post("/stores/" + storeId + "/products")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .headers(TestHelper.headerMap("/stores/" + storeId + "/products/" + product.getId()));
    }

    @Test
    public void should_200_when_get_products_of_store() throws Exception {
        Store store = new Store("owner-id", "book store");
        String storeId = store.getId();
        Product product = new Product("Detective Fic", storeId);

        when(mockStoreRepository.findById(eq(storeId))).thenReturn(Optional.of(store));
        when(mockProductRepository.findByStoreId(eq(storeId))).thenReturn(new ArrayList<Product>(){{
            add(product);
        }});

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/stores/" + storeId + "/products")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("items.self", hasItems("/products/" + product.getId()));
    }

    @Test
    public void should_200_when_get_product_by_id() throws Exception {
        String storeId = "storeId";
        Product product = new Product("Detective Fic", storeId);

        when(mockProductRepository.findById(eq(product.getId()))).thenReturn(Optional.of(product));

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products/" + product.getId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("description", equalTo("Detective Fic"))
                .body("links.self.href", equalTo("/products/" + product.getId()))
                .body("links.store.href", equalTo("/stores/" + storeId));
    }
}
