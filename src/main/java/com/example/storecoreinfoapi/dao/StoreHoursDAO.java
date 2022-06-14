package com.example.storecoreinfoapi.dao;

import com.example.storecoreinfoapi.dto.ScheduleDTO;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "store_hours")
public class StoreHoursDAO {

    @Id
    @Column(name = "schedule_id")
    private UUID scheduleId;

    private UUID storeId;

    @OneToMany(targetEntity = ScheduleDAO.class)
    private Set<ScheduleDAO> schedule;

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

    public Set<ScheduleDAO> getSchedule() {
        return schedule;
    }

    public void setSchedule(Set<ScheduleDAO> schedule) {
        this.schedule = schedule;
    }
}
