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

    public static void main(String[] args) {

        demonstrateLengthComparison(12,LengthUnit.INCHES,1,LengthUnit.FEET);
        demonstrateLengthComparison(36,LengthUnit.INCHES,1,LengthUnit.YARDS);
        demonstrateLengthComparison(30,LengthUnit.FEET,10,LengthUnit.YARDS);
        demonstrateLengthComparison(0.393701,LengthUnit.INCHES,1,LengthUnit.CENTIMETERS);

    }
}