package com.mtedd.titreminexcel.model;

import com.mtedd.titreminexcel.enums.GeneratedDocumentTypeEnum;

public class GeneratedDocument {
    private String name;
    private String conditionDisplay;
    private String conditionRequire;
    private String comment;
    private String activityId;
    private String activityPort;
    private String title;
    private String description;
    private String purpose;
    private String format;
    private String applicableTo;
    private boolean multiple;
    private GeneratedDocumentTypeEnum type;
    private boolean toSign;
    private String useEparaph;
    private BizEntityReference eparaph; // UN SEUL CHAMP - BizEntityReference au lieu de Map
    private boolean toAttach;
    private boolean enabled;
    private String gedPath;
    private String gedMetadata;
    private boolean gedVersionable;
    private int maxSizeMB;
    private BizEntityReference template; // UN SEUL CHAMP - BizEntityReference au lieu de Map

    public GeneratedDocument() {
        this.eparaph = new BizEntityReference();
        this.template = new BizEntityReference();
        this.enabled = true;
    }

    // Getters et setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getConditionDisplay() { return conditionDisplay; }
    public void setConditionDisplay(String conditionDisplay) { this.conditionDisplay = conditionDisplay; }

    public String getConditionRequire() { return conditionRequire; }
    public void setConditionRequire(String conditionRequire) { this.conditionRequire = conditionRequire; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getActivityId() { return activityId; }
    public void setActivityId(String activityId) { this.activityId = activityId; }

    public String getActivityPort() { return activityPort; }
    public void setActivityPort(String activityPort) { this.activityPort = activityPort; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public String getApplicableTo() { return applicableTo; }
    public void setApplicableTo(String applicableTo) { this.applicableTo = applicableTo; }

    public boolean isMultiple() { return multiple; }
    public void setMultiple(boolean multiple) { this.multiple = multiple; }

    public GeneratedDocumentTypeEnum getType() { return type; }
    public void setType(GeneratedDocumentTypeEnum type) { this.type = type; }

    public boolean isToSign() { return toSign; }
    public void setToSign(boolean toSign) { this.toSign = toSign; }

    public String getUseEparaph() { return useEparaph; }
    public void setUseEparaph(String useEparaph) { this.useEparaph = useEparaph; }

    public BizEntityReference getEparaph() { return eparaph; } // UN SEUL GETTER
    public void setEparaph(BizEntityReference eparaph) { this.eparaph = eparaph; } // UN SEUL SETTER

    public boolean isToAttach() { return toAttach; }
    public void setToAttach(boolean toAttach) { this.toAttach = toAttach; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getGedPath() { return gedPath; }
    public void setGedPath(String gedPath) { this.gedPath = gedPath; }

    public String getGedMetadata() { return gedMetadata; }
    public void setGedMetadata(String gedMetadata) { this.gedMetadata = gedMetadata; }

    public boolean isGedVersionable() { return gedVersionable; }
    public void setGedVersionable(boolean gedVersionable) { this.gedVersionable = gedVersionable; }

    public int getMaxSizeMB() { return maxSizeMB; }
    public void setMaxSizeMB(int maxSizeMB) { this.maxSizeMB = maxSizeMB; }

    public BizEntityReference getTemplate() { return template; } // UN SEUL GETTER
    public void setTemplate(BizEntityReference template) { this.template = template; } // UN SEUL SETTER
}