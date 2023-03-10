package com.cyquen.employee.service.impl;

import com.cyquen.employee.mapper.EmployeeLogMapper;
import com.cyquen.employee.mapper.EmployeeMapper;
import com.cyquen.employee.model.*;
import com.cyquen.employee.service.AnalysisService;
import com.cyquen.employee.util.TimeUtils;
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
     * ???????????????????????????????????????
     *
     * @return ????????????
     */
    private double computeCosine(Set<Integer> u1Set, Set<Integer> u2Set) {
        Set<Integer> intersection = u1Set.parallelStream().filter(u2Set::contains).collect(Collectors.toSet());
        // ?????????????????????????????????
        double molecule = intersection.size();
        // ?????????????????????????????????
        double denominator = Math.sqrt(u1Set.size() * u2Set.size());
        // ???????????????????????????
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
    public List<Distribution> findWorkplaceDistribution(String type) {
        return employeeMapper.findDistribution(type);
    }

    @Override
    public List<Count> statistics(String type) {
        return switch (type) {
            case "age" -> employeeMapper.findAgeRangeNumber();
            case "gender" -> employeeMapper.findGenderNumber();
            case "post" -> employeeMapper.findPostNumber();
            default -> null;
        };
    }

    @Override
    public Set<Integer> postGenerality(String post) {
        // ????????????1????????????
        List<Integer> employees = employeeMapper.findAllByPost(post, TimeUtils.beforeYear(1));
        if (employees.size() == 0) {
            return null;
        }
        Iterator<Integer> iterator = employees.iterator();
        Integer employeeId = iterator.next();
        Set<Integer> sameLog = employeeLogMapper.findFunctionIdsByEmployeeId(employeeId);
        while (iterator.hasNext()) {
            employeeId = iterator.next();
            Set<Integer> eFunctions = employeeLogMapper.findFunctionIdsByEmployeeId(employeeId);
            sameLog = sameLog.stream().filter(eFunctions::contains).collect(Collectors.toSet());
            if (sameLog.size() == 0) {
                break;
            }
        }
        return sameLog;
    }
}
