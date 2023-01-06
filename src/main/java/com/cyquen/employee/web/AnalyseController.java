package com.cyquen.employee.web;

import com.cyquen.employee.model.Count;
import com.cyquen.employee.model.EmployeeSimilarity;
import com.cyquen.employee.model.FrequencyData;
import com.cyquen.employee.model.Distribution;
import com.cyquen.employee.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据分析控制器
 */
@RestController
public class AnalyseController {

    @Autowired
    AnalysisService analysisService;

    /**
     * 获取与员工 employeeId 工作内容相似的集合列表
     *
     * @param employeeId 员工ID
     * @return 员工列表
     */
    @GetMapping("/employees/similar/{employId}")
    public List<EmployeeSimilarity> querySimilarEmployees(@PathVariable("employId") Integer employeeId) {
        return analysisService.findNearbyEmployees(employeeId);
    }

    /**
     * 根据功能列表查找与这些功能比较相近的员工列表
     *
     * @param functions 功能列表
     * @return 员工列表
     */
    @GetMapping("/employees/functions")
    public List<EmployeeSimilarity> queryEmployeesByFunctions(@RequestParam("functions") Set<Integer> functions) {
        return analysisService.findNearbyEmployees(functions);
    }

    /**
     * 查找员工日常使用情况
     *
     * @param employeeId 员工 ID
     * @param date       日期
     * @return 访问频率情况
     */
    @GetMapping("/employees/daily")
    public List<FrequencyData> queryDailyUseFrequency(@RequestParam("employeeId") Integer employeeId,
                                                      @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return analysisService.findDailyFrequencyData(employeeId, date);
    }

    /**
     * 查找员工在哪些功能使用时间比较长
     *
     * @param employeeId 员工 ID
     * @param date       日期
     * @return 结果列表
     */
    @GetMapping("/employees/daily/longest")
    public List<Map.Entry<Integer, Long>> queryDailyLongestUsage(@RequestParam("employeeId") Integer employeeId,
                                                                 @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return analysisService.findLongestUsage(employeeId, date);
    }

    @GetMapping("/employees/distribution/{type}")
    public List<Distribution> queryWorkplaceDistribution(@PathVariable("type") String type) {
        return analysisService.findWorkplaceDistribution(type);
    }

    @GetMapping("/employees/statistics/{type}")
    public List<Count> queryStatistics(@PathVariable("type") String type) {
        return analysisService.statistics(type);
    }
}
