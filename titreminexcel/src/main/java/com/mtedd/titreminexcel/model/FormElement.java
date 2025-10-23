package com.mtedd.titreminexcel.model;

import java.util.Map;

public class FormElement {
    private String name;
    private String label;
    private String type;
    private Map<String, Object> typeOptions; // Doit être Map<String, Object>
    private String description;
    private boolean required;
    private String regex;
    private String defaultValue;
    private boolean hidden;
    private boolean enabled;
    private String usage;
    private String visible;
    private String onChange;
    private String validation;

    // Getters et setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Map<String, Object> getTypeOptions() { return typeOptions; }
    public void setTypeOptions(Map<String, Object> typeOptions) { this.typeOptions = typeOptions; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isRequired() { return required; }
    public void setRequired(boolean required) { this.required = required; }

    public String getRegex() { return regex; }
    public void setRegex(String regex) { this.regex = regex; }

    public String getDefaultValue() { return defaultValue; }
    public void setDefaultValue(String defaultValue) { this.defaultValue = defaultValue; }

    public boolean isHidden() { return hidden; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getUsage() { return usage; }
    public void setUsage(String usage) { this.usage = usage; }

    public String getVisible() { return visible; }
    public void setVisible(String visible) { this.visible = visible; }

    public String getOnChange() { return onChange; }
    public void setOnChange(String onChange) { this.onChange = onChange; }

    public String getValidation() { return validation; }
    public void setValidation(String validation) { this.validation = validation; }
}