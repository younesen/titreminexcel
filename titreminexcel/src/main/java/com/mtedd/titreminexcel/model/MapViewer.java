package com.mtedd.titreminexcel.model;

public class MapViewer {
    private String name;
    private Boolean defaultView;
    private String description;
    private Object mapViewerRef; // Remplace BizEntityReference

    // Constructeurs
    public MapViewer() {
        this.mapViewerRef = new Object();
    }

    // Getters et setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(Boolean defaultView) {
        this.defaultView = defaultView;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getMapViewerRef() {
        return mapViewerRef;
    }

    public void setMapViewerRef(Object mapViewerRef) {
        this.mapViewerRef = mapViewerRef;
    }
}