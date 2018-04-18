package price.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import price.depend.ProductClient;
import price.repository.PriceRepository;
import org.springframework.stereotype.Component;
import price.web.Routes;

import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import java.util.Map;

@Component
@Path("products")
public class ProductsResource {

    @Autowired
    private ProductClient productClient;

    @Context
    private ResourceContext resourceContext;

    @Path("{product-id}")
    public PriceListResource getPriceListResource(@PathParam("product-id") String productId) {
        //todo: customized feign exception handler
        Map product = productClient.getProduct(productId);
        PriceListResource priceListResource = resourceContext.getResource(PriceListResource.class);
        priceListResource.setProductId(productId);
        return priceListResource;
    }

}
