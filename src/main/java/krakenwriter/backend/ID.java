package krakenwriter.backend;

import krakenwriter.frontend.VisualSpace;

/**
 *
 * @author Connor
 */
public class ID {

    private static int instances = 0;

    private String identifier; //DOC (Document), LBL (Label), CLN (Connection Line)
    private int code; //Hex Code (0x??????)

    public ID(Obj object) {
        if (object instanceof ExternalDocument) {
            identifier = "DOC";
        } else if (object instanceof Label) {
            identifier = "LBL";
        } else if (object instanceof ConnectionLine) {
            identifier = "CL";
        } else {
            new RuntimeException("Failed to give object identifier").printStackTrace();
        }

        do {
            code = ++instances;
        } while (checkID());
    }

    ID(String identifier, int code) {
        this.identifier = identifier;
        this.code = code;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String id() {
        String hex = Integer.toHexString(code);
        int length = hex.length();
        for (int i = 0; i < 6 - length; i++) {
            hex = "0" + hex;
        }
        return identifier + hex;
    }

    public static ID toID(String id) {
    	if (id.length() == 9) {
	        switch (id.substring(0, 3)) {
	            case "DOC":
	                return new ID("DOC", Integer.parseInt(id.substring(3, 9)));
	            case "LBL":
	                return new ID("LBL", Integer.parseInt(id.substring(3, 9)));
	            case "CLN":
	                return new ID("CLN", Integer.parseInt(id.substring(3, 9)));
	        }
    	}
        return null;
    }
    
    public static ID[] toID(String[] ids) {
        ID[] newIds = new ID[ids.length];
        for (int i = 0; i < ids.length; i++) {
        	newIds[i] = toID(ids[i]);
        }
        return newIds;
    }

    public static String toString(ID id) {
    	String hexCode = Integer.toHexString(id.code);
    	while (hexCode.length() < 6) {
    		hexCode = "0" + hexCode;
    	}
        return id.identifier + hexCode;
    }

    private boolean checkID() {
        return VisualSpace.getObject(this) != null;
    }

}
