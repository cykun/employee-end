package com.cyquen.employee.model;

import java.io.Serializable;

/**
 * 存储用户之间的相似度
 * @author zhengxikun
 */
public class EmployeeSimilarity implements Serializable, Comparable<EmployeeSimilarity> {

    private Integer employeeId;

    private String name;

    private Double similarity;

    public EmployeeSimilarity() {}

    public EmployeeSimilarity(Integer employeeId, String name, Double similarity) {
        this.employeeId = employeeId;
        this.name = name;
        this.similarity = similarity;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    @Override
    public int compareTo(EmployeeSimilarity o) {
        return o.similarity.compareTo(this.similarity);
    }
}
