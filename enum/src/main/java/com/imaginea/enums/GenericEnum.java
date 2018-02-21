package com.imaginea.enums;

import com.imaginea.enums.exceptions.EnumTypeNotFoundException;

/**
 *This is a generic enum which will be give the values of any enum which implement this.
 *
 * @author sudheerp
 * @param <T>
 */
public interface GenericEnum<T> {

    T getValue();

    /**
     * This method accept a enum which extends @GenericEnum<T> and
     * give the corresponding value
     * @param classVal
     * @param t
     * @param <T>
     * @return
     * @throws EnumTypeNotFoundException
     */
    static <T> GenericEnum<T> getEnumFromValue(Class<? extends GenericEnum<T>> classVal, T t)
            throws EnumTypeNotFoundException {
        for(GenericEnum<T> en: classVal.getEnumConstants()){
            if(en.getValue().equals(t)) return en;
        }
        throw new EnumTypeNotFoundException("Enum not found");
    }
}
