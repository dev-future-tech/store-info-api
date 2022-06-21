package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dto.StoreDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class CacheServiceTest {

    private final Logger log = LoggerFactory.getLogger(CacheServiceTest.class);

    @Resource
    StoreService service;

    @Resource
    CacheService cacheService;

    @Test
    public void testGetFromCache() {
        List<StoreDTO> emptyStores = this.cacheService.getStores(10, 0);

        assertThat(emptyStores).isNullOrEmpty();

        List<StoreDTO> interim = this.service.getStores(10, 0);
        log.info("Interim size: {}", interim.size());

        List<StoreDTO> filledStores = this.cacheService.populateStoreCache(10, 0);
        log.info("filledStores size: {}", filledStores.size());

        assertThat(filledStores).isNotEmpty();
    }
}
