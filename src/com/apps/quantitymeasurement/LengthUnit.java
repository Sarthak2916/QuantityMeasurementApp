package com.apps.quantitymeasurement;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum LengthUnit implements IMeasurable {

    FEET(12.0),
    INCHES(1.0),
    YARDS(36),
    CENTIMETERS( 0.393701);

    private final double conversionFactor;

    LengthUnit(double conversionFactor){
        this.conversionFactor= conversionFactor;
    }
    public double getConversionFactor(){
        return conversionFactor;
    }

    public double convertToBaseUnit(double value){
        double baseValue= conversionFactor*value;
        BigDecimal bd= new BigDecimal(baseValue).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double convertFromBaseUnit(double baseValue){
        double targetValue= baseValue/conversionFactor;
        BigDecimal bd= new BigDecimal(targetValue).setScale(2,RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public <T extends IMeasurable> double convertFromBaseToTargetUnit(double baseValue,  T targetUnit){
        double targetValue= baseValue/targetUnit.getConversionFactor();
        BigDecimal bd= new BigDecimal(targetValue).setScale(2,RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
