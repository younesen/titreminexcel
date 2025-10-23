package com.mtedd.titreminexcel.model;

public class MetadataProperty {
    private String name;
    private String type;
    private String classifier;
    private String value;
    private String comment;
    private Boolean enabled;

    // Constructeurs
    public MetadataProperty() {
        this.enabled = true;
    }

    public MetadataProperty(String name, String type, String classifier, String value) {
        this();
        this.name = name;
        this.type = type;
        this.classifier = classifier;
        this.value = value;
    }

    // Getters et setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "MetadataProperty{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", classifier='" + classifier + '\'' +
                ", value='" + value + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}