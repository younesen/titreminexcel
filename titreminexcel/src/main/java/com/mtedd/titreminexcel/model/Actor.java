package com.mtedd.titreminexcel.model;

import com.mtedd.titreminexcel.enums.ActorTypeEnum;
import com.mtedd.titreminexcel.enums.ActorResolutionTypeEnum;

public class Actor {
    private String name;
    private String role;
    private Boolean entityType;
    private ActorResolutionTypeEnum entityResolutionType;
    private String entityResolutionInput;
    private ActorTypeEnum actorType;
    private ActorResolutionTypeEnum actorResolutionType;
    private String actorResolutionInput;
    private Boolean active;
    private String description;

    // Getters et setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getEntityType() { return entityType; }
    public void setEntityType(Boolean entityType) { this.entityType = entityType; }

    public ActorResolutionTypeEnum getEntityResolutionType() { return entityResolutionType; }
    public void setEntityResolutionType(ActorResolutionTypeEnum entityResolutionType) { this.entityResolutionType = entityResolutionType; }

    public String getEntityResolutionInput() { return entityResolutionInput; }
    public void setEntityResolutionInput(String entityResolutionInput) { this.entityResolutionInput = entityResolutionInput; }

    public ActorTypeEnum getActorType() { return actorType; }
    public void setActorType(ActorTypeEnum actorType) { this.actorType = actorType; }

    public ActorResolutionTypeEnum getActorResolutionType() { return actorResolutionType; }
    public void setActorResolutionType(ActorResolutionTypeEnum actorResolutionType) { this.actorResolutionType = actorResolutionType; }

    public String getActorResolutionInput() { return actorResolutionInput; }
    public void setActorResolutionInput(String actorResolutionInput) { this.actorResolutionInput = actorResolutionInput; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}