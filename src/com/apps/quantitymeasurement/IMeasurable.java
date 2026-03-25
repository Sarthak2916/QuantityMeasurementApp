package com.apps.quantitymeasurement;

@FunctionalInterface
interface SupportsArithmetic {

    boolean isSupported();

}

public interface IMeasurable {

    SupportsArithmetic supportsArithmetic=()->true;

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    <T extends IMeasurable> double convertFromBaseToTargetUnit(double baseValue, T targetUnit);

    default boolean supportsArithmetic(){
        return supportsArithmetic.isSupported();
    }
    default void validateOperationSupport(String operation){}
}
