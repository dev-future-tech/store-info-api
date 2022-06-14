package com.example.storecoreinfoapi.dao;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "store_schedule")
public class ScheduleDAO {

    @Id
    @Column(name = "schedule_id")
    private UUID scheduleId;

    @OneToOne
    @JoinColumn(name="store_id")
    StoreDAO store;
    @Column(name="day_of_week")
    private String dayOfWeek;

    @Column(name = "hour_open_from")
    private Integer hourOpenFrom;

    @Column(name = "minute_open_from")
    private Integer minuteOpenFrom;

    @Column(name = "hour_closed_from")
    private Integer hourClosedFrom;

    @Column(name = "minute_closed_from")
    private Integer minuteClosedFrom;

    public StoreDAO getStore() {
        return store;
    }

    public void setStore(StoreDAO store) {
        this.store = store;
    }

    public UUID getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(UUID scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getHourOpenFrom() {
        return hourOpenFrom;
    }

    public void setHourOpenFrom(Integer hourOpenFrom) {
        this.hourOpenFrom = hourOpenFrom;
    }

    public Integer getMinuteOpenFrom() {
        return minuteOpenFrom;
    }

    public void setMinuteOpenFrom(Integer minuteOpenFrom) {
        this.minuteOpenFrom = minuteOpenFrom;
    }

    public Integer getHourClosedFrom() {
        return hourClosedFrom;
    }

    public void setHourClosedFrom(Integer hourClosedFrom) {
        this.hourClosedFrom = hourClosedFrom;
    }

    public Integer getMinuteClosedFrom() {
        return minuteClosedFrom;
    }

    public void setMinuteClosedFrom(Integer minuteClosedFrom) {
        this.minuteClosedFrom = minuteClosedFrom;
    }
}
