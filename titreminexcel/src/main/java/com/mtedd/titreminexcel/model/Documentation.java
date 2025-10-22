package com.mtedd.titreminexcel.model;

public class Documentation {
    private BizEntityReference publicDocumentation;
    private BizEntityReference internalProcessingGuide;

    public Documentation() {
        this.publicDocumentation = new BizEntityReference();
        this.internalProcessingGuide = new BizEntityReference();
    }

    // Getters et setters
    public BizEntityReference getPublicDocumentation() { return publicDocumentation; }
    public void setPublicDocumentation(BizEntityReference publicDocumentation) { this.publicDocumentation = publicDocumentation; }

    public BizEntityReference getInternalProcessingGuide() { return internalProcessingGuide; }
    public void setInternalProcessingGuide(BizEntityReference internalProcessingGuide) { this.internalProcessingGuide = internalProcessingGuide; }
}