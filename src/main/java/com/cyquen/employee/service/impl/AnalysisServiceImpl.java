package com.cyquen.employee.service.impl;

import com.cyquen.employee.mapper.EmployeeLogMapper;
import com.cyquen.employee.mapper.EmployeeMapper;
import com.cyquen.employee.model.*;
import com.cyquen.employee.service.AnalysisService;
import com.cyquen.employee.util.TimeUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired(required = true)
    EmployeeMapper employeeMapper;

    @Autowired
    EmployeeLogMapper employeeLogMapper;

    @Cacheable(cacheNames = "nearEmployees", cacheManager = "caffeineCacheManager")
    @Override
    public List<EmployeeSimilarity> findNearbyEmployees(Integer employeeId) {
        List<Employee> employees = employeeMapper.findAll();
        Set<Integer> e1Functions = employeeLogMapper.findFunctionIdsByEmployeeId(employeeId);
        PriorityQueue<EmployeeSimilarity> employeeSimilarities = new PriorityQueue<>();
        int topKNum = 10;
        for (Employee employee : employees) {
            if (Objects.equals(employee.getId(), employeeId)) {
                continue;
            }
            Set<Integer> e2Functions = employeeLogMapper.findFunctionIdsByEmployeeId(employee.getId());

            double similarity = computeCosine(e1Functions, e2Functions);
            if (similarity != 0) {
                EmployeeSimilarity employeeSimilarity = new EmployeeSimilarity(employee.getId(), employee.getName(), similarity);
                if (employeeSimilarities.size() == topKNum) {
                    if (employeeSimilarities.peek().getSimilarity() < similarity) {
                        employeeSimilarities.poll();
                        employeeSimilarities.add(employeeSimilarity);
                    }
                } else {
                    employeeSimilarities.add(employeeSimilarity);
                }
            }
        }
        return employeeSimilarities.stream().sorted().collect(Collectors.toList());
    }

    @Cacheable(cacheNames = "nearEmployeesByFunctions", cacheManager = "caffeineCacheManager")
    @Override
    public List<EmployeeSimilarity> findNearbyEmployees(Set<Integer> functions) {
        List<Employee> employees = employeeMapper.findAll();
        PriorityQueue<EmployeeSimilarity> employeeSimilarities = new PriorityQueue<>();
        int topKNum = 10;
        for (Employee employee : employees) {
            Set<Integer> e1Functions = employeeLogMapper.findFunctionIdsByEmployeeId(employee.getId());

            double similarity = computeCosine(e1Functions, functions);
            if (similarity != 0) {
                EmployeeSimilarity employeeSimilarity = new EmployeeSimilarity(employee.getId(), employee.getName(), similarity);
                if (employeeSimilarities.size() == topKNum) {
                    if (employeeSimilarities.peek().getSimilarity() < similarity) {
                        employeeSimilarities.poll();
                        employeeSimilarities.add(employeeSimilarity);
                    }
                } else {
                    employeeSimilarities.add(employeeSimilarity);
                }
            }
        }
        return employeeSimilarities.stream().sorted().collect(Collectors.toList());
    }

    /**
     * 计算员工与员工之间的相似性
     *
     * @return 相似程度
     */
    private double computeCosine(Set<Integer> u1Set, Set<Integer> u2Set) {
        Set<Integer> intersection = u1Set.parallelStream().filter(u2Set::contains).collect(Collectors.toSet());
        // 余弦相似度公式中的分子
        double molecule = intersection.size();
        // 余弦相似度公式中的分母
        double denominator = Math.sqrt(u1Set.size() * u2Set.size());
        // 两用户之间的相似度
        return denominator == 0 ? 0 : molecule / denominator;
    }

    @Override
    public List<FrequencyData> findDailyFrequencyData(Integer employeeId, Date date) {
        return employeeLogMapper.findFrequencyData(employeeId, date, TimeUtils.getEnd(date));
    }

    @Cacheable(cacheNames = "longestUsage", cacheManager = "caffeineCacheManager")
    @Override
    public List<Map.Entry<Integer, Long>> findLongestUsage(Integer employeeId, Date date) {
        List<EmployeeLog> employeeLogs = employeeLogMapper.findEmployeeLog(employeeId, date, TimeUtils.getEnd(date));
        if (employeeLogs.size() == 0) {
            return null;
        }
        Map<Integer, Long> map = new HashMap<>();
        Iterator<EmployeeLog> iterator = employeeLogs.listIterator();
        EmployeeLog pre = iterator.next();
        while (iterator.hasNext()) {
            EmployeeLog cur = iterator.next();
            Long duration = map.getOrDefault(pre.getFunctionId(), 0L) + cur.getCreateTime().getTime() - pre.getCreateTime().getTime();
            map.put(pre.getFunctionId(), duration);
            pre = cur;
        }
        PriorityQueue<Map.Entry<Integer, Long>> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            long l = o1.getValue() - o2.getValue();
            return (int) l;
        });
        for (Map.Entry<Integer, Long> item : map.entrySet()) {
            priorityQueue.add(item);
            if (priorityQueue.size() > 10) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.stream().toList();
    }

    @Override
    public List<WorkplaceDistribution> findWorkplaceDistribution() {
        return employeeMapper.findWorkplaceDistribution();
    }

    @Override
    public List<Count> statistics(String type) {
        if(type.equals("age")) {
            return employeeMapper.findAgeRangeNumber();
        } else if(type.equals("gender")) {
            return employeeMapper.findGenderNumber();
        }
        return null;
    }
}
