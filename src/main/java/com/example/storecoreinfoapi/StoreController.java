package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dto.ScheduleDTO;
import com.example.storecoreinfoapi.dto.StoreDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/store", headers = "x-api-version=v1")
public class StoreController {

    private final Logger log = LoggerFactory.getLogger(StoreController.class);

    @Resource
    StoreService service;

    @Resource
    CacheService cacheService;

    @GetMapping()
    public ResponseEntity<List<StoreDTO>> getStores(@RequestParam(value = "size", defaultValue = "10") int size,
                                                    @RequestParam(value = "page", defaultValue = "0") int page) {
        List<StoreDTO> results = this.cacheService.getStores(size, page);

        if (results == null) {
            log.info("Cache not populated, populating cache!");
            results = this.cacheService.populateStoreCache(size, page);
        }
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{store_id}")
    public ResponseEntity<StoreDTO> findByStoreId(@PathVariable("store_id") String storeId) {
        log.debug("Finding store with id {}", storeId);

        try {
            StoreDTO store = this.service.getStore(UUID.fromString(storeId));
            return ResponseEntity.ok(store);
        } catch (Exception e) {
            log.error("Error finding store with id {}", storeId, e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{store_id}/schedule")
    public ResponseEntity<List<ScheduleDTO>> getStoreSchedules(@PathVariable("store_id") String storeId) {
        log.debug("Finding store schedules with id {}", storeId);
        List<ScheduleDTO> results = this.service.getStoreSchedules(UUID.fromString(storeId));

        return ResponseEntity.ok(results);
    }

    @GetMapping("/cache")
    public ResponseEntity<Void> testCachedValue() {
        log.info("Getting value!");
        String val1 = this.cacheService.cacheThis();

        log.info("Value is: {}", val1);

        String val2 = this.cacheService.cacheThis();

        log.info("Second attempt is: {}", val2);

        return ResponseEntity.ok().build();
    }
}
