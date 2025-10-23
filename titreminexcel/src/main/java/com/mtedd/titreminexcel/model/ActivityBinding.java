package com.mtedd.titreminexcel.model;

public class ActivityBinding {
    private String activityId;
    private String activityName;
    private String taskType;
    private String displayFormName;
    private String handlingFormName;
    private AssignmentType assignment;
    private String notificationName;
    private BusinessRuleListType businessRules;
    private Boolean updateGed;
    private Boolean updateBi;
    private String mapViewerName;

    // Constructeurs
    public ActivityBinding() {
        this.updateGed = false;
        this.updateBi = false;
    }

    // Getters et setters
    public String getActivityId() { return activityId; }
    public void setActivityId(String activityId) { this.activityId = activityId; }

    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }

    public String getTaskType() { return taskType; }
    public void setTaskType(String taskType) { this.taskType = taskType; }

    public String getDisplayFormName() { return displayFormName; }
    public void setDisplayFormName(String displayFormName) { this.displayFormName = displayFormName; }

    public String getHandlingFormName() { return handlingFormName; }
    public void setHandlingFormName(String handlingFormName) { this.handlingFormName = handlingFormName; }

    public AssignmentType getAssignment() { return assignment; }
    public void setAssignment(AssignmentType assignment) { this.assignment = assignment; }

    public String getNotificationName() { return notificationName; }
    public void setNotificationName(String notificationName) { this.notificationName = notificationName; }

    public BusinessRuleListType getBusinessRules() { return businessRules; }
    public void setBusinessRules(BusinessRuleListType businessRules) { this.businessRules = businessRules; }

    public Boolean getUpdateGed() { return updateGed; }
    public void setUpdateGed(Boolean updateGed) { this.updateGed = updateGed; }

    public Boolean getUpdateBi() { return updateBi; }
    public void setUpdateBi(Boolean updateBi) { this.updateBi = updateBi; }

    public String getMapViewerName() { return mapViewerName; }
    public void setMapViewerName(String mapViewerName) { this.mapViewerName = mapViewerName; }

    @Override
    public String toString() {
        return "ActivityBinding{" +
                "activityId='" + activityId + '\'' +
                ", activityName='" + activityName + '\'' +
                ", taskType='" + taskType + '\'' +
                ", displayFormName='" + displayFormName + '\'' +
                ", updateGed=" + updateGed +
                ", updateBi=" + updateBi +
                '}';
    }
}