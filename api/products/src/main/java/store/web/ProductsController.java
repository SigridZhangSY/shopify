package store.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import store.domain.Product;
import store.domain.Store;
import store.dto.ProductDTO;
import store.repository.ProductRepository;
import store.repository.StoreRepository;
import store.service.ProductService;
import store.web.exception.NotFoundException;
import store.web.serializer.Page;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
public class ProductsController {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    @ResponseBody
    @PostMapping(path = "/stores/{storeId}/products", consumes = "application/json")
    public ResponseEntity<?> createProducts(@PathVariable String storeId, @RequestBody Map<String, Object> payload) {
        if(!payload.containsKey("description") || !payload.containsKey("name")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Optional<Store> store = storeRepository.findById(storeId);
        if (!store.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Product product = new Product(payload.get("name").toString(), storeId, payload.get("description").toString());
        Product fetch = productRepository.save(product);

        URI uri = URI.create("/products/" + fetch.getId());

        return ResponseEntity.created(uri).build();
    }

    @ResponseBody
    @GetMapping(value = "/stores/{storeId}/products", produces = "application/json")
    public Page<ProductDTO> getProductList(@PathVariable String storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        if (!store.isPresent()) {
            throw  new NotFoundException("store " + storeId + " not found");
        }

        List<ProductDTO> productDTOList = productRepository.findByStoreId(storeId).stream().map(product ->
                modelMapper.map(product, ProductDTO.class)
        ).collect(toList());

        return new Page(productDTOList);
    }

    @ResponseBody
    @GetMapping(value = "/products/{productId}", produces = "application/json")
    public ProductDTO getProduct(@PathVariable String productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return modelMapper.map(product.get(), ProductDTO.class);
        } else {
            throw new NotFoundException();
        }
    }
}
