package com.cyquen.employee;

import com.cyquen.employee.mapper.EmployeeLogMapper;
import com.cyquen.employee.mapper.EmployeeMapper;
import com.cyquen.employee.service.AnalysisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    EmployeeLogMapper employeeLogMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    AnalysisService analysisService;

    @Test
    public void getEmployeeLog() {
        // System.out.println(employeeLogMapper.findEmployeeLog());
//        System.out.println(employeeMapper.findAllId());
        //System.out.println(employeeLogMapper.findFrequencyData(1, new Date(0), new Date(System.currentTimeMillis())));

        System.out.println(employeeMapper.findDistribution("address"));
    }
}
