package com.cyquen.employee.service.impl;

import com.cyquen.employee.mapper.EmployeeLogMapper;
import com.cyquen.employee.model.EmployeeLog;
import com.cyquen.employee.service.EmployeeLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeLogServiceImpl implements EmployeeLogService {

    @Autowired
    EmployeeLogMapper employeeLogMapper;

    @Override
    public boolean add(EmployeeLog employeeLog) {
        return employeeLogMapper.addEmployeeLog(employeeLog);
    }
}
