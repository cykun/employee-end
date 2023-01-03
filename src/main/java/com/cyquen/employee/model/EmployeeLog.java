package com.cyquen.employee.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 员工日志信息
 * @author zhengxikun
 */
public class EmployeeLog implements Serializable {

    private Integer employeeId;

    private Date createTime;

    private Integer functionId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "EmployeeLog{" +
                "employeeId=" + employeeId +
                ", createTime=" + format.format(createTime) +
                ", functionId=" + functionId +
                '}';
    }
}
