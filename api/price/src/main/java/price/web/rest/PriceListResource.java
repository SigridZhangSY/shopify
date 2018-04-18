package price.web.rest;

import jdk.nashorn.internal.runtime.options.Option;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import price.domain.Price;
import price.repository.PriceRepository;
import price.web.Routes;
import price.web.serializer.Page;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

public class PriceListResource {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private Routes routes;

    private String productId;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("price-list")
    public Response createPrice(Map<String, Object> info) {
        if(!info.containsKey("priceValue")) {
            throw new BadRequestException();
        }

        Float priceValue = Float.valueOf(info.get("priceValue").toString());
        Price price = new Price(productId, priceValue);
        Price fetch = priceRepository.save(price);

        return Response.status(201).location(routes.priceUrl(fetch)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("price-list")
    public Page<Price> getPriceList() {
        List<Price> priceList = priceRepository.findByProductId(productId);

        return new Page(priceList);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("current-price")
    public Price getCurrentPrice() {
        return priceRepository
                .findTopByProductIdOrderByCreatedAtDesc(productId)
                .orElseThrow(() -> new NotFoundException());
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
