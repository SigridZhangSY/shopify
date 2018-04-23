package inventory.repository;

import inventory.domain.InventoryRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRequestRepository extends JpaRepository <InventoryRequest, String> {
    List<InventoryRequest> findByProductIdOrOrderByCreatedAtDesc(String productId);
}
