package com.mtedd.titreminexcel.model;

public class SeqStructure {
    private String sep;
    private String p1;
    private String p2;
    private String p3;
    private String p4;
    private String p5;
    private String sequenceModel;

    // Constructeurs
    public SeqStructure() {
        this.sep = "-";
        this.sequenceModel = "standard";
    }

    public SeqStructure(String sep, String p1, String p2, String p3) {
        this();
        this.sep = sep;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    // Getters et setters
    public String getSep() { return sep; }
    public void setSep(String sep) { this.sep = sep; }

    public String getP1() { return p1; }
    public void setP1(String p1) { this.p1 = p1; }

    public String getP2() { return p2; }
    public void setP2(String p2) { this.p2 = p2; }

    public String getP3() { return p3; }
    public void setP3(String p3) { this.p3 = p3; }

    public String getP4() { return p4; }
    public void setP4(String p4) { this.p4 = p4; }

    public String getP5() { return p5; }
    public void setP5(String p5) { this.p5 = p5; }

    public String getSequenceModel() { return sequenceModel; }
    public void setSequenceModel(String sequenceModel) { this.sequenceModel = sequenceModel; }

    @Override
    public String toString() {
        return "SeqStructure{" +
                "sep='" + sep + '\'' +
                ", p1='" + p1 + '\'' +
                ", p2='" + p2 + '\'' +
                ", p3='" + p3 + '\'' +
                ", p4='" + p4 + '\'' +
                ", p5='" + p5 + '\'' +
                ", sequenceModel='" + sequenceModel + '\'' +
                '}';
    }
}