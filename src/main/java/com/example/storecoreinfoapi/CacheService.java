package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dto.StoreDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CacheService {
    private final Logger log = LoggerFactory.getLogger(CacheService.class);

    @Resource
    StoreService storeService;

    @Cacheable(cacheNames = "myCache")
    public String cacheThis() {
        log.info("This not coming from cache");
        return "this is it";
    }

    @Cacheable(cacheNames = "stores", key="'searchCrit_'.concat(#size).concat(#page)")
    public List<StoreDTO> getStores(int size, int page) {
        log.info("Returning null value...");
        return null;
    }

    @CachePut(cacheNames="stores", key="'searchCrit_'.concat(#size).concat(#page)")
    public List<StoreDTO> populateStoreCache(int size, int page) {
        log.info("Populating store cache...");
        return this.storeService.getStores(size, page);
    }
}
