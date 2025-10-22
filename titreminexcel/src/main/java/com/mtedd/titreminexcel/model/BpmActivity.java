package com.mtedd.titreminexcel.model;

import com.mtedd.titreminexcel.enums.BpmActivityTypeEnum;
import java.util.List;

public class BpmActivity {
    private String qn;
    private String name;
    private String id;
    private String documentation;
    private BpmActivityTypeEnum type;
    private List<BpmActivityPort> port;

    // Getters et setters
    public String getQn() { return qn; }
    public void setQn(String qn) { this.qn = qn; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDocumentation() { return documentation; }
    public void setDocumentation(String documentation) { this.documentation = documentation; }

    public BpmActivityTypeEnum getType() { return type; }
    public void setType(BpmActivityTypeEnum type) { this.type = type; }

    public List<BpmActivityPort> getPort() { return port; }
    public void setPort(List<BpmActivityPort> port) { this.port = port; }
}