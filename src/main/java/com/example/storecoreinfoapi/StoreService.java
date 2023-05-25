package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dao.StoreDAO;
import com.example.storecoreinfoapi.dto.ScheduleDTO;
import com.example.storecoreinfoapi.dto.StoreDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Component
public class StoreService {

    @Resource
    StoreRespository repository;

    @Resource
    JdbcTemplate template;

    public StoreDTO getStore(UUID storeUUID) {
        return this.repository.findById(storeUUID).map(this::toDTO).orElseThrow();
    }

    public List<StoreDTO> getStores(int size, int page) {
        String query = "select store_id, store_name, phone_no from store offset ? limit ?";

        return this.template.query(query, ps -> {
            ps.setInt(1, page);
            ps.setInt(2, size);
        },
                (rs, rowNum) -> {
                    StoreDTO dto = new StoreDTO();
                    dto.setStoreId(rs.getObject("store_id", UUID.class));
                    dto.setName(rs.getString("store_name"));
                    dto.setPhoneNo(rs.getString("phone_no"));
                    return dto;
                });
    }

    public List<ScheduleDTO> getStoreSchedules(UUID storeId) {
        String query = """
                       select store_schedule.schedule_id, store_name,
                              store_schedule.day_of_week,
                              (to_char(store_schedule.hour_open_from, '00')||':'||LTRIM(to_char(store_schedule.minute_open_from, '00'))) opening_hour,
                              (to_char(store_schedule.hour_closed_from, '00')||':'||LTRIM(to_char(store_schedule.minute_closed_from, '00'))) closing_hour
                       from store
                       join store_hours_mapping on store.store_id = store_hours_mapping.store_id
                       join store_schedule on store_schedule.hours_id = store_hours_mapping.hours_id
                       where store.store_id = ?;
                """;

        ScheduleRowExtractor extractor = new ScheduleRowExtractor();
        this.template.query(query, ps -> ps.setObject(1, storeId), extractor);
        return extractor.getSchedules();
    }

    StoreDTO toDTO(StoreDAO dao) {
        StoreDTO toReturn = new StoreDTO();
        toReturn.setStoreId(dao.getStoreId());
        toReturn.setPhoneNo(dao.getPhoneNo());
        toReturn.setName(dao.getStoreName());
        return toReturn;
    }
}
