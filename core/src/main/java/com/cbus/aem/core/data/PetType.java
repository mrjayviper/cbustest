package com.cbus.aem.core.data;

public class PetType {
    private String displayName;

    private String value;

    public PetType(
        String displayName
        , String value
    ) {
        this.displayName = displayName;
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
