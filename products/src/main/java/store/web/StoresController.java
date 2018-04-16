package store.web;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import store.domain.Store;
import store.dto.StoreDTO;
import store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import store.web.serializer.Page;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/stores")
public class StoresController {

    @Autowired
    private StoreRepository storeRepository;

    private static final ModelMapper modelMapper = new ModelMapper();


    @ResponseBody
    @RequestMapping(method = POST, consumes = "application/json")
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {

        if(!payload.containsKey("ownerId") || !payload.containsKey("name")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Store store = new Store(payload.get("ownerId").toString(), payload.get("name").toString());

        Store fetch = storeRepository.save(store);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(fetch.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ResponseBody
    @GetMapping(produces = "application/json")
    public Page<StoreDTO> getList() {
        List<Store> stores = storeRepository.findAll();
        List<StoreDTO> storeDTOS = new ArrayList<>();
        stores.forEach((store -> {
            StoreDTO storeDTO = modelMapper.map(store, StoreDTO.class);
            storeDTOS.add(storeDTO);
        }));
        return new Page(storeDTOS);
    }
}
