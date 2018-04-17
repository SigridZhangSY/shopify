package price.web.rest;

import price.domain.Price;
import price.repository.PriceRepository;
import org.springframework.stereotype.Component;
import price.web.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Component
@Path("/products/{product-id}")
public class PricesResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/price-list")
    public Response createPrice(Map<String, Object> info,
                                @PathParam("product-id") String productId,
                                @Context PriceRepository priceRepository,
                                @Context Routes routes) {
        if(!info.containsKey("priceValue")) {
            throw new BadRequestException("need priceValue");
        }
        Float priceValue = Float.valueOf(info.get("priceValue").toString());
        Price price = new Price(productId, priceValue);
        Price fetch = priceRepository.save(price);

        return Response.status(201).location(routes.priceUrl(fetch)).build();
    }
}
