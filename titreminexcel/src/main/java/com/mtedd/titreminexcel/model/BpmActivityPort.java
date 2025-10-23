package com.mtedd.titreminexcel.model;

public class BpmActivityPort {
    private Boolean outgoing;
    private String name;
    private String decision;
    private String id;
    private String documentation;
    private String condition;

    // Getters et setters
    public Boolean getOutgoing() { return outgoing; }
    public void setOutgoing(Boolean outgoing) { this.outgoing = outgoing; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDocumentation() { return documentation; }
    public void setDocumentation(String documentation) { this.documentation = documentation; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
}