package com.apps.quantitymeasurement.uc7;

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

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = length1.add(length2, Length.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.001);           // tolerance for floating point
        assertEquals(Length.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches(){

        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = length1.add(length2, Length.LengthUnit.INCHES);

        assertEquals(24.0, result.getValue(), 0.001);
        assertEquals(Length.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards(){

        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = length1.add(length2, Length.LengthUnit.YARDS);

        assertEquals(0.67, result.getValue(), 0.001);
        assertEquals(Length.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters(){

        Length length1 = new Length(1.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(1.0, Length.LengthUnit.INCHES);

        Length result = length1.add(length2, Length.LengthUnit.CENTIMETER);

        assertEquals(5.08, result.getValue(), 0.001);
        assertEquals(Length.LengthUnit.CENTIMETER, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand(){

        Length length1 = new Length(2.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(3.0, Length.LengthUnit.FEET);

        Length result = length1.add(length2, Length.LengthUnit.YARDS);

        assertEquals(3.0, result.getValue(), 0.001);
        assertEquals(Length.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand(){

        Length length1 = new Length(2.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(3.0, Length.LengthUnit.FEET);

        Length result = length1.add(length2, Length.LengthUnit.FEET);

        assertEquals(9.0, result.getValue(), 0.001);
        assertEquals(Length.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity(){

        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result1 = length1.add(length2, Length.LengthUnit.YARDS);
        Length result2 = length2.add(length1, Length.LengthUnit.YARDS);

        assertEquals(result1.getValue(), result2.getValue(), 0.001);
        assertEquals(result1.getUnit(), result2.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero(){

        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(0.0, Length.LengthUnit.INCHES);

        Length result = length1.add(length2, Length.LengthUnit.YARDS);

        assertEquals(1.67, result.getValue(), 0.001);
        assertEquals(Length.LengthUnit.YARDS, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues(){

        Length length1 = new Length(5.0, Length.LengthUnit.FEET);
        Length length2 = new Length(-2.0, Length.LengthUnit.FEET);

        Length result = length1.add(length2, Length.LengthUnit.INCHES);

        assertEquals(36.0, result.getValue(), 0.001);
        assertEquals(Length.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit(){

        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertThrows(IllegalArgumentException.class, () -> {
            length1.add(length2, null);
        });
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale(){

        Length length1 = new Length(1000.0, Length.LengthUnit.FEET);
        Length length2 = new Length(500.0, Length.LengthUnit.FEET);

        Length result = length1.add(length2, Length.LengthUnit.INCHES);

        assertEquals(18000.0, result.getValue(), 0.001);
        assertEquals(Length.LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale(){

        Length length1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = length1.add(length2, Length.LengthUnit.YARDS);

        assertEquals(0.67, result.getValue(), 0.001);
        assertEquals(Length.LengthUnit.YARDS, result.getUnit());
    }


}