package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dto.ScheduleDTO;
import com.example.storecoreinfoapi.dto.StoreAddressDTO;
import com.example.storecoreinfoapi.dto.StoreDTO;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StoreServiceTest {

    @Resource
    StoreService service;

    @Test
    public void testGetStoreSchedules() {
        List<ScheduleDTO> hours = this.service.getStoreSchedules(UUID.fromString("23d515d4-3a88-4bb5-bad3-76f822509459"));
        assertThat(hours).isNotEmpty().hasSize(5);
    }

    @Test
    public void testGetStore() {
        UUID storeId = UUID.fromString("23d515d4-3a88-4bb5-bad3-76f822509459");
        StoreDTO store = this.service.getStore(storeId);

        assertThat(store.getName()).isEqualTo("Huangze");
        assertThat(store.getStoreId()).isEqualTo(storeId);
    }

    @Test
    public void testGetStoreAddress() {
        UUID storeId = UUID.fromString("23d515d4-3a88-4bb5-bad3-76f822509459");

        StoreAddressDTO address = this.service.getStoreAddress(storeId);

        assertThat(address)
                .isNotNull()
                .hasFieldOrPropertyWithValue("addressId", UUID.fromString("053d69f2-f38e-498a-94b2-09cbbb8676e3"))
                .hasFieldOrPropertyWithValue("street1", "47283 Killdeer Pass")
                .hasFieldOrPropertyWithValue("postalCode", "33411");

    }
}
