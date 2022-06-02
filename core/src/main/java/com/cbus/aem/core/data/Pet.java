package com.cbus.aem.core.data;

public class Pet {
    private String ownerName;

    private String ownerGender;

    private String name;

    private String type;

    public Pet(
        String ownerName
        , String ownerGender
        , String name
        , String type
    ) {
        this.ownerName = ownerName;
        this.ownerGender = ownerGender;
        this.name = name;
        this.type = type;
    }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public String getOwnerGender() { return ownerGender; }
    public void setOwnerGender(String ownerGender) { this.ownerGender = ownerGender; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
