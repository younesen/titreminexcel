package com.mtedd.titreminexcel.model;

import com.mtedd.titreminexcel.enums.RuleSeverityEnum;

public class BusinessRuleType {
    private String name;
    private String description;
    private String condition;
    private String onSuccessAction;
    private String onFailureAction;
    private Integer priority;
    private Boolean enabled;
    private RuleSeverityEnum severity;

    // Constructeurs
    public BusinessRuleType() {
        this.enabled = true;
    }

    // Getters et setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getOnSuccessAction() { return onSuccessAction; }
    public void setOnSuccessAction(String onSuccessAction) { this.onSuccessAction = onSuccessAction; }

    public String getOnFailureAction() { return onFailureAction; }
    public void setOnFailureAction(String onFailureAction) { this.onFailureAction = onFailureAction; }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public RuleSeverityEnum getSeverity() { return severity; }
    public void setSeverity(RuleSeverityEnum severity) { this.severity = severity; }
}