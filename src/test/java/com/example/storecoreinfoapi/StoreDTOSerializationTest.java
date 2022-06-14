package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dto.ScheduleDTO;
import com.example.storecoreinfoapi.dto.StoreDTO;
import com.example.storecoreinfoapi.dto.StoreHoursDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

@SpringBootTest
public class StoreDTOSerializationTest {

    private final Logger log = LoggerFactory.getLogger(StoreDTOSerializationTest.class);

    @Test
    public void testStoreSerialization() {
        StoreDTO store = new StoreDTO();

        store.setName("TroubleWay Castro");
        store.setPhoneNo("415.468.2312");
        store.setStoreId(UUID.randomUUID());

        StoreHoursDTO regularHours = new StoreHoursDTO();
        regularHours.setStoreId(store.getStoreId());
        regularHours.setScheduleId(UUID.randomUUID());

        ScheduleDTO[] week = new ScheduleDTO[7];

        ScheduleDTO monday = new ScheduleDTO();
        monday.setDayOfWeek("monday");
        monday.setOpenTime("9:00");
        monday.setCloseTime("22:30");

        week[0] = monday;

        ScheduleDTO tuesday = new ScheduleDTO();
        tuesday.setDayOfWeek("tuesday");
        tuesday.setOpenTime("9:00");
        tuesday.setCloseTime("22:30");

        week[1] = tuesday;

        ScheduleDTO wednesday = new ScheduleDTO();
        wednesday.setDayOfWeek("wednesday");
        wednesday.setOpenTime("9:00");
        wednesday.setCloseTime("22:30");

        week[2] = wednesday;

        ScheduleDTO thursday = new ScheduleDTO();
        thursday.setDayOfWeek("thursday");
        thursday.setOpenTime("9:00");
        thursday.setCloseTime("22:30");

        week[3] = thursday;

        ScheduleDTO friday = new ScheduleDTO();
        friday.setDayOfWeek("friday");
        friday.setOpenTime("9:00");
        friday.setCloseTime("22:30");

        week[4] = friday;

        ScheduleDTO saturday = new ScheduleDTO();
        saturday.setDayOfWeek("saturday");
        saturday.setOpenTime("9:00");
        saturday.setCloseTime("21:00");

        week[5] = saturday;


        ScheduleDTO sunday = new ScheduleDTO();
        sunday.setDayOfWeek("sunday");
        sunday.setOpenTime("9:00");
        sunday.setCloseTime("21:00");

        week[6] = sunday;

        regularHours.setSchedule(week);

        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream storeBaos = new ByteArrayOutputStream();
        ByteArrayOutputStream hoursBaos = new ByteArrayOutputStream();

        try {
            mapper.writeValue(storeBaos, store);
            log.info(storeBaos.toString());

            mapper.writeValue(hoursBaos, regularHours);
            log.info(hoursBaos.toString());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
