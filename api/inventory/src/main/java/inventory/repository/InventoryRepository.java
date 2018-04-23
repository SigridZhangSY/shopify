package inventory.repository;

import inventory.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository <Inventory, String> {
    Optional<Inventory> findFirstByProductIdOrderByCreatedAtDesc(String productId);
}
