package com.cyquen.employee.web;

import com.cyquen.employee.model.SysLogInfo;
import com.cyquen.employee.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日志管理控制器
 */
@RestController
@RequestMapping("/syslog")
public class SysLogController {

    @Autowired
    SysLogService sysLogService;

    @GetMapping("/all")
    public List<SysLogInfo> queryAll(@RequestParam(value = "type", required = false) Integer type) {
        return sysLogService.findAll(type);
    }

    @GetMapping("/error")
    public void errorSimulate() throws Exception {
        throw new Exception("simulate exception");
    }
}
