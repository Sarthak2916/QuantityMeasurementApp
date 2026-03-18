package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementApp {

    // Generic method to compare two lengths
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static void demonstrateLengthComparison(double val1, LengthUnit unit1, double val2, LengthUnit unit2){
        Length length1= new Length(val1, unit1);
        Length length2= new Length(val2, unit2);
        boolean result= demonstrateLengthEquality(length1,length2);
        System.out.println("Are lengths "+val1+" "+unit1+" and "+val2+" "+unit2+" equal: "+result);
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit){
        return new Length(value,fromUnit).convertTo(toUnit);
    }
    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit){
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2){
        return length1.add(length2);
    }

    public static void main(String[] args) {

        demonstrateLengthComparison(30,LengthUnit.FEET,10,LengthUnit.YARDS);
        demonstrateLengthComparison(0.393701,LengthUnit.INCHES,1,LengthUnit.CENTIMETERS);

        Length feetToInches= demonstrateLengthConversion(4,LengthUnit.FEET,LengthUnit.INCHES);
        System.out.println(feetToInches.toString());

        Length inchesToYards= demonstrateLengthConversion(new Length(40,LengthUnit.INCHES),LengthUnit.YARDS);
        System.out.println(inchesToYards.toString());

        Length length1= new Length(12, LengthUnit.FEET);
        Length length2= new Length(40,LengthUnit.CENTIMETERS);

        System.out.println(length1.add(length2).toString());

    }
}