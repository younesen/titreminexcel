package com.mtedd.titreminexcel.model;

public class AssignmentType {
    private String profileName;
    private String roleName;
    private BizEntityReference entityRef;
    private String assignmentRules;

    public AssignmentType() {
        this.profileName = "";
        this.roleName = "";
        this.entityRef = new BizEntityReference();
        this.assignmentRules = "";
    }

    // Getters et setters
    public String getProfileName() { return profileName; }
    public void setProfileName(String profileName) { this.profileName = profileName; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    public BizEntityReference getEntityRef() { return entityRef; }
    public void setEntityRef(BizEntityReference entityRef) { this.entityRef = entityRef; }

    public String getAssignmentRules() { return assignmentRules; }
    public void setAssignmentRules(String assignmentRules) { this.assignmentRules = assignmentRules; }
}