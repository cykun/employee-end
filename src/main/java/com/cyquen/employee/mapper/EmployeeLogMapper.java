package com.cyquen.employee.mapper;

import com.cyquen.employee.model.EmployeeLog;
import com.cyquen.employee.model.FrequencyData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Mapper
public interface EmployeeLogMapper {

    List<EmployeeLog> findEmployeeLog(@Param("employeeId") Integer employeeId, @Param("from") Date from, @Param("to") Date to);

    Set<Integer> findFunctionIdsByEmployeeId(@Param("employeeId") Integer employeeId);

    List<FrequencyData> findFrequencyData(@Param("employeeId") Integer employeeId, @Param("start") Date start, @Param("end") Date end);

    Boolean addEmployeeLog(EmployeeLog employeeLog);
}
