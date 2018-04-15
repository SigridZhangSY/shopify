package store.web;

import store.domain.Store;
import store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/stores")
public class StoresController {

    @Autowired
    private StoreRepository storeRepository;

    @ResponseBody
    @RequestMapping(method = POST, consumes = "application/json")
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {

        Store store = new Store(payload.get("ownerId").toString());

        Store fetch = storeRepository.save(store);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(fetch.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
