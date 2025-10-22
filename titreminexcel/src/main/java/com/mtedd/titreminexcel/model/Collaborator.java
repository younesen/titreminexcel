package com.mtedd.titreminexcel.model;

import com.mtedd.titreminexcel.enums.ActorTypeEnum;

public class Collaborator {
    private String name;
    private String role;
    private BizEntityReference entityRef; // UN SEUL CHAMP
    private String email;
    private String userId;
    private ActorTypeEnum type;
    private boolean active;

    public Collaborator() {
        this.entityRef = new BizEntityReference();
        this.active = true;
    }

    // Getters et setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public BizEntityReference getEntityRef() { return entityRef; } // UN SEUL GETTER
    public void setEntityRef(BizEntityReference entityRef) { this.entityRef = entityRef; } // UN SEUL SETTER

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public ActorTypeEnum getType() { return type; }
    public void setType(ActorTypeEnum type) { this.type = type; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}