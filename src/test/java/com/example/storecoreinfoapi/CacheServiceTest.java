package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dto.StoreDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import jakarta.annotation.Resource;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class CacheServiceTest {

    private final Logger log = LoggerFactory.getLogger(CacheServiceTest.class);

    @Container
    static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:6.2-alpine")).withExposedPorts(6379);

    @DynamicPropertySource
    static void registerRedisProperties(DynamicPropertyRegistry registry)  {
        registry.add("spring.data.redis.port", () -> redis.getFirstMappedPort());
        registry.add("spring.data.redis.host", () -> redis.getHost());
    }

    @Resource
    StoreService service;

    @Resource
    CacheService cacheService;

    @Test
    void testGetFromCache() {
        List<StoreDTO> emptyStores = this.cacheService.getStores(10, 0);

        assertThat(emptyStores).isNullOrEmpty();

        List<StoreDTO> interim = this.service.getStores(10, 0);
        log.info("Interim size: {}", interim.size());

        List<StoreDTO> filledStores = this.cacheService.populateStoreCache(10, 0);
        log.info("filledStores size: {}", filledStores.size());

        assertThat(filledStores).isNotEmpty();

        log.info("Cache testing complete!");
    }
}
