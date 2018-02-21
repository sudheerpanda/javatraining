package com.imaginea.enums.exceptions;

/**
 * Throw an exception when the enum type not found
 * @author sudheerp
 */
public class EnumTypeNotFoundException extends Exception {
    public EnumTypeNotFoundException(String message) {
        super(message);
    }
}
