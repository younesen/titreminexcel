package com.mtedd.titreminexcel.model;

import java.util.Date;

public class StatisticsType {
    private Integer totalInstances;
    private Double successRate;
    private Long averageProcessingTime;
    private Date lastExecution;
    private String mostUsedForm;

    // Constructeurs
    public StatisticsType() {
        this.totalInstances = 0;
        this.successRate = 0.0;
    }

    public StatisticsType(Integer totalInstances, Double successRate) {
        this();
        this.totalInstances = totalInstances;
        this.successRate = successRate;
    }

    // Getters et setters
    public Integer getTotalInstances() {
        return totalInstances;
    }

    public void setTotalInstances(Integer totalInstances) {
        this.totalInstances = totalInstances;
    }

    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    public Long getAverageProcessingTime() {
        return averageProcessingTime;
    }

    public void setAverageProcessingTime(Long averageProcessingTime) {
        this.averageProcessingTime = averageProcessingTime;
    }

    public Date getLastExecution() {
        return lastExecution;
    }

    public void setLastExecution(Date lastExecution) {
        this.lastExecution = lastExecution;
    }

    public String getMostUsedForm() {
        return mostUsedForm;
    }

    public void setMostUsedForm(String mostUsedForm) {
        this.mostUsedForm = mostUsedForm;
    }

    @Override
    public String toString() {
        return "StatisticsType{" +
                "totalInstances=" + totalInstances +
                ", successRate=" + successRate +
                ", averageProcessingTime=" + averageProcessingTime +
                ", lastExecution=" + lastExecution +
                ", mostUsedForm='" + mostUsedForm + '\'' +
                '}';
    }
}