package com.apps.quantitymeasurement.uc6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testFeetEquality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testInchesEquality() {
        Length l1 = new Length(5.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(5.0, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testFeetInchesComparison() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testFeetInequality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void testInchesInequality() {
        Length l1 = new Length(10.0, Length.LengthUnit.INCHES);
        Length l2 = new Length(20.0, Length.LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void testCrossUnitInequality() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(10.0, Length.LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void testMultipleFeetComparison() {
        Length l1 = new Length(2.0, Length.LengthUnit.FEET);
        Length l2 = new Length(24.0, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void yardEquals36Inches() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        Length l1 = new Length(100.0, Length.LengthUnit.CENTIMETER);
        Length l2 = new Length(39.3701, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void threeFeetEqualsOneYard() {
        Length l1 = new Length(3.0, Length.LengthUnit.FEET);
        Length l2 = new Length(1.0, Length.LengthUnit.YARDS);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        Length l1 = new Length(30.48, Length.LengthUnit.CENTIMETER);
        Length l2 = new Length(1.0, Length.LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void yardNotEqualToInches() {
        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(35.0, Length.LengthUnit.INCHES);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void referenceEqualitySameObject() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);

        assertTrue(l1.equals(l1));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);

        assertFalse(l1.equals(null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length a = new Length(12.0, Length.LengthUnit.INCHES);
        Length b = new Length(1.0, Length.LengthUnit.FEET);
        Length c = new Length(1.0, Length.LengthUnit.FEET);

        // reflexive
        assertTrue(a.equals(a));

        // symmetric
        assertTrue(a.equals(b));
        assertTrue(b.equals(a));

        // transitive
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    public void differentValuesSameUnitNotEqual() {
        Length l1 = new Length(5.0, Length.LengthUnit.FEET);
        Length l2 = new Length(6.0, Length.LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void crossUnitEqualityDemonstrateMethod() {
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void convertFeetToInches(){
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.convertTo(Length.LengthUnit.INCHES);
        assertTrue(l1.equals(l2));

    }

    @Test
    public void convertYardsToInchesUsingOverloadedMethod(){

        Length l1 = new Length(3.0, Length.LengthUnit.YARDS);
        Length result = l1.convertTo(Length.LengthUnit.INCHES);
        Length expected = new Length(108.0, Length.LengthUnit.INCHES);

        assertTrue(result.equals(expected));
    }

    @Test
    public void addFeetAndInches(){
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);
        Length sumLength = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2);
        Length expectedLength = new Length(2.0, Length.LengthUnit.FEET);
        assertTrue(QuantityMeasurementApp.demonstrateLengthEquality(sumLength, expectedLength));
    }

    @Test
    public void testAdditionSameUnitFeetPlusFeet(){
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(2.0, Length.LengthUnit.FEET);

        Length result = l1.add(l2);

        assertTrue(result.equals(new Length(3.0, Length.LengthUnit.FEET)));
    }
}