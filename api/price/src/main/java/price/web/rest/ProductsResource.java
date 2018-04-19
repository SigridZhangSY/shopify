package price.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import price.depend.ProductsClient;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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
    public PriceListResource getPriceListResource(@PathParam("product-id") String productId) {
        //todo: customized feign exception handler
        Map product = productsClient.getProduct(productId);
        PriceListResource priceListResource = resourceContext.getResource(PriceListResource.class);
        priceListResource.setProductId(productId);
        return priceListResource;
    }

}
