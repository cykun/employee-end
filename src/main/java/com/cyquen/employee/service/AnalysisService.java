package com.cyquen.employee.service;

import com.cyquen.employee.model.Count;
import com.cyquen.employee.model.EmployeeSimilarity;
import com.cyquen.employee.model.FrequencyData;
import com.cyquen.employee.model.Distribution;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 分析服务接口
 */
public interface AnalysisService {

    /**
     * 根据员工 ID 获取到与其工作内容上相近的员工列表
     *
     * @param employeeId 员工ID
     * @return 员工列表
     */
    List<EmployeeSimilarity> findNearbyEmployees(Integer employeeId);

    /**
     * 根据功能列表查找与这些功能点相近的员工列表
     *
     * @param functions 功能点集合
     * @return 员工列表
     */
    List<EmployeeSimilarity> findNearbyEmployees(Set<Integer> functions);

    /**
     * 根据员工号，查找指定日期的工作频率相关数据
     *
     * @param employeeId 员工ID
     * @param date 指定日期，年月日
     * @return 工作频率数据
     */
    List<FrequencyData> findDailyFrequencyData(Integer employeeId, Date date);

    /**
     * 根据员工号，查找指定日期的主要工作数据
     * @param employeeId 员工ID
     * @param date 指定日期，年月日
     * @return topk 时间工作列表
     */
    List<Map.Entry<Integer, Long>> findLongestUsage(Integer employeeId, Date date);

    /**
     * 根据类型，查找员工地区分布
     * @param type 类型：address（家庭住址），workplace（工作地点）
     * @return 工作地区分布数据
     */
    List<Distribution> findWorkplaceDistribution(String type);

    /**
     * 统计指定类型数据
     * @param type 需要统计的类型：age（年龄），gender（性别），post（岗位）
     * @return 统计数据
     */
    List<Count> statistics(String type);

    /**
     * 岗位共性分析
     *
     * @param post 岗位名称
     * @return 岗位共性列表
     */
    Set<Integer> postGenerality(String post);
}
