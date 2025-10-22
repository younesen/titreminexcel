package com.mtedd.titreminexcel.model;

import java.util.Date;

public class TemporalDataType {
    private Date creationDate;
    private Long averageProcessingTime;
    private String timeUnit;

    // Getters et setters
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Long getAverageProcessingTime() { return averageProcessingTime; }
    public void setAverageProcessingTime(Long averageProcessingTime) { this.averageProcessingTime = averageProcessingTime; }

    public String getTimeUnit() { return timeUnit; }
    public void setTimeUnit(String timeUnit) { this.timeUnit = timeUnit; }
}