package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByStoreId(String storeId);
}
