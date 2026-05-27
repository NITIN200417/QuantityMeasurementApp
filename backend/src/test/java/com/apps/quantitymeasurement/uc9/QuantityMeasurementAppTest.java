package com.apps.quantitymeasurement.uc9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testInchesEquality() {
        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testFeetInchesComparison() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testFeetInequality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void testInchesInequality() {
        Length l1 = new Length(12.0, LengthUnit.INCHES);
        Length l2 = new Length(24.0, LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void testCrossUnitInequality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(10.0, LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void testMultipleFeetComparison() {
        Length l1 = new Length(3.0, LengthUnit.FEET);
        Length l2 = new Length(36.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void yardEquals36Inches() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(36.0, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        Length l1 = new Length(100.0, LengthUnit.CENTIMETER);
        Length l2 = new Length(39.3701, LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void threeFeetEqualsOneYard() {
        Length l1 = new Length(3.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.YARDS);

        assertTrue(l1.equals(l2));
    }

//    @Test
//    public void thirtyPoint48CmEqualsOneFoot() {
//        Length l1 = new Length(30.48, LengthUnit.CENTIMETER);
//        Length l2 = new Length(1.0, LengthUnit.FEET);
//
//        assertTrue(l1.equals(l2));
//    }

    @Test
    public void yardNotEqualToInches() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(30.0, LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void referenceEqualitySameObject() {
        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l1));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertFalse(l1.equals(null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length a = new Length(1.0, LengthUnit.FEET);
        Length b = new Length(12.0, LengthUnit.INCHES);
        Length c = new Length(1.0, LengthUnit.FEET);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        assertTrue(
                QuantityMeasurementApp.demonstrateLengthEquality()
        );
    }

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {
        Weight q1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue() {
        Weight q1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(2.0, WeightUnit.KILOGRAM);

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {
        Weight q1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {
        Weight q1 = new Weight(1000.0, WeightUnit.GRAM);
        Weight q2 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_NullComparison() {
        Weight q1 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertFalse(q1.equals(null));
    }

    @Test
    public void testEquality_SameReference() {
        Weight q1 = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(q1.equals(q1));
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Weight(1.0, null));
    }

    @Test
    public void testEquality_TransitiveProperty() {
        Weight a = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight b = new Weight(1000.0, WeightUnit.GRAM);
        Weight c = new Weight(1.0, WeightUnit.KILOGRAM);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void testEquality_ZeroValue() {
        Weight q1 = new Weight(0.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(0.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_NegativeWeight() {
        Weight q1 = new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(-1000.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_LargeWeightValue() {
        Weight q1 = new Weight(1000000.0, WeightUnit.GRAM);
        Weight q2 = new Weight(1000.0, WeightUnit.KILOGRAM);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_SmallWeightValue() {
        Weight q1 = new Weight(0.001, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(1.0, WeightUnit.GRAM);

        assertTrue(q1.equals(q2));
    }


    // ================= CONVERSION =================

    @Test
    public void convertFeetToInches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length result = l1.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);

        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(l1, LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), 0.01);
    }

    @Test
    public void testConversion_PoundToKilogram() {
        Weight q1 = new Weight(2.20462, WeightUnit.POUND);
        Weight result = q1.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 0.001);
    }

//    @Test
//    public void testConversion_KilogramToPound() {
//        Weight q1 = new Weight(1.0, WeightUnit.KILOGRAM);
//        Weight result = q1.convertTo(WeightUnit.POUND);
//
//        assertEquals(1000.0 / 453.592, result.getValue(), 0.001);
//    }

    @Test
    public void testConversion_SameUnit() {
        Weight q1 = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight result = q1.convertTo(WeightUnit.KILOGRAM);

        assertEquals(5.0, result.getValue(), 0.0001);
    }

    @Test
    public void testConversion_ZeroValue() {
        Weight q1 = new Weight(0.0, WeightUnit.KILOGRAM);
        Weight result = q1.convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue(), 0.0001);
    }

    @Test
    public void testConversion_NegativeValue() {
        Weight q1 = new Weight(-1.0, WeightUnit.KILOGRAM);
        Weight result = q1.convertTo(WeightUnit.GRAM);

        assertEquals(-1000.0, result.getValue(), 0.0001);
    }

    @Test
    public void testConversion_RoundTrip() {
        Weight result = new Weight(1.5, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.5, result.getValue(), 0.0001);
    }

    // ================= ADDITION =================

    @Test
    public void addFeetAndInches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    public void addFeetAndInchesWithTargetUnitInches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2, LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {
        Weight q1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(2.0, WeightUnit.KILOGRAM);

        Weight result = q1.add(q2);

        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {
        Weight q1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight result = q1.add(q2);

        assertEquals(2.0, result.getValue(), 0.0001);
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {
        Weight q1 = new Weight(2.20462, WeightUnit.POUND);
        Weight q2 = new Weight(1.0, WeightUnit.KILOGRAM);

        Weight result = q1.add(q2);

        assertEquals(2 * (1000.0 / 453.592), result.getValue(), 0.001);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram() {
        Weight q1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight result = q1.add(q2, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 0.0001);
    }

    @Test
    public void testAddition_Commutativity() {
        Weight q1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(1000.0, WeightUnit.GRAM);

        Weight ab = q1.add(q2);
        Weight ba = q2.add(q1);

        assertTrue(ab.equals(ba));
    }

    @Test
    public void testAddition_WithZero() {
        Weight q1 = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(0.0, WeightUnit.GRAM);

        Weight result = q1.add(q2);

        assertEquals(5.0, result.getValue(), 0.0001);
    }

    @Test
    public void testAddition_NegativeValues() {
        Weight q1 = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(-2000.0, WeightUnit.GRAM);

        Weight result = q1.add(q2);

        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test
    public void testAddition_LargeValues() {
        Weight q1 = new Weight(1e6, WeightUnit.KILOGRAM);
        Weight q2 = new Weight(1e6, WeightUnit.KILOGRAM);

        Weight result = q1.add(q2);

        assertEquals(2e6, result.getValue(), 0.0001);
    }
}

