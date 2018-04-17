package store.web;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import store.domain.Store;
import store.repository.StoreRepository;
import store.support.ApiUnitTest;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class StoresControllerTest extends ApiUnitTest {
    @Mock
    private StoreRepository mockStoreRepository;

    @InjectMocks
    private StoresController storesController;

    @Before
    public void setUp() throws Exception {
        setUpApi(storesController);
    }

    @Test
    public void should_return_201_when_create_store() throws Exception {
        Map<String, Object> storeParam = new HashMap<>();
        storeParam.put("ownerId", "owner-id");
        storeParam.put("name", "bookStore");
        Store mockStore = new Store("owner-id", "bookStore");
        Map expectHeaders = new HashMap();
        expectHeaders.put("Location", containsString("/stores/" + mockStore.getId()));
        when(mockStoreRepository.save(any())).thenReturn(mockStore);

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(storeParam)
                .when()
                .post("/stores")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .headers(expectHeaders);
    }

    @Test
    public void should_400_when_create_store_with_invalid_parameter() throws Exception {
        Map<String, Object> storeParam = new HashMap<>();

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(storeParam)
                .when()
                .post("/stores")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
