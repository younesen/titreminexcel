package com.mtedd.titreminexcel.model;

import java.util.List;

public class BusinessRuleListType {
    private List<BusinessRuleType> businessRules;

    // Constructeurs
    public BusinessRuleListType() {
        this.businessRules = new java.util.ArrayList<>();
    }

    // Getters et setters
    public List<BusinessRuleType> getBusinessRules() {
        return businessRules;
    }

    public void setBusinessRules(List<BusinessRuleType> businessRules) {
        this.businessRules = businessRules;
    }
}