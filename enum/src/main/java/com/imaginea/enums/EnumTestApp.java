package com.imaginea.enums;

import com.imaginea.enums.exceptions.EnumTypeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Get the enum values using generic enum
 *
 * @author sudheerp
 */
public class EnumTestApp {

    public static final Logger LOGGER = LoggerFactory.getLogger(EnumTestApp.class);

    public static void main(String[] args) {

        try {
            String value = GenericEnum.getEnumFromValue(States.class, "DELHI").getValue();
            LOGGER.info("The Enum as found and the value is {}", value);
        } catch (EnumTypeNotFoundException e) {
            LOGGER.error("The enum was not found", e);
        }
    }
}
