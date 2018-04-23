package inventory.web;

import inventory.domain.Inventory;
import inventory.domain.InventoryRequest;
import inventory.domain.InventoryRequestType;
import inventory.repository.InventoryRepository;
import inventory.repository.InventoryRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;

public class InventoryResource {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryRequestRepository inventoryRequestRepository;

    @Autowired
    private Routes routes;

    private String productId;

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("inventory-request")
    public Response createInventoryRequest(Map<String, Object> info) {
        int amount = Integer.parseInt(info.get("amount").toString());
        InventoryRequestType type = InventoryRequestType.valueOf(info.get("type").toString());

        Optional<Inventory> currentInventory = inventoryRepository.findFirstByProductIdOrderByCreatedAtDesc(productId);
        int amountResult;

        if (type == InventoryRequestType.INCREASE) {

            if (currentInventory.isPresent()){
                amountResult = currentInventory.get().getAmount() + amount;
            } else {
                amountResult = amount;
            }

            InventoryRequest inventoryRequest = new InventoryRequest(productId, amount, type);
            Inventory inventory = new Inventory(productId, amountResult, inventoryRequest);
            inventoryRequest.setInventory(inventory);
            InventoryRequest fetch = inventoryRequestRepository.save(inventoryRequest);

            return Response.created(routes.inventoryRequestUrl(fetch)).build();

        } else if (type == InventoryRequestType.REDUCE) {
            if (!currentInventory.isPresent()) {
                throw  new BadRequestException();
            }
            amountResult = currentInventory.get().getAmount() - amount;

            if (amountResult < 0) {
                throw  new BadRequestException();
            }
            String orderItemId = info.get("orderItemId").toString();
            InventoryRequest inventoryRequest = new InventoryRequest(productId, amount, orderItemId, type);
            Inventory inventory = new Inventory(productId, amountResult, inventoryRequest);
            inventoryRequest.setInventory(inventory);
            InventoryRequest fetch = inventoryRequestRepository.save(inventoryRequest);

            return Response.created(routes.inventoryRequestUrl(fetch)).build();

        }

        throw  new BadRequestException();

    }
}
