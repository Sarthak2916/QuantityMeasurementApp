package com.apps.quantitymeasurement;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum WeightUnit implements IMeasurable{

    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592);

    private final double conversionFactor;

    WeightUnit(double conversionFactor){
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

    public <T extends IMeasurable> double convertFromBaseToTargetUnit(double baseValue, T targetUnit){
        double targetValue= baseValue/targetUnit.getConversionFactor();
        BigDecimal bd= new BigDecimal(targetValue).setScale(2,RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
