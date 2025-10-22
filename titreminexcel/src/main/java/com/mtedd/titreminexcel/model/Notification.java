package com.mtedd.titreminexcel.model;

public class Notification {
    private String name;
    private String description;
    private Boolean notifyAssignation;
    private Boolean notifyStart;
    private Boolean notifyClose;
    private Object notificationRef; // Remplace BizEntityReference

    // Constructeurs
    public Notification() {
        this.notificationRef = new Object();
    }

    // Getters et setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getNotifyAssignation() {
        return notifyAssignation;
    }

    public void setNotifyAssignation(Boolean notifyAssignation) {
        this.notifyAssignation = notifyAssignation;
    }

    public Boolean getNotifyStart() {
        return notifyStart;
    }

    public void setNotifyStart(Boolean notifyStart) {
        this.notifyStart = notifyStart;
    }

    public Boolean getNotifyClose() {
        return notifyClose;
    }

    public void setNotifyClose(Boolean notifyClose) {
        this.notifyClose = notifyClose;
    }

    public Object getNotificationRef() {
        return notificationRef;
    }

    public void setNotificationRef(Object notificationRef) {
        this.notificationRef = notificationRef;
    }
}