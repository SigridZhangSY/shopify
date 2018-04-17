package hello.web.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
public class PricesResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/products/{product-id}/price-list")
    public Response createPrice(@PathParam("product-id") String productId) {
        return Response.status(201).build();
    }
}
