package com.cyquen.employee.mapper;

import com.cyquen.employee.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    Employee findById(@Param("id") Integer id);

    List<Employee> findAll();

    List<Integer> findAllId();
}
