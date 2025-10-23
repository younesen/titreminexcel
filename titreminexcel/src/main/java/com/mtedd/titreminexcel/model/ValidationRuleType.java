package com.mtedd.titreminexcel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mtedd.titreminexcel.enums.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidationRuleType {
    private String field;
    private String ruleExpression;
    private String message;
    private ValidationSeverityEnum severity;

    // Getters et Setters
    public String getField() { return field; }
    public void setField(String field) { this.field = field; }

    public String getRuleExpression() { return ruleExpression; }
    public void setRuleExpression(String ruleExpression) { this.ruleExpression = ruleExpression; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public ValidationSeverityEnum getSeverity() { return severity; }
    public void setSeverity(ValidationSeverityEnum severity) { this.severity = severity; }
}