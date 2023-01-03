package com.cyquen.employee.web;

import com.cyquen.employee.model.EmployeeLog;
import com.cyquen.employee.model.Result;
import com.cyquen.employee.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理控制器
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService logService;

    @PostMapping
    public Result<Boolean> addLog(@RequestBody EmployeeLog employeeLog) {
        return new Result<>(200, null, logService.add(employeeLog));
    }
}
