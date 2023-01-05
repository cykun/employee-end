package com.cyquen.employee.service;

import com.cyquen.employee.model.Count;
import com.cyquen.employee.model.EmployeeSimilarity;
import com.cyquen.employee.model.FrequencyData;
import com.cyquen.employee.model.WorkplaceDistribution;
import org.checkerframework.checker.units.qual.C;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 分析服务接口
 */
public interface AnalysisService {

    List<EmployeeSimilarity> findNearbyEmployees(Integer employeeId);

    List<EmployeeSimilarity> findNearbyEmployees(Set<Integer> functions);

    List<FrequencyData> findDailyFrequencyData(Integer employeeId, Date date);

    List<Map.Entry<Integer, Long>> findLongestUsage(Integer employeeId, Date date);

    List<WorkplaceDistribution> findWorkplaceDistribution();

    List<Count> statistics(String type);
}
