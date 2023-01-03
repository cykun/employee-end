package com.cyquen.employee.schedule;

import com.cyquen.employee.mapper.EmployeeLogMapper;
import com.cyquen.employee.model.FrequencyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

    EmployeeLogMapper employeeLogMapper;

    @Autowired
    public void setEmployeeLogMapper(EmployeeLogMapper employeeLogMapper) {
        this.employeeLogMapper = employeeLogMapper;
    }

    /**
     * second/min/hour/day/mouth/week
     */
//    @Scheduled(cron = "0 56 18 * * *")
    @Scheduled(fixedDelay = 5000)
    public void analysisSchedule() {
        analysis();
    }

    private FrequencyData getHistoryData(java.util.Date date) {
        // return employeeLogMapper.findFrequencyData(0, new Time(date.getTime()));
        return null;
    }

    private void analysis() {

    }

}
