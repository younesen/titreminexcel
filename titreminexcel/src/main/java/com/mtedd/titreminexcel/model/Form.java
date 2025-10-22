package com.mtedd.titreminexcel.model;

import com.mtedd.titreminexcel.enums.FormTypeEnum;
import java.util.List;

public class Form {
    private String name;
    private String title;
    private String description;
    private FormTypeEnum formType;
    private List<FieldSet> fieldsets;

    // Getters et setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public FormTypeEnum getFormType() { return formType; }
    public void setFormType(FormTypeEnum formType) { this.formType = formType; }

    public List<FieldSet> getFieldsets() { return fieldsets; }
    public void setFieldsets(List<FieldSet> fieldsets) { this.fieldsets = fieldsets; }
}