package com.mtedd.titreminexcel.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import com.mtedd.titreminexcel.enums.*;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldSet {
    private String name;
    private String title;
    private String description;
    private String usage;
    private String icon;
    private String image;
    private String layout;
    private String coreType;
    private List<FormElement> formElements;

    // Getters et setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUsage() { return usage; }
    public void setUsage(String usage) { this.usage = usage; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getLayout() { return layout; }
    public void setLayout(String layout) { this.layout = layout; }

    public String getCoreType() { return coreType; }
    public void setCoreType(String coreType) { this.coreType = coreType; }

    public List<FormElement> getFormElements() { return formElements; }
    public void setFormElements(List<FormElement> formElements) { this.formElements = formElements; }
}