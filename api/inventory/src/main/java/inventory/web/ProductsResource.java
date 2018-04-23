package inventory.web;

import inventory.depend.ProductsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import java.util.Map;

@Component
@Path("products")
public class ProductsResource {
    @Autowired
    private ProductsClient productsClient;

    @Context
    private ResourceContext resourceContext;

    @Path("{product-id}")
    public InventoryResource getInventoryResource(@PathParam("product-id") String productId){
        Map product = productsClient.getProduct(productId);

        InventoryResource inventoryResource = resourceContext.getResource(InventoryResource.class);
        inventoryResource.setProductId(productId);
        return inventoryResource;
    }
}
