package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Generic method to compare two lengths
    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    // Demonstrate Feet equality
    public static void demonstrateFeetEquality() {

        Length l1 = new Length(5, Length.LengthUnit.FEET);
        Length l2 = new Length(5, Length.LengthUnit.FEET);

        System.out.println("5 feet == 5 feet : " +
                demonstrateLengthEquality(l1, l2));
    }

    // Demonstrate Inches equality
    public static void demonstrateInchesEquality() {

        Length l1 = new Length(12, Length.LengthUnit.INCHES);
        Length l2 = new Length(12, Length.LengthUnit.INCHES);

        System.out.println("12 inches == 12 inches : " +
                demonstrateLengthEquality(l1, l2));
    }

    // Demonstrate Feet and Inches comparison
    public static void demonstrateFeetInchesComparison() {

        Length l1 = new Length(5, Length.LengthUnit.FEET);
        Length l2 = new Length(60, Length.LengthUnit.INCHES);

        System.out.println("5 feet == 60 inches : " +
                demonstrateLengthEquality(l1, l2));
    }

    public static void main(String[] args) {

        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}