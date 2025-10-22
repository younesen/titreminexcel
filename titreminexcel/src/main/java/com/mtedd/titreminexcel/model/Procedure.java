package com.mtedd.titreminexcel.model;

import com.mtedd.titreminexcel.enums.ProcedureTypeEnum;
import java.util.List;

public class Procedure {
    private String name;
    private String title;
    private String description;
    private String presentationText;
    private String domain;
    private String version;
    private ProcedureTypeEnum procedureType;
    private List<BpmProcess> processes;
    private List<BpmActivity> activities;
    private List<BpmState> states;
    private List<Actor> actors;
    private List<FormElement> formElements;
    private List<FieldSet> fieldSets;
    private List<Form> forms;
    private List<GeneratedDocument> requiredDocuments;
    private List<GeneratedDocument> generatedDocument;
    private List<ActivityBinding> activityBindings;
    private List<BusinessSequence> businessSequences;
    private Documentation documentation;
    private Metadata metadata;
    private List<MapViewer> mapViewers;
    private List<Notification> notifications;
    // Getters et setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPresentationText() { return presentationText; }
    public void setPresentationText(String presentationText) { this.presentationText = presentationText; }

    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public ProcedureTypeEnum getProcedureType() { return procedureType; }
    public void setProcedureType(ProcedureTypeEnum procedureType) { this.procedureType = procedureType; }

    public List<BpmProcess> getProcesses() { return processes; }
    public void setProcesses(List<BpmProcess> processes) { this.processes = processes; }

    public List<BpmActivity> getActivities() { return activities; }
    public void setActivities(List<BpmActivity> activities) { this.activities = activities; }

    public List<BpmState> getStates() { return states; }
    public void setStates(List<BpmState> states) { this.states = states; }

    public List<Actor> getActors() { return actors; }
    public void setActors(List<Actor> actors) { this.actors = actors; }

    public List<FormElement> getFormElements() { return formElements; }
    public void setFormElements(List<FormElement> formElements) { this.formElements = formElements; }

    public List<FieldSet> getFieldSets() { return fieldSets; }
    public void setFieldSets(List<FieldSet> fieldSets) { this.fieldSets = fieldSets; }

    public List<Form> getForms() { return forms; }
    public void setForms(List<Form> forms) { this.forms = forms; }

    public List<GeneratedDocument> getRequiredDocuments() { return requiredDocuments; }
    public void setRequiredDocuments(List<GeneratedDocument> requiredDocuments) { this.requiredDocuments = requiredDocuments; }

    public List<GeneratedDocument> getGeneratedDocument() { return generatedDocument; }
    public void setGeneratedDocument(List<GeneratedDocument> generatedDocument) { this.generatedDocument = generatedDocument; }

    public List<ActivityBinding> getActivityBindings() { return activityBindings; }
    public void setActivityBindings(List<ActivityBinding> activityBindings) { this.activityBindings = activityBindings; }

    public List<BusinessSequence> getBusinessSequences() { return businessSequences; }
    public void setBusinessSequences(List<BusinessSequence> businessSequences) { this.businessSequences = businessSequences; }

    public Documentation getDocumentation() { return documentation; }
    public void setDocumentation(Documentation documentation) { this.documentation = documentation; }

    public Metadata getMetadata() { return metadata; }
    public void setMetadata(Metadata metadata) { this.metadata = metadata; }

    public List<MapViewer> getMapViewers() {
        return mapViewers;
    }

    public void setMapViewers(List<MapViewer> mapViewers) {
        this.mapViewers = mapViewers;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}