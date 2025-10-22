package com.mtedd.titreminexcel.model;

import java.util.List;

public class Metadata {
    private List<Collaborator> owners;
    private TemporalDataType temporalData;
    private StatisticsType statistics;
    private List<MetadataProperty> props;

    // Constructeurs
    public Metadata() {}

    public Metadata(List<Collaborator> owners, TemporalDataType temporalData) {
        this.owners = owners;
        this.temporalData = temporalData;
    }

    // Getters et setters
    public List<Collaborator> getOwners() { return owners; }
    public void setOwners(List<Collaborator> owners) { this.owners = owners; }

    public TemporalDataType getTemporalData() { return temporalData; }
    public void setTemporalData(TemporalDataType temporalData) { this.temporalData = temporalData; }

    public StatisticsType getStatistics() { return statistics; }
    public void setStatistics(StatisticsType statistics) { this.statistics = statistics; }

    public List<MetadataProperty> getProps() { return props; }
    public void setProps(List<MetadataProperty> props) { this.props = props; }

    @Override
    public String toString() {
        return "Metadata{" +
                "owners=" + owners +
                ", temporalData=" + temporalData +
                ", statistics=" + statistics +
                ", props=" + props +
                '}';
    }
}