package com.cyquen.employee.service;

import com.cyquen.employee.model.EmployeeLog;
import com.cyquen.employee.model.SysLogInfo;

/**
 * 系统日志服务
 */
public interface SysLogService {

    void save(SysLogInfo sysLogInfo);
}
