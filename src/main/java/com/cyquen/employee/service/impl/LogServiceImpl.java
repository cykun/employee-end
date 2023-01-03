package com.cyquen.employee.service.impl;

import com.cyquen.employee.mapper.EmployeeLogMapper;
import com.cyquen.employee.model.EmployeeLog;
import com.cyquen.employee.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    EmployeeLogMapper employeeLogMapper;

    @Override
    public boolean add(EmployeeLog employeeLog) {
        return employeeLogMapper.addEmployeeLog(employeeLog);
    }
}
