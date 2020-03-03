package backend_models;

public class ReferencedDocument extends Obj {
    
    private InternalDocument referencedDoc;

    private String referencedText;

    public ReferencedDocument(String id) {
        super(VisualSpace.getObject(id).getTitle(), VisualSpace.getObject(id).getDescription(), id);
        Obj object = VisualSpace.getObject(id);
        if (object instanceof ExternalDocument) {
            this.referencedDoc = ((ExternalDocument) object).getInternal();
        } else if (object instanceof InternalDocument) {
            this.referencedDoc = ((InternalDocument) object);
        }
    }

    public  ReferencedDocument(ExternalDocument externalDoc) {
        super(externalDoc.getTitle(), externalDoc.getDescription(), externalDoc.getID());
        this.referencedDoc = externalDoc.getInternal();
    }
    
    public void setReferencedText(String referencedText) { this.referencedText = referencedText; }

    public String getReferencedText() { return referencedText; }
}