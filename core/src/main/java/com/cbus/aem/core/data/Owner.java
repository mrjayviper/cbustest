package com.cbus.aem.core.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Owner {
    private String name;

    private String genderId;

    private Integer age;

    private Pet[] pets;

    public static class Pet {
        private String name;

        private String type;

        public String getName() {  return name; }
        public void setName(String name) { this.name = name; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
    }

    public Owner() {}

    @JsonCreator
    public Owner(
        @JsonProperty("name") String name
        , @JsonProperty("gender") String genderId
        , @JsonProperty("age")Integer age
        , @JsonProperty("pets") Pet[] pets) {
        this.name = name;
        this.genderId = genderId;
        this.age = age;
        this.pets = pets;
    };

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGenderId() { return genderId; }
    public void setGenderId(String genderId) { this.genderId = genderId; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Pet[] getPets() { return pets; }
    public void setPets(Pet[] pets) {
        this.pets = pets;
    }

    public Integer numberOfPets() { return (pets != null ? pets.length : 0); }
}
