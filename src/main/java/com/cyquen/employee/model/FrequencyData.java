package com.cyquen.employee.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * 频率数据
 */
public class FrequencyData implements Serializable {

    private Time createTime;

    private Integer frequency;

    public Date getCreateTime() {
        return createTime;
    }

    public void setTime(Time createTime) {
        this.createTime = createTime;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "FrequencyData{" +
                "createTime=" + createTime +
                ", frequency=" + frequency +
                '}';
    }
}
