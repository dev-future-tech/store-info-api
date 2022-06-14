package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dao.StoreDAO;
import com.example.storecoreinfoapi.dto.ScheduleDTO;
import com.example.storecoreinfoapi.dto.StoreDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    public List<ScheduleDTO> getStoreSchedules(UUID storeId) {
        String query = """
                       select schedule_id, store_name,
                       day_of_week,
                       (to_char(hour_open_from, '00')||':'||LTRIM(to_char(minute_open_from, '00'))) opening_hour,
                       (to_char(hour_closed_from, '00')||':'||LTRIM(to_char(minute_closed_from, '00'))) closing_hour
                from store
                join store_schedule on store.store_id = store_schedule.store_id
                where store.store_id = '%s';
                """.formatted(storeId.toString());

        ScheduleRowExtractor extractor = new ScheduleRowExtractor();
        this.template.query(query, extractor);
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
