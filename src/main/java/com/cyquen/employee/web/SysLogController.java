package com.cyquen.employee.web;

import com.cyquen.employee.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理控制器
 */
@RestController
@RequestMapping("/syslog")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

}
