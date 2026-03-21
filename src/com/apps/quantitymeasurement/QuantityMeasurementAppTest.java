package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue(){
        Quantity<LengthUnit> f1= new Quantity(1, LengthUnit.FEET);
        Quantity<LengthUnit> f2= new Quantity(1, LengthUnit.FEET);
         assertEquals(f1,f2);
    }
    @Test
    public void testFeetEquality_DifferentValue(){
        Quantity<LengthUnit> f1= new Quantity(1, LengthUnit.FEET);
        Quantity<LengthUnit> f2= new Quantity(2, LengthUnit.FEET);
        assertNotEquals(f1,f2);
    }
    @Test
    public void testInchesEquality_SameValue(){
        Quantity<LengthUnit> i1= new Quantity(1, LengthUnit.INCHES);
        Quantity<LengthUnit> i2= new Quantity(1, LengthUnit.INCHES);
        assertEquals(i1,i2);
    }
    @Test
    public void testInchesEquality_DifferentValue(){
        Quantity<LengthUnit> i1= new Quantity(1, LengthUnit.INCHES);
        Quantity<LengthUnit> i2= new Quantity(2, LengthUnit.INCHES);
        assertNotEquals(i1,i2);
    }
    @Test
    public void testYardsEquality_SameValue(){
        Quantity<LengthUnit> y1= new Quantity(10, LengthUnit.YARDS);
        Quantity<LengthUnit> y2= new Quantity(10, LengthUnit.YARDS);
        assertEquals(y1,y2);
    }
    @Test
    public void testYardsEquality_DifferentValue(){
        Quantity<LengthUnit> y1= new Quantity(1, LengthUnit.YARDS);
        Quantity<LengthUnit> y2= new Quantity(2, LengthUnit.YARDS);
        assertNotEquals(y1,y2);
    }
    @Test
    public void testCentimetersEquality_SameValue(){
        Quantity<LengthUnit> c1= new Quantity(1, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> c2= new Quantity(1, LengthUnit.CENTIMETERS);
        assertEquals(c1,c2);
    }
    @Test
    public void testCentimetersEquality_DifferentValue(){
        Quantity<LengthUnit> c1= new Quantity(1, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> c2= new Quantity(2, LengthUnit.CENTIMETERS);
        assertNotEquals(c1,c2);
    }
    @Test
    public void testEquality_SameReference(){
        Quantity<LengthUnit> f1= new Quantity(1, LengthUnit.FEET);
        assertEquals(f1,f1);
    }
    @Test
    public void testCrossUnitEquality(){
        Quantity<LengthUnit> f1= new Quantity(1, LengthUnit.FEET);
        Quantity<LengthUnit> i1= new Quantity(12, LengthUnit.INCHES);
        assertEquals(f1,i1);
    }
    @Test
    public void testEquality_AllUnits_ComplexScenario(){
        Quantity quantity[]= {
                new Quantity<>(9, LengthUnit.FEET),
                new Quantity<>(108, LengthUnit.INCHES),
                new Quantity<>(3, LengthUnit.YARDS),
                new Quantity<>(274.32, LengthUnit.CENTIMETERS)
        };
        for(int i=0;i<quantity.length;i++){
            for(int j=0;j<quantity.length;j++){
                assertEquals(quantity[i],quantity[j]);
            }
        }
    }
    @Test
    public void testConversion_ZeroValue(){
        Quantity<LengthUnit> actualVal= new Quantity(0,LengthUnit.CENTIMETERS).convertTo(LengthUnit.INCHES);
        Quantity<LengthUnit> expectedLen= new Quantity(0, LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(actualVal,expectedLen));
    }
    @Test
    public void testEquality_InvalidUnit_Throws(){
        Quantity<LengthUnit> quantity= new Quantity(10, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,()->{
            QuantityMeasurementApp.demonstrateEquality(quantity,null);
        });
    }
    @Test
    public void testConversion_InvalidUnit_Throws(){
        Quantity<LengthUnit> quantity= new Quantity(10, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,()->{
            QuantityMeasurementApp.demonstrateConversion(quantity,null);
        });
    }
    @Test
    public void thirtyPoint48CmEqualsOneFoot(){
        Quantity<LengthUnit> foot= new Quantity(1, LengthUnit.FEET);
        Quantity<LengthUnit> cm= new Quantity(30.48,LengthUnit.CENTIMETERS);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(foot,cm));
    }
    @Test
    public void addFeetAndInches(){
        Quantity<LengthUnit> Quantity1= new Quantity(1, LengthUnit.FEET);
        Quantity<LengthUnit> Quantity2= new Quantity(12, LengthUnit.INCHES);
        Quantity<LengthUnit> sumQuantity= QuantityMeasurementApp.demonstrateAddition(Quantity1, Quantity2, LengthUnit.FEET);
        Quantity<LengthUnit> expectedQuantity= new Quantity(2, LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(sumQuantity,expectedQuantity));
    }
    @Test
    public void addYardsAndCentimeters(){
        Quantity<LengthUnit> Quantity1= new Quantity(1, LengthUnit.YARDS);
        Quantity<LengthUnit> Quantity2= new Quantity(90, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> sumQuantity= QuantityMeasurementApp.demonstrateAddition(Quantity1, Quantity2, LengthUnit.YARDS);
        Quantity<LengthUnit> expectedQuantity= new Quantity(1.98, LengthUnit.YARDS);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(sumQuantity,expectedQuantity));
    }
    @Test
    public void addFeetAndYardsWithTargetUnitCentimeters(){
        Quantity<LengthUnit> Quantity1= new Quantity(2, LengthUnit.YARDS);
        Quantity<LengthUnit> Quantity2= new Quantity(4, LengthUnit.FEET);
        Quantity<LengthUnit> sumQuantity= QuantityMeasurementApp.demonstrateAddition(Quantity1, Quantity2, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> expectedQuantity= new Quantity(304.8, LengthUnit.CENTIMETERS);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(sumQuantity,expectedQuantity));
    }

    @Test
    public void testEquality_KilogramToKilogram_SameValue(){
        Quantity<WeightUnit> w1= new Quantity(5, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2= new Quantity(5,WeightUnit.KILOGRAM);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(w1,w2));
    }
    @Test
    public void testEquality_KilogramToKilogram_DifferentValue(){
        Quantity<WeightUnit> w1= new Quantity(5, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2= new Quantity(11,WeightUnit.KILOGRAM);
        assertFalse(QuantityMeasurementApp.demonstrateEquality(w1,w2));
    }
    @Test
    public void testEquality_KilogramToGram_EquivalentValue(){
        Quantity<WeightUnit> w1= new Quantity(5, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2= new Quantity(5000,WeightUnit.GRAM);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(w1,w2));
    }
    @Test
    public void testEquality_PoundToGram_EquivalentValue(){
        Quantity<WeightUnit> w1= new Quantity(10, WeightUnit.POUND);
        Quantity<WeightUnit> w2= new Quantity(4535.92,WeightUnit.GRAM);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(w1,w2));
    }
    @Test
    public void testEquality_NullComparison(){
        Quantity<WeightUnit> w1= new Quantity(10, WeightUnit.MILLIGRAM);
        assertThrows(IllegalArgumentException.class,()->QuantityMeasurementApp.demonstrateEquality(w1,null));
    }
    @Test
    public void test_NullUnit(){
        assertThrows(IllegalArgumentException.class,()->new Quantity(3,null));
    }
    @Test public void testConversion_PoundToKilogram(){
        Quantity<WeightUnit> actualQuantity= new Quantity(2.20462,WeightUnit.POUND).convertTo(WeightUnit.KILOGRAM);
        Quantity<WeightUnit> expectedQuantity= new Quantity(1,WeightUnit.KILOGRAM);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(actualQuantity,expectedQuantity));
    }

    @Test public void testConversion_RoundTrip(){
        Quantity<WeightUnit> w= new Quantity(10, WeightUnit.KILOGRAM).convertTo(WeightUnit.POUND).convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);
        Quantity<WeightUnit> expectedW= new Quantity(10, WeightUnit.KILOGRAM);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(w,expectedW));
    }

    @Test public void testAddition_Weight_SameUnit(){

        Quantity<WeightUnit> kg1= new Quantity(5, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> kg2= new Quantity(10, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> expectedKg= new Quantity(15, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> actualKg= QuantityMeasurementApp.demonstrateAddition(kg1,kg2);

        Quantity<WeightUnit> p1= new Quantity(5, WeightUnit.POUND);
        Quantity<WeightUnit> p2= new Quantity(10, WeightUnit.POUND);
        Quantity<WeightUnit> expectedP= new Quantity(15, WeightUnit.POUND);
        Quantity<WeightUnit> actualP= QuantityMeasurementApp.demonstrateAddition(p1,p2);

        Quantity<WeightUnit> gm1= new Quantity(5, WeightUnit.GRAM);
        Quantity<WeightUnit> gm2= new Quantity(10, WeightUnit.GRAM);
        Quantity<WeightUnit> expectedGm= new Quantity(15, WeightUnit.GRAM);
        Quantity<WeightUnit> actualGm= QuantityMeasurementApp.demonstrateAddition(gm1,gm2);

        assertAll("Test Addition Same Unit",
                ()->assertEquals(expectedKg,actualKg),
                ()->assertEquals(expectedP,actualP),
                ()->assertEquals(expectedGm,actualGm)
                );
    }

    @Test public void testAddition_Weight_CrossUnit(){

        Quantity<WeightUnit> kg = new Quantity(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> gm = new Quantity(500, WeightUnit.GRAM);
        Quantity<WeightUnit> expectedKg = new Quantity(1.5, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> actualKg = QuantityMeasurementApp.demonstrateAddition(kg, gm);


        Quantity<WeightUnit> pound = new Quantity(1, WeightUnit.POUND);
        Quantity<WeightUnit> gram = new Quantity(546.408, WeightUnit.GRAM);
        Quantity<WeightUnit> expectedPound = new Quantity(2.2, WeightUnit.POUND);
        Quantity<WeightUnit> actualPound = QuantityMeasurementApp.demonstrateAddition(pound, gram);

        Quantity<WeightUnit> gm2 = new Quantity(500, WeightUnit.GRAM);
        Quantity<WeightUnit> kg2 = new Quantity(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> expectedGram = new Quantity(1500, WeightUnit.GRAM);
        Quantity<WeightUnit> actualGram = QuantityMeasurementApp.demonstrateAddition(gm2, kg2);

        assertAll("Test Addition Cross Units",
                () -> assertEquals(expectedKg, actualKg),
                () -> assertEquals(expectedPound, actualPound),
                () -> assertEquals(expectedGram, actualGram)
        );
    }

    @Test public void testEquality_LitreToLitre_SameValue(){
        Quantity<VolumeUnit> v1= new Quantity<>(10,VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2= new Quantity<>(10,VolumeUnit.LITRE);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(v1,v2));
    }

    @Test public void testEquality_LitreToLitre_DifferentValue(){
        Quantity<VolumeUnit> v1= new Quantity<>(10,VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2= new Quantity<>(12,VolumeUnit.LITRE);
        assertFalse(QuantityMeasurementApp.demonstrateEquality(v1,v2));
    }

    @Test public void testEquality_LitreToMillilitre_EquivalentValue(){
        Quantity<VolumeUnit> v1= new Quantity<>(10,VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2= new Quantity<>(10000,VolumeUnit.MILLILITRE);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(v1,v2));
    }

    @Test public void testEquality_GallonToLitre_EquivalentValue(){
        Quantity<VolumeUnit> v1= new Quantity<>(10,VolumeUnit.GALLON);
        Quantity<VolumeUnit> v2= new Quantity<>(37.85,VolumeUnit.LITRE);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(v1,v2));
    }

    @Test public void testEquality_VolumeVsLength_Incompatible(){
        Quantity<VolumeUnit> v1= new Quantity<>(10,VolumeUnit.LITRE);
        Quantity<LengthUnit> l1= new Quantity<>(10,LengthUnit.INCHES);
        assertNotEquals(v1,l1);
    }

    @Test public void testEquality_VolumeVsWeight_Incompatible(){
        Quantity<VolumeUnit> v1= new Quantity<>(10,VolumeUnit.LITRE);
        Quantity<WeightUnit> w1= new Quantity<>(10,WeightUnit.KILOGRAM);
        assertNotEquals(v1,w1);
    }

    @Test public void testConversion_LitreToMillilitre(){
        Quantity<VolumeUnit> actualQuantity= new Quantity(2,VolumeUnit.LITRE).convertTo(VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> expectedQuantity= new Quantity(2000,VolumeUnit.MILLILITRE);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(actualQuantity,expectedQuantity));
    }

    @Test public void testConversion_GallonsToMillilitre(){
        Quantity<VolumeUnit> actualQuantity= new Quantity(2,VolumeUnit.GALLON).convertTo(VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> expectedQuantity= new Quantity(7570.82,VolumeUnit.MILLILITRE);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(actualQuantity,expectedQuantity));
    }

    @Test public void testAddition_LitreAndLitre(){
        Quantity<VolumeUnit> Quantity1= new Quantity(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> Quantity2= new Quantity(12, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sumQuantity= QuantityMeasurementApp.demonstrateAddition(Quantity1, Quantity2);
        Quantity<VolumeUnit> expectedQuantity= new Quantity(13, VolumeUnit.LITRE);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(sumQuantity,expectedQuantity));
    }

    @Test public void testAddition_LitreAndGallon(){
        Quantity<VolumeUnit> Quantity1= new Quantity(3.785, VolumeUnit.LITRE);
        Quantity<VolumeUnit> Quantity2= new Quantity(1, VolumeUnit.GALLON);
        Quantity<VolumeUnit> sumQuantity= QuantityMeasurementApp.demonstrateAddition(Quantity1, Quantity2, VolumeUnit.GALLON);
        Quantity<VolumeUnit> expectedQuantity= new Quantity(2, VolumeUnit.GALLON);
        assertTrue(QuantityMeasurementApp.demonstrateEquality(sumQuantity,expectedQuantity));
    }


}
