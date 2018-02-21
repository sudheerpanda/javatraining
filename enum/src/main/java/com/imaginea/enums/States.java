package com.imaginea.enums;

/**
 * This is a states enums which implements a generic enum
 * @author sudheerp
 */
public enum States implements GenericEnum<String> {
    DELHI("Delhi"),
    CHENNAI("Chennai"),
    MUMBAI("Mumbai"),
    KOLKATTA("Kolkatta");

    String value;

    States(String value){
        this.value=value;
    }
    @Override
    public String getValue() {
        return this.value;
    }
}
