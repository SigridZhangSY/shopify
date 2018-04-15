package store.web;

import store.domain.User;
import store.repository.UserRepository;

import store.support.ApiUnitTest;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.when;

public class UserControllerTest extends ApiUnitTest {

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    UserController userController;

    @Before
    public void setUp() throws Exception {
        setUpApi(userController);
    }

    @Test
    public void should_return_200_when_get_user() throws Exception {
        User mockUser = new User(1L, "FirstName", "LastName");

        ArrayList<User> list = new ArrayList<User>();
        list.add(mockUser);

        when(mockUserRepository.findAll()).thenReturn(list);

        given().
                when().
                get("/users").
                then().
                statusCode(HttpStatus.SC_OK).
                body("firstName", hasItem("FirstName"));
    }
}
