package com.example.storecoreinfoapi;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.storecoreinfoapi.dto.StoreDTO;

@WebMvcTest(StoreController.class)
@MockBeans({@MockBean(StoreService.class), @MockBean(CacheService.class)})
@Testcontainers
public class StoreControllerTest {

    private final Logger log = LoggerFactory.getLogger(StoreControllerTest.class);

    @Resource
    MockMvc mvc;

    @Resource
    StoreService service;

    @Resource
    CacheService cacheService;

    @Test
    public void testGetStores() throws Exception {

        List<StoreDTO> stores = new ArrayList<>();
        StoreDTO one = new StoreDTO();
        StoreDTO two = new StoreDTO();
        StoreDTO three = new StoreDTO();

        stores.addAll(Arrays.asList(one, two, three));

        log.info("Stores ready for testing: {}", stores.size());


        when(service.getStores(Mockito.anyInt(), Mockito.anyInt())).thenReturn(stores);
        when(cacheService.populateStoreCache(Mockito.anyInt(), Mockito.anyInt())).thenReturn(stores);
        when(cacheService.getStores(Mockito.anyInt(), Mockito.anyInt())).thenReturn(null);

        this.mvc.perform(get("/store").header("x-api-version", "v1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetCachedStores() throws Exception {

        List<StoreDTO> stores = new ArrayList<>();
        StoreDTO one = new StoreDTO();
        StoreDTO two = new StoreDTO();
        StoreDTO three = new StoreDTO();

        stores.addAll(Arrays.asList(one, two, three));

        log.info("Stores ready for testing: {}", stores.size());

        when(cacheService.getStores(Mockito.anyInt(), Mockito.anyInt())).thenReturn(stores);

        this.mvc.perform(get("/store").header("x-api-version", "v1"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetStoreById() throws Exception {

        UUID storeId = UUID.randomUUID();

        StoreDTO store = new StoreDTO();
        store.setStoreId(storeId);

        when(service.getStore(Mockito.any(UUID.class))).thenReturn(store);

        this.mvc.perform(get("/store/".concat(storeId.toString())).header("x-api-version", "v1"))
        .andDo(print())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$.store_id", equalTo(storeId.toString())));
    }
}
