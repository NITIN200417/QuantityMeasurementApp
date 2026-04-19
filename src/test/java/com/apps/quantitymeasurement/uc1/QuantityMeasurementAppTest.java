package com.apps.quantitymeasurement.uc1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {
    QuantityMeasurementApp.Feet obj1 = new QuantityMeasurementApp.Feet(2.0);
    QuantityMeasurementApp.Feet obj2 = new QuantityMeasurementApp.Feet(2.0);
    Object obj3 = obj2;
    Object obj4 = obj1;
    Object nullObject = null;
    Object generic = 2.0;
    QuantityMeasurementApp.Feet obj5 = new QuantityMeasurementApp.Feet(3.0);

    @Test // @Reflexive
    void shouldReturnTrue_whenObjectIsComparedWithItself() {
        assertTrue(obj1.equals(obj1));
    }

    @Test
    void shouldReturnTrue_whenFeetValuesAreEqual() {
        assertTrue(obj1.equals(obj2));
    }

    @Test //Symmetric
    void shouldReturnTrue_whenFeetValuesAreEqual_Symmetric() {
        assertTrue(obj2.equals(obj1));
    }

    @Test
    void shouldReturnTrue_whenFeetValuesAreEqual_Transitive() {
        assertTrue(obj3.equals(obj4));
    }

    @Test
    void shouldReturnFalse_whenFeetValuesAreNotEqual() {
        assertEquals(obj1.equals(obj5), false);
    }

    @Test
    void shouldReturnFalse_whenFeetValuesAreNull() {
        assertEquals(obj1.equals(nullObject), false);
    }

    @Test
    void shouldReturnFalse_whenFeetValuesAreEmpty() {
        assertEquals(obj1.equals(new QuantityMeasurementApp.Feet(0.0)), false);
    }

    @Test
    void shouldReturnFalse_whenObjectHasDifferentClass() {
        assertEquals(obj1.equals(generic), false);
    }
}