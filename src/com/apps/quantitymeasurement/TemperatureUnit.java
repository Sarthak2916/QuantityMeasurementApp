package com.apps.quantitymeasurement;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS,
    FAHRENHEIT;

    private final SupportsArithmetic supportsArithmetic = () -> false;

    @Override
    public double convertToBaseUnit(double value) {
        double result;

        switch (this) {
            case CELSIUS:
                result = value;
                break;
            case FAHRENHEIT:
                result = (value - 32) * 5 / 9;
                break;
            default:
                throw new IllegalArgumentException("Invalid unit");
        }

        return round(result);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        double result;

        switch (this) {
            case CELSIUS:
                result = baseValue;
                break;
            case FAHRENHEIT:
                result = (baseValue * 9 / 5) + 32;
                break;
            default:
                throw new IllegalArgumentException("Invalid unit");
        }

        return round(result);
    }

    @Override
    public <T extends IMeasurable> double convertFromBaseToTargetUnit(double baseValue, T targetUnit) {
        return ((TemperatureUnit) targetUnit).convertFromBaseUnit(baseValue);
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        if (!supportsArithmetic()) {
            throw new UnsupportedOperationException(
                    this.name() + " does not support " + operation + " operation"
            );
        }
    }

    private double round(double value) {
        return new BigDecimal(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}