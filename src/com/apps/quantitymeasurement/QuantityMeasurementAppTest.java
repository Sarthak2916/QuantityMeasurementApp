package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue(){
        Length f1= new Length(1, LengthUnit.FEET);
        Length f2= new Length(1, LengthUnit.FEET);
         assertEquals(f1,f2);
    }
    @Test
    public void testFeetEquality_DifferentValue(){
        Length f1= new Length(1, LengthUnit.FEET);
        Length f2= new Length(2, LengthUnit.FEET);
        assertNotEquals(f1,f2);
    }
    @Test
    public void testInchesEquality_SameValue(){
        Length i1= new Length(1, LengthUnit.INCHES);
        Length i2= new Length(1, LengthUnit.INCHES);
        assertEquals(i1,i2);
    }
    @Test
    public void testInchesEquality_DifferentValue(){
        Length i1= new Length(1, LengthUnit.INCHES);
        Length i2= new Length(2, LengthUnit.INCHES);
        assertNotEquals(i1,i2);
    }
    @Test
    public void testYardsEquality_SameValue(){
        Length y1= new Length(10, LengthUnit.YARDS);
        Length y2= new Length(10, LengthUnit.YARDS);
        assertEquals(y1,y2);
    }
    @Test
    public void testYardsEquality_DifferentValue(){
        Length y1= new Length(1, LengthUnit.YARDS);
        Length y2= new Length(2, LengthUnit.YARDS);
        assertNotEquals(y1,y2);
    }
    @Test
    public void testCentimetersEquality_SameValue(){
        Length c1= new Length(1, LengthUnit.CENTIMETERS);
        Length c2= new Length(1, LengthUnit.CENTIMETERS);
        assertEquals(c1,c2);
    }
    @Test
    public void testCentimetersEquality_DifferentValue(){
        Length c1= new Length(1, LengthUnit.CENTIMETERS);
        Length c2= new Length(2, LengthUnit.CENTIMETERS);
        assertNotEquals(c1,c2);
    }
    @Test
    public void testEquality_SameReference(){
        Length f1= new Length(1, LengthUnit.FEET);
        assertEquals(f1,f1);
    }
    @Test
    public void testCrossUnitEquality(){
        Length f1= new Length(1, LengthUnit.FEET);
        Length i1= new Length(12, LengthUnit.INCHES);
        assertEquals(f1,i1);
    }
    @Test
    public void testEquality_AllUnits_ComplexScenario(){
        Length length[]= {
                new Length(9, LengthUnit.FEET),
                new Length(108, LengthUnit.INCHES),
                new Length(3, LengthUnit.YARDS),
                new Length(274.32, LengthUnit.CENTIMETERS)
        };
        for(int i=0;i<length.length;i++){
            for(int j=0;j<length.length;j++){
                assertEquals(length[i],length[j]);
            }
        }
    }
    @Test
    public void testConversion_ZeroValue(){
        Length actualVal= new Length(0,LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES);
        Length expectedLen= new Length(0, LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(actualVal,expectedLen));
    }
    @Test
    public void testEquality_InvalidUnit_Throws(){
        Length length= new Length(10, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,()->{
            length.equals(null);
        });
    }
    @Test
    public void testConversion_InvalidUnit_Throws(){
        Length length= new Length(10, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,()->{
            QuantityMeasurementApp.demonstrateLengthConversion(length,null);
        });
    }
    @Test
    public void thirtyPoint48CmEqualsOneFoot(){
        Length foot= new Length(1, LengthUnit.FEET);
        Length cm= new Length(30.48,LengthUnit.CENTIMETERS);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(foot,cm));
    }
    @Test
    public void addFeetAndInches(){
        Length length1= new Length(1, LengthUnit.FEET);
        Length length2= new Length(12, LengthUnit.INCHES);
        Length sumLength= QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.FEET);
        Length expectedLength= new Length(2, LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(sumLength,expectedLength));
    }
    @Test
    public void addYardsAndCentimeters(){
        Length length1= new Length(1, LengthUnit.YARDS);
        Length length2= new Length(90, LengthUnit.CENTIMETERS);
        Length sumLength= QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.YARDS);
        Length expectedLength= new Length(1.98, LengthUnit.YARDS);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(sumLength,expectedLength));
    }
    @Test
    public void addFeetAndYardsWithTargetUnitCentimeters(){
        Length length1= new Length(2, LengthUnit.YARDS);
        Length length2= new Length(4, LengthUnit.FEET);
        Length sumLength= QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, LengthUnit.CENTIMETERS);
        Length expectedLength= new Length(304.8, LengthUnit.CENTIMETERS);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(sumLength,expectedLength));
    }

    @Test
    public void testEquality_KilogramToKilogram_SameValue(){
        Weight w1= new Weight(5, WeightUnit.KILOGRAM);
        Weight w2= new Weight(5,WeightUnit.KILOGRAM);
        assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1,w2));
    }
    @Test
    public void testEquality_KilogramToKilogram_DifferentValue(){
        Weight w1= new Weight(5, WeightUnit.KILOGRAM);
        Weight w2= new Weight(11,WeightUnit.KILOGRAM);
        assertFalse(QuantityMeasurementApp.demonstrateWeightEquality(w1,w2));
    }
    @Test
    public void testEquality_KilogramToGram_EquivalentValue(){
        Weight w1= new Weight(5, WeightUnit.KILOGRAM);
        Weight w2= new Weight(5000,WeightUnit.GRAM);
        assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1,w2));
    }
    @Test
    public void testEquality_PoundToGram_EquivalentValue(){
        Weight w1= new Weight(10, WeightUnit.POUND);
        Weight w2= new Weight(4535.92,WeightUnit.GRAM);
        assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w1,w2));
    }
    @Test
    public void testEquality_NullComparison(){
        Weight w1= new Weight(10, WeightUnit.MILLIGRAM);
        assertThrows(IllegalArgumentException.class,()->QuantityMeasurementApp.demonstrateWeightEquality(w1,null));
    }
    @Test
    public void test_NullUnit(){
        assertThrows(IllegalArgumentException.class,()->new Weight(3,null));
    }
    @Test public void testConversion_PoundToKilogram(){
        Weight actualWeight= new Weight(2.20462,WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM);
        Weight expectedWeight= new Weight(1,WeightUnit.KILOGRAM);
        assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(actualWeight,expectedWeight));
    }

    @Test public void testConversion_RoundTrip(){
        Weight w= new Weight(10, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND).convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);
        Weight expectedW= new Weight(10, WeightUnit.KILOGRAM);
        assertTrue(QuantityMeasurementApp.demonstrateWeightEquality(w,expectedW));
    }

    @Test public void testAddition_SameUnit(){

        Weight kg1= new Weight(5, WeightUnit.KILOGRAM);
        Weight kg2= new Weight(10, WeightUnit.KILOGRAM);
        Weight expectedKg= new Weight(15, WeightUnit.KILOGRAM);
        Weight actualKg= QuantityMeasurementApp.demonstrateWeightAddition(kg1,kg2);

        Weight p1= new Weight(5, WeightUnit.POUND);
        Weight p2= new Weight(10, WeightUnit.POUND);
        Weight expectedP= new Weight(15, WeightUnit.POUND);
        Weight actualP= QuantityMeasurementApp.demonstrateWeightAddition(p1,p2);

        Weight gm1= new Weight(5, WeightUnit.GRAM);
        Weight gm2= new Weight(10, WeightUnit.GRAM);
        Weight expectedGm= new Weight(15, WeightUnit.GRAM);
        Weight actualGm= QuantityMeasurementApp.demonstrateWeightAddition(gm1,gm2);

        assertAll("Test Addition Same Unit",
                ()->assertEquals(expectedKg,actualKg),
                ()->assertEquals(expectedP,actualP),
                ()->assertEquals(expectedGm,actualGm)
                );
    }

    @Test public void testAddition_CrossUnit(){

        Weight kg = new Weight(1, WeightUnit.KILOGRAM);   // 1000 g
        Weight gm = new Weight(500, WeightUnit.GRAM);     // 500 g
        Weight expectedKg = new Weight(1.5, WeightUnit.KILOGRAM);
        Weight actualKg = QuantityMeasurementApp.demonstrateWeightAddition(kg, gm);


        Weight pound = new Weight(1, WeightUnit.POUND);   // 453.592 g
        Weight gram = new Weight(546.408, WeightUnit.GRAM); // total = 1000 g
        Weight expectedPound = new Weight(2.2, WeightUnit.POUND);
        Weight actualPound = QuantityMeasurementApp.demonstrateWeightAddition(pound, gram);

        Weight gm2 = new Weight(500, WeightUnit.GRAM);
        Weight kg2 = new Weight(1, WeightUnit.KILOGRAM);
        Weight expectedGram = new Weight(1500, WeightUnit.GRAM);
        Weight actualGram = QuantityMeasurementApp.demonstrateWeightAddition(gm2, kg2);

        assertAll("Test Addition Cross Units",
                () -> assertEquals(expectedKg, actualKg),
                () -> assertEquals(expectedPound, actualPound),
                () -> assertEquals(expectedGram, actualGram)
        );
    }


}
