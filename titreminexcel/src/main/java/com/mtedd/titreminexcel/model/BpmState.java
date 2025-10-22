package com.mtedd.titreminexcel.model;

import java.util.List;

public class BpmState {
    private String qn;
    private String title;
    private String subtitle;
    private Integer slaDuration;
    private String slaUnit;
    private String documentation;
    private List<String> activityNames;

    // Constructeurs
    public BpmState() {
        this.slaDuration = 24;
        this.slaUnit = "hours";
    }

    // Getters et setters
    public String getQn() {
        return qn;
    }

    public void setQn(String qn) {
        this.qn = qn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getSlaDuration() {
        return slaDuration;
    }

    public void setSlaDuration(Integer slaDuration) {
        this.slaDuration = slaDuration;
    }

    public String getSlaUnit() {
        return slaUnit;
    }

    public void setSlaUnit(String slaUnit) {
        this.slaUnit = slaUnit;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public List<String> getActivityNames() {
        return activityNames;
    }

    public void setActivityNames(List<String> activityNames) {
        this.activityNames = activityNames;
    }

    @Override
    public String toString() {
        return "BpmState{" +
                "qn='" + qn + '\'' +
                ", title='" + title + '\'' +
                ", slaDuration=" + slaDuration +
                ", slaUnit='" + slaUnit + '\'' +
                ", activityNames=" + activityNames +
                '}';
    }
}