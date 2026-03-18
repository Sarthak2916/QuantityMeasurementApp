package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.QuantityMeasurementApp;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue(){
        Length f1= new Length(1, Length.LengthUnit.FEET);
        Length f2= new Length(1, Length.LengthUnit.FEET);
         assertEquals(f1,f2);
    }
    @Test
    public void testFeetEquality_DifferentValue(){
        Length f1= new Length(1, Length.LengthUnit.FEET);
        Length f2= new Length(2, Length.LengthUnit.FEET);
        assertNotEquals(f1,f2);
    }
    @Test
    public void testInchesEquality_SameValue(){
        Length i1= new Length(1, Length.LengthUnit.INCHES);
        Length i2= new Length(1, Length.LengthUnit.INCHES);
        assertEquals(i1,i2);
    }
    @Test
    public void testInchesEquality_DifferentValue(){
        Length i1= new Length(1, Length.LengthUnit.INCHES);
        Length i2= new Length(2, Length.LengthUnit.INCHES);
        assertNotEquals(i1,i2);
    }
    @Test
    public void testYardsEquality_SameValue(){
        Length y1= new Length(10, Length.LengthUnit.YARDS);
        Length y2= new Length(10, Length.LengthUnit.YARDS);
        assertEquals(y1,y2);
    }
    @Test
    public void testYardsEquality_DifferentValue(){
        Length y1= new Length(1, Length.LengthUnit.YARDS);
        Length y2= new Length(2, Length.LengthUnit.YARDS);
        assertNotEquals(y1,y2);
    }
    @Test
    public void testCentimetersEquality_SameValue(){
        Length c1= new Length(1, Length.LengthUnit.CENTIMETERS);
        Length c2= new Length(1, Length.LengthUnit.CENTIMETERS);
        assertEquals(c1,c2);
    }
    @Test
    public void testCentimetersEquality_DifferentValue(){
        Length c1= new Length(1, Length.LengthUnit.CENTIMETERS);
        Length c2= new Length(2, Length.LengthUnit.CENTIMETERS);
        assertNotEquals(c1,c2);
    }
    @Test
    public void testEquality_SameReference(){
        Length f1= new Length(1, Length.LengthUnit.FEET);
        assertEquals(f1,f1);
    }
    @Test
    public void testCrossUnitEquality(){
        Length f1= new Length(1, Length.LengthUnit.FEET);
        Length i1= new Length(12, Length.LengthUnit.INCHES);
        assertEquals(f1,i1);
    }
    @Test
    public void testEquality_AllUnits_ComplexScenario(){
        Length length[]= {
                new Length(9, Length.LengthUnit.FEET),
                new Length(108, Length.LengthUnit.INCHES),
                new Length(3, Length.LengthUnit.YARDS)
        };
        for(int i=0;i<length.length;i++){
            for(int j=0;j<length.length;j++){
                assertEquals(length[i],length[j]);
            }
        }
    }
    @Test
    public void testConversion_ZeroValue(){
        Length actualVal= new Length(0,Length.LengthUnit.CENTIMETERS).convertTo(Length.LengthUnit.INCHES);
        Length expectedLen= new Length(0, Length.LengthUnit.INCHES);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(actualVal,expectedLen));
    }
    @Test
    public void testEquality_InvalidUnit_Throws(){
        Length length= new Length(10, Length.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,()->{
            length.equals(null);
        });
    }
    @Test
    public void testConversion_InvalidUnit_Throws(){
        Length length= new Length(10, Length.LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class,()->{
            QuantityMeasurementApp.demonstrateLengthConversion(length,null);
        });
    }
    @Test
    public void thirtyPoint48CmEqualsOneFoot(){
        Length foot= new Length(1, Length.LengthUnit.FEET);
        Length cm= new Length(30.48,Length.LengthUnit.CENTIMETERS);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(foot,cm));
    }
    @Test
    public void addFeetAndInches(){
        Length length1= new Length(1, Length.LengthUnit.FEET);
        Length length2= new Length(12, Length.LengthUnit.INCHES);
        Length sumLength= QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.FEET);
        Length expectedLength= new Length(2, Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(sumLength,expectedLength));
    }
    @Test
    public void addYardsAndCentimeters(){
        Length length1= new Length(1, Length.LengthUnit.YARDS);
        Length length2= new Length(90, Length.LengthUnit.CENTIMETERS);
        Length sumLength= QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.YARDS);
        Length expectedLength= new Length(1.98, Length.LengthUnit.YARDS);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(sumLength,expectedLength));
    }
    @Test
    public void addFeetAndYardsWithTargetUnitCentimeters(){
        Length length1= new Length(2, Length.LengthUnit.YARDS);
        Length length2= new Length(4, Length.LengthUnit.FEET);
        Length sumLength= QuantityMeasurementApp.demonstrateLengthAddition(length1, length2, Length.LengthUnit.CENTIMETERS);
        Length expectedLength= new Length(304.8, Length.LengthUnit.CENTIMETERS);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(sumLength,expectedLength));
    }

}
