package inventory.repository;

import inventory.domain.InventoryRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRequestRepository extends JpaRepository <InventoryRequest, String> {
}
