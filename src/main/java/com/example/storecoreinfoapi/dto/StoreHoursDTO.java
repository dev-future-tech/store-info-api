package com.example.storecoreinfoapi.dto;

import java.util.UUID;

public class StoreHoursDTO {

    private UUID scheduleId;
    private UUID storeId;
    private ScheduleDTO[] schedule;
    private ScheduleDTO[] scheduleOverride;

    public UUID getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(UUID scheduleId) {
        this.scheduleId = scheduleId;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public void setStoreId(UUID storeId) {
        this.storeId = storeId;
    }

    public ScheduleDTO[] getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleDTO[] schedule) {
        this.schedule = schedule;
    }

    public ScheduleDTO[] getScheduleOverride() {
        return scheduleOverride;
    }

    public void setScheduleOverride(ScheduleDTO[] scheduleOverride) {
        this.scheduleOverride = scheduleOverride;
    }
}
