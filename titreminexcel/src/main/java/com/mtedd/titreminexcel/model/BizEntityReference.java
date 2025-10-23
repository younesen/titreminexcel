package com.mtedd.titreminexcel.model;

public class BizEntityReference {
    private String modelQN;
    private String entityId;
    private String entityName;

    public BizEntityReference() {}

    public BizEntityReference(String modelQN, String entityId, String entityName) {
        this.modelQN = modelQN;
        this.entityId = entityId;
        this.entityName = entityName;
    }

    // Getters et setters
    public String getModelQN() { return modelQN; }
    public void setModelQN(String modelQN) { this.modelQN = modelQN; }

    public String getEntityId() { return entityId; }
    public void setEntityId(String entityId) { this.entityId = entityId; }

    public String getEntityName() { return entityName; }
    public void setEntityName(String entityName) { this.entityName = entityName; }
}