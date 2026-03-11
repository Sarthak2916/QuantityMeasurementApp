package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue(){
         Feet f1= new Feet(1.0);
         Feet f2= new Feet(1);
         assertEquals(f1,f2);
    }
    @Test
    public void testFeetEquality_DifferentValue(){
        Feet f1= new Feet(5);
        Feet f2= new Feet(4);
        assertEquals(f1,f2);
    }
    @Test
    public void testFeetEquality_NullComparison(){
        Feet f1= new Feet(1.0);
//        Feet f2= new Feet(1);
        assertEquals(null,f1);
    }
    @Test
    public void testFeetEquality_NonNumericInput(){
        Feet f1= new Feet(1.0);
        String s= "1.0";
        assertEquals(f1,s);
    }
    @Test
    public void testFeetEquality_SameReference(){
        Feet f1= new Feet(1.0);
        assertEquals(f1,f1);
    }
}
