package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dto.ScheduleDTO;
import com.example.storecoreinfoapi.dto.StoreDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/store/v1")
public class StoreController {

    private final Logger log = LoggerFactory.getLogger(StoreController.class);

    @Resource
    StoreService service;

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
}
