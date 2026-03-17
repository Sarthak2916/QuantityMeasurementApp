package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testEquality_NullComparison(){
        Length f1= new Length(1, Length.LengthUnit.FEET);
        assertNotEquals(null,f1);
    }
    @Test
    public void testEquality_InvalidUnit(){
        Length f1= new Length(1, Length.LengthUnit.FEET);
        String s= "1.0";
        assertNotEquals(f1,s);
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

}
