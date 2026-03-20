package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable>
    Quantity<U> demonstrateConversion(double value, U fromUnit, U toUnit){
        return new Quantity(value,fromUnit).convertTo(toUnit);
    }
    public static <U extends IMeasurable>
    Quantity<U> demonstrateConversion(Quantity<U> quantity, U toUnit){
        return quantity.convertTo(toUnit);
    }

    public static <U extends IMeasurable>
    Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2){
        return quantity1.add(quantity2);
    }
    public static <U extends IMeasurable>
    Quantity demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        return quantity1.add(quantity2, targetUnit);
    }


    public static void main(String[] args) {

        //Length
        System.out.println(demonstrateEquality(new Quantity<>(10,LengthUnit.FEET), new Quantity<>(120,LengthUnit.INCHES)));
        Quantity<LengthUnit> feetToInches= demonstrateConversion(4,LengthUnit.FEET,LengthUnit.INCHES);
        System.out.println(feetToInches.toString());

        Quantity<LengthUnit> inchesToYards= demonstrateConversion(new Quantity<>(40,LengthUnit.INCHES),LengthUnit.YARDS);
        System.out.println(inchesToYards.toString());

        Quantity<LengthUnit> quantity1= new Quantity(12, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2= new Quantity(40,LengthUnit.CENTIMETERS);

        System.out.println(quantity1.add(quantity2, LengthUnit.YARDS).toString());
        System.out.println(demonstrateAddition(quantity1, quantity2).toString());

        //Weight
        Quantity<WeightUnit> gramsToPounds= demonstrateConversion(54000,WeightUnit.GRAM,WeightUnit.POUND);
        System.out.println(gramsToPounds.toString());

        Quantity<WeightUnit> kilosToMilligrams= demonstrateConversion(new Quantity<>(4,WeightUnit.KILOGRAM),WeightUnit.MILLIGRAM);
        System.out.println(kilosToMilligrams.toString());

        Quantity<WeightUnit> weight1 = new Quantity(12, WeightUnit.POUND);
        Quantity<WeightUnit> weight2 = new Quantity(40,WeightUnit.KILOGRAM);

        System.out.println(weight1.add(weight2, WeightUnit.GRAM).toString());
        System.out.println(demonstrateAddition(weight1, weight2).toString());

    }
}