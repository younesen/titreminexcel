package com.mtedd.titreminexcel.model;

import com.mtedd.titreminexcel.enums.ProcessRoleEnum;

public class BpmProcess {
    private String qn;
    private String name;
    private String description;
    private String id;
    private String xml;
    private ProcessRoleEnum processRole;

    // Getters et setters
    public String getQn() { return qn; }
    public void setQn(String qn) { this.qn = qn; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getXml() { return xml; }
    public void setXml(String xml) { this.xml = xml; }

    public ProcessRoleEnum getProcessRole() { return processRole; }
    public void setProcessRole(ProcessRoleEnum processRole) { this.processRole = processRole; }
}