package com.riddlin.app.message;

public enum MessageType {
    
    /**
     * The message is informative in nature, like a note or notice.
     */
    INFO("alert-info"), 

    /**
     * The message indicates that an action initiated by the user was performed successfully.
     */
    SUCCESS("alert-success"), 
    
    /**
     * The message warns the user something is not quite right.
     * Corrective action is generally recommended but not required.
     */
    WARNING("alert-block"), 
    
    /**
     * The message reports an error condition that needs to be addressed by the user.
     */
    ERROR("alert-error");
    
    private final String cssClass;
    
    private MessageType(String cssClass) {
        this.cssClass = cssClass; 
    }
    
    /**
     * The css class for styling messages of this type.
     */
    public String getCssClass() {
        return cssClass;
    }
}
