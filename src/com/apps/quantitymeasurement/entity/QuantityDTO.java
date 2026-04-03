package com.apps.quantitymeasurement.entity;

import com.apps.quantitymeasurement.unit.IMeasurable;
import com.apps.quantitymeasurement.unit.LengthUnit;
import com.apps.quantitymeasurement.unit.VolumeUnit;
import com.apps.quantitymeasurement.unit.WeightUnit;
import com.apps.quantitymeasurement.unit.TemperatureUnit;

public class QuantityDTO {

    public interface IMeasurableUnit {
        String getUnitName();
        String getMeasurementType();
    }

    // Making them public as requested
    public enum LengthUnitDTO implements IMeasurableUnit {
        FEET, INCHES, YARDS, CENTIMETERS, METERS;
        @Override public String getUnitName() { return this.name(); }
        @Override public String getMeasurementType() { return "LENGTH"; }
    }

    public enum VolumeUnitDTO implements IMeasurableUnit {
        LITRE, MILLILITRE, GALLON;
        @Override public String getUnitName() { return this.name(); }
        @Override public String getMeasurementType() { return "VOLUME"; }
    }

    public enum WeightUnitDTO implements IMeasurableUnit {
        GRAM, KILOGRAM, POUND, TONNE;
        @Override public String getUnitName() { return this.name(); }
        @Override public String getMeasurementType() { return "WEIGHT"; }
    }

    public enum TemperatureUnitDTO implements IMeasurableUnit {
        CELSIUS, FAHRENHEIT, KELVIN;
        @Override public String getUnitName() { return this.name(); }
        @Override public String getMeasurementType() { return "TEMPERATURE"; }
    }

    private double value;
    private String unit;
    private String measurementType;

    public QuantityDTO(double value, IMeasurableUnit unit) {
        this.value = value;
        this.unit = unit.getUnitName();
        this.measurementType = unit.getMeasurementType();
    }

    public double getValue() { return value; }
    public String getUnit() { return unit; }
    public String getMeasurementType() { return measurementType; }

    @Override
    public String toString() {
        return "QuantityDTO{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                ", measurementType='" + measurementType + '\'' +
                '}';
    }
}