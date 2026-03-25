package com.apps.quantitymeasurement;

public enum TemperatureUnit {

    CELSIUS(false),
    FAHRENHEIT(true);

    private final boolean isFahrenheit;
    TemperatureUnit(boolean isFahrenheit){
        this.isFahrenheit= isFahrenheit;
    }
}
