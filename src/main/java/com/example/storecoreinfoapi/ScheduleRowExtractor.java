package com.example.storecoreinfoapi;

import com.example.storecoreinfoapi.dto.ScheduleDTO;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ScheduleRowExtractor implements RowCallbackHandler {
    private final List<ScheduleDTO> schedules;

    public ScheduleRowExtractor() {
        this.schedules = new ArrayList<>();
    }

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setScheduleId(rs.getObject("schedule_id", UUID.class));
        String openTime = rs.getString("opening_hour").trim();
        String closeTime = rs.getString("closing_hour").trim();
        dto.setOpenTime(openTime);
        dto.setCloseTime(closeTime);
        dto.setDayOfWeek(rs.getString("day_of_week"));
        schedules.add(dto);
    }

    public List<ScheduleDTO> getSchedules() {
        return schedules;
    }
}
