package com.cyquen.employee.mapper;

import com.cyquen.employee.model.Count;
import com.cyquen.employee.model.Employee;
import com.cyquen.employee.model.Distribution;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    Employee findById(@Param("id") Integer id);

    List<Employee> findAll();

    List<Integer> findAllId();

    List<Distribution> findDistribution(@Param("type") String type);

    List<Count> findAgeRangeNumber();

    List<Count> findGenderNumber();

    List<Count> findPostNumber();
}
