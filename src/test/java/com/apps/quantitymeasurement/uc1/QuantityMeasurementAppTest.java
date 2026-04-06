package com.apps.quantitymeasurement.uc1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.uc1.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurement.uc1.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality_SameValue(){
        Feet f1 = new Feet(2.0);
        Feet f2 = new Feet(2.0);

        assertEquals(f1, f2);
    }

    @Test
    public void testFeetEquality_DifferentValue(){
        Feet f1 = new Feet(2.0);
        Feet f2 = new Feet(3.0);

        assertNotEquals(f1, f2);
    }

    @Test
    public void testFeetEquality_NullComparison(){
        Feet f1 = new Feet(2.0);

        assertNotEquals(f1, null);
    }

    @Test
    public void testFeetEquality_DifferentClass(){
        Feet f1 = new Feet(2.0);
        Object obj = 2.0;

        assertNotEquals(f1, obj);
    }

    @Test
    public void testFeetEquality_SameReference(){
        Feet f1 = new Feet(2.0);
        Feet f2 = f1;

        assertEquals(f1, f2);
    }

    @Test
    public void testInchesEquality_SameValue(){
        Inches i1 = new Inches(2.0);
        Inches i2 = new Inches(2.0);

        assertEquals(i1, i2);
    }

    @Test
    public void testInchesEquality_DifferentValue(){
        Inches i1 = new Inches(2.0);
        Inches i2 = new Inches(3.0);

        assertNotEquals(i1, i2);
    }

    @Test
    public void testInchesEquality_NUllComparison(){
        Inches i1 = new Inches(2.0);

        assertNotEquals(i1, null);
    }

    @Test
    public void testInchesEquality_DifferentClass(){
        Inches i1 = new Inches(2.0);
        Object obj = 2.0;

        assertNotEquals(i1, obj);
    }

    @Test
    public void testInchesEquality_SameReferencec(){
        Inches i1 = new Inches(2.0);
        Inches i2 = i1;

        assertEquals(i1, i2);
    }
}