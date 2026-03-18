package com.apps.quantitymeasurement;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Length {

    private double value;
    private LengthUnit unit;
    private double convertedValue;

    public enum LengthUnit{

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
    }

    public Length(double value, LengthUnit unit){
        this.value= value;
        this.unit= unit;
        this.convertedValue= convertToBaseUnit();
    }
    private double convertToBaseUnit(){
        return unit.conversionFactor*value;
    }
    public boolean compare(Length length){
        BigDecimal bd1= new BigDecimal(this.convertedValue).setScale(2,RoundingMode.HALF_UP);
        BigDecimal bd2= new BigDecimal(length.convertedValue).setScale(2,RoundingMode.HALF_UP);
        return Double.compare(bd1.doubleValue(),bd2.doubleValue())==0 ? true:false;
    }
    @Override
    public boolean equals(Object obj) throws IllegalArgumentException{
        if(obj==this) return true;
        if(obj==null || obj.getClass()!=Length.class) throw new IllegalArgumentException("Not a valid Argument");
        return compare((Length) obj);
    }

    public Length convertTo(LengthUnit targetUnit) throws IllegalArgumentException{
        if(targetUnit==null) throw new IllegalArgumentException("Not a valid Argument");
        double value=convertedValue/targetUnit.getConversionFactor();
        BigDecimal bd= new BigDecimal(value);
        bd= bd.setScale(2, RoundingMode.HALF_UP);
        double roundedVal= bd.doubleValue();
        return new Length(roundedVal, targetUnit);
    }

    public Length add(Length length, LengthUnit targetUnit){
        return addAndConvert(length, targetUnit);
    }
    private Length addAndConvert(Length length, LengthUnit targetUnit){
        double addition= this.convertedValue+length.convertedValue;
        double value= convertFromBaseToTargetUnit(addition, targetUnit);
        return new Length(value, targetUnit);
    }
    private double convertFromBaseToTargetUnit(double LengthInInches, LengthUnit targetUnit){
        double val= LengthInInches/targetUnit.getConversionFactor();
        BigDecimal bd= new BigDecimal(val);
        bd= bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String toString(){
        return value+" "+unit;
    }

    public static void main(String[] args) {

        Length l1= new Length(27,LengthUnit.FEET);
        Length l2= new Length(9,LengthUnit.YARDS);
        System.out.println("Are lengths equal: "+l1.equals(l2));

        Length len1= new Length(5, LengthUnit.FEET);
        Length converted= len1.convertTo(LengthUnit.INCHES);
        System.out.println(converted.toString());

        System.out.println(l2.add(l1, LengthUnit.CENTIMETERS).toString());

    }
}
