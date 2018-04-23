package inventory;

import inventory.web.ProductsResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ProductsResource.class);
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
