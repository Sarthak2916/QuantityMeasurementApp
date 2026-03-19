package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

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
    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit){
        return length1.add(length2, targetUnit);
    }

    public static boolean demonstrateWeightEquality(Weight weight1, Weight weight2) {
        return weight1.equals(weight2);
    }

    public static void demonstrateWeightComparison(double val1, WeightUnit unit1, double val2, WeightUnit unit2){
        Weight weight1= new Weight(val1, unit1);
        Weight weight2 = new Weight(val2, unit2);
        boolean result= demonstrateWeightEquality(weight1, weight2);
        System.out.println("Are Weights "+val1+" "+unit1+" and "+val2+" "+unit2+" equal: "+result);
    }

    public static Weight demonstrateWeightConversion(double value, WeightUnit fromUnit, WeightUnit toUnit){
        return new Weight(value,fromUnit).convertTo(toUnit);
    }
    public static Weight demonstrateWeightConversion(Weight weight, WeightUnit toUnit){
        return weight.convertTo(toUnit);
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2){
        return weight1.add(weight2);
    }
    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2, WeightUnit targetUnit){
        return weight1.add(weight2, targetUnit);
    }


    public static void main(String[] args) {

        //Length
        demonstrateLengthComparison(30,LengthUnit.FEET,10,LengthUnit.YARDS);
        demonstrateLengthComparison(0.393701,LengthUnit.INCHES,1,LengthUnit.CENTIMETERS);

        Length feetToInches= demonstrateLengthConversion(4,LengthUnit.FEET,LengthUnit.INCHES);
        System.out.println(feetToInches.toString());

        Length inchesToYards= demonstrateLengthConversion(new Length(40,LengthUnit.INCHES),LengthUnit.YARDS);
        System.out.println(inchesToYards.toString());

        Length length1= new Length(12, LengthUnit.FEET);
        Length length2= new Length(40,LengthUnit.CENTIMETERS);

        System.out.println(length1.add(length2, LengthUnit.YARDS).toString());
        System.out.println(demonstrateLengthAddition(length1, length2).toString());

        //Weight
        demonstrateWeightComparison(11.023113,WeightUnit.POUND,5,WeightUnit.KILOGRAM);
        demonstrateWeightComparison(1,WeightUnit.GRAM,1000,WeightUnit.MILLIGRAM);

        Weight gramsToPounds= demonstrateWeightConversion(54000,WeightUnit.GRAM,WeightUnit.POUND);
        System.out.println(gramsToPounds.toString());

        Weight kilosToMilligrams= demonstrateWeightConversion(new Weight(4,WeightUnit.KILOGRAM),WeightUnit.MILLIGRAM);
        System.out.println(kilosToMilligrams.toString());

        Weight weight1 = new Weight(12, WeightUnit.POUND);
        Weight weight2 = new Weight(40,WeightUnit.KILOGRAM);

        System.out.println(weight1.add(weight2, WeightUnit.GRAM).toString());
        System.out.println(demonstrateWeightAddition(weight1, weight2).toString());

    }
}