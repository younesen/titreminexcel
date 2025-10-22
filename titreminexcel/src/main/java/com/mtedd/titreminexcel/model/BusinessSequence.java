package com.mtedd.titreminexcel.model;

public class BusinessSequence {
    private SeqStructure seqStructure;
    private String seqField;
    private String activityId;
    private String activityPort;
    private String condition;

    // Constructeurs
    public BusinessSequence() {}

    public BusinessSequence(SeqStructure seqStructure, String seqField, String activityId) {
        this.seqStructure = seqStructure;
        this.seqField = seqField;
        this.activityId = activityId;
    }

    // Getters et setters
    public SeqStructure getSeqStructure() { return seqStructure; }
    public void setSeqStructure(SeqStructure seqStructure) { this.seqStructure = seqStructure; }

    public String getSeqField() { return seqField; }
    public void setSeqField(String seqField) { this.seqField = seqField; }

    public String getActivityId() { return activityId; }
    public void setActivityId(String activityId) { this.activityId = activityId; }

    public String getActivityPort() { return activityPort; }
    public void setActivityPort(String activityPort) { this.activityPort = activityPort; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    @Override
    public String toString() {
        return "BusinessSequence{" +
                "seqStructure=" + seqStructure +
                ", seqField='" + seqField + '\'' +
                ", activityId='" + activityId + '\'' +
                ", activityPort='" + activityPort + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}