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
    public void testCrossUnitEquality(){
        Length f1= new Length(1, Length.LengthUnit.FEET);
        Length i1= new Length(12, Length.LengthUnit.INCHES);
        assertEquals(f1,i1);
    }

}
