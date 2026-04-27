package com.apps.quantitymeasurement.uc11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    //Tests for Generalized Quantity Class covering Length and Weight Units

    @Test
    public void lengthFeetEqualsInches() {
        Quantity<LengthUnit> quantity1 = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(12, LengthUnit.INCHES);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void lengthYardsEqualsFeet() {
        Quantity<LengthUnit> quantity1 = new Quantity<>(12, LengthUnit.YARDS);
        Quantity<LengthUnit> quantity2 = new Quantity<>(36, LengthUnit.FEET);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void weightKilogramEqualsGrams() {
        Quantity<WeightUnit> quantity1 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> quantity2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void weightPoundEqualsGrams() {
        Quantity<WeightUnit> quantity1 = new Quantity<>(453.592, WeightUnit.GRAM);
        Quantity<WeightUnit> quantity2 = new Quantity<>(1.0, WeightUnit.POUND);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void testEquality_LitreToLitre_SameValue() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1.0, VolumeUnit.LITRE);

        assertTrue(v1.equals(v2));
    }

    @Test
    public void testEquality_LitreToMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(v1.equals(v2));
    }

//    @Test
//    public void testEquality_LitreToGallon() {
//        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
//        Quantity<VolumeUnit> v2 = new Quantity<>(0.264172, VolumeUnit.GALLON);
//
//        assertTrue(v1.equals(v2));
//    }

    //Addition tests for conversion and addition can be added similarly

    @Test
    public void convertLengthFeetToInches() {
        Quantity<LengthUnit> quantity = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = quantity.convertTo(LengthUnit.INCHES);

        assertEquals(120, result.getValue(), 0.01);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    @Test
    public void addLengthFeetAndInches() {
        Quantity<LengthUnit> quantity1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = quantity1.add(quantity2);

        assertEquals(2.0, result.getValue(), 0.01);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void addWeightKilogramsAndGram() {
        Quantity<WeightUnit> quantity1 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> quantity2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = quantity1.add(quantity2);

        assertEquals(2000.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), 0.01);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    @Test
    public void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), 0.01);
    }

    @Test
    public void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> v = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v.convertTo(VolumeUnit.GALLON);

        assertEquals(0.264172, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_LitrePlusLitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(3.0, result.getValue(), 0.01);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_LitrePlusMillilitre() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = v1.add(v2);

        assertEquals(2.0, result.getValue(), 0.01);
        assertEquals(VolumeUnit.LITRE, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit() {
        Quantity<VolumeUnit> v1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> v2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result =
                v1.add(v2, VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue(), 0.01);
        assertEquals(VolumeUnit.MILLILITRE, result.getUnit());
    }

    //Generic Type Safety tests

    @Test
    public void testGenericTypeSafetyWithWeight() {
        Quantity<WeightUnit> quantity1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> quantity2 = new Quantity<>(500.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = quantity1.add(quantity2);

        assertEquals(1.5, result.getValue(), 0.01);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void convertWeightKilogramsToGrams() {
        Quantity<WeightUnit> quantity = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = quantity.convertTo(WeightUnit.GRAM);

        assertEquals(1000, result.getValue(), 0.001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void convertLengthYardsToInches() {
        Quantity<LengthUnit> quantity = new Quantity<>(1, LengthUnit.YARDS);
        Quantity<LengthUnit> result = quantity.convertTo(LengthUnit.INCHES);

        assertEquals(36, result.getValue(), 0.1);
        assertEquals(LengthUnit.INCHES, result.getUnit());
    }

    //Negative Test fir Cross-Type Operation

    @Test
    public void preventCrossTypeComparisonLengthVsWeight() {
        Quantity<LengthUnit> quantity1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> quantity2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Object obj = quantity2;
        assertFalse(quantity1.equals(obj));
    }

    @Test
    public void preventCrossTypeAdditionLengthVsWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> {
            Quantity unsafe = (Quantity) weight;
            length.add(unsafe);
        });
    }

    @Test
    public void preventCrossTypeConversionLengthToWeight() {

        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(ClassCastException.class, () -> {
            length.convertTo((LengthUnit) (Object) WeightUnit.KILOGRAM);
        });
    }

    @Test
    public void addLengthYardsAndFeet() {

        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);

        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = yard.add(feet, LengthUnit.FEET);

        assertEquals(6.0, result.getValue(), 0.01);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void addWeightTonnesAndKilograms() {

        Quantity<WeightUnit> tonne =
                new Quantity<>(1.0, WeightUnit.TONNE);

        Quantity<WeightUnit> kg =
                new Quantity<>(500.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                tonne.add(kg, WeightUnit.KILOGRAM);

        assertEquals(1500.0, result.getValue(), 0.01);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testVolumeVsLengthComparison() {
        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(volume.equals(length));
    }

    @Test
    public void testVolumeVsWeightComparison() {
        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(volume.equals(weight));
    }

    //Backward Compatibility Tests with Previous Implementations

    @Test
    public void backwardCompatibilityLengthFeetEqualsInches() {
        Quantity<LengthUnit> quantity1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(12.0, LengthUnit.INCHES);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void backwardCompatibilityWeightKilogramEqualsGram() {
        Quantity<WeightUnit> quantity1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> quantity2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void backwardCompatibilityConvertLengthFeetToInches() {

        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = length.convertTo(LengthUnit.INCHES);

        assertEquals(120.0, result.getValue(), 0.01);
    }

    @Test
    public void backwardCompatibilityConvertWeightKilogramsToGrams() {

        Quantity<WeightUnit> weight = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = weight.convertTo(WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 0.01);
    }

    @Test
    public void backwardCompatibilityAddLengthInSameUnit() {

        Quantity<LengthUnit> l1 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(3.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = l1.add(l2);

        assertEquals(5.0, result.getValue(), 0.01);
        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void backwardCompatibilityAddWeightInSameUnit() {

        Quantity<WeightUnit> w1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(3.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = w1.add(w2);

        assertEquals(5.0, result.getValue(), 0.01);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void backwardCompatibilityLengthYardsEqualsFeet() {

        Quantity<LengthUnit> yard = new Quantity<>(1.0, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = new Quantity<>(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    public void backwardCompatibilityWeightPoundEqualsGrams() {

        Quantity<WeightUnit> pound = new Quantity<>(1.0, WeightUnit.POUND);
        Quantity<WeightUnit> grams = new Quantity<>(453.592, WeightUnit.GRAM);

        assertTrue(pound.equals(grams));
    }

    @Test
    public void backwardCompatibilityChainedAdditionsLength() {

        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
        Quantity<LengthUnit> l3 = new Quantity<>(1.0, LengthUnit.YARDS);

        Quantity<LengthUnit> result = l1.add(l2).add(l3);

        // 1 ft + 1 ft + 3 ft = 5 ft
        assertEquals(5.0, result.getValue(), 0.01);
    }
}