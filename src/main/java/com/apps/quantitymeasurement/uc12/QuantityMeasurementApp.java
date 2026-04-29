package com.apps.quantitymeasurement.uc12;

public class QuantityMeasurementApp {
    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit) {
        return quantity.convertTo(targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.add(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        return quantity1.add(quantity2, targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.subtract(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) {
        return quantity1.subtract(quantity2, targetUnit);
    }

    public static <U extends IMeasurable> double demonstrateDivision(Quantity<U> quantity1, Quantity<U> quantity2) {
        return quantity1.divide(quantity2);
    }

    public static void main(String[] args) {

        System.out.println("--- Weight Operations ---");
        Quantity<WeightUnit> weightInGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> weightInKilograms = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        System.out.println("Are weights equal? " + demonstrateEquality(weightInGrams, weightInKilograms));

        Quantity<WeightUnit> convertedWeight = demonstrateConversion(weightInGrams, WeightUnit.KILOGRAM);
        System.out.println("Converted Weight: "+ convertedWeight.getValue() + " "+ convertedWeight.getUnit());

        Quantity<WeightUnit> weightInPounds = new Quantity<>(2.20462, WeightUnit.POUND);
        Quantity<WeightUnit> sumWeight = demonstrateAddition(weightInKilograms, weightInPounds);
        System.out.println("Sum Weight: "+ sumWeight.getValue() + " "+ sumWeight.getUnit());

        Quantity<WeightUnit> sumWeightInGrams = demonstrateAddition(weightInKilograms, weightInPounds, WeightUnit.GRAM);
        System.out.println("Sum Weight in Grams: " +sumWeightInGrams.getValue() + " "+ sumWeightInGrams.getUnit());


        System.out.println("\n--- Length Operations ---");
        Quantity<LengthUnit> l1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(12.0, LengthUnit.INCHES);
        System.out.println("Input: new Quantity<>(1.0, FEET).equals(new Quantity<>(12.0, INCHES)) → Output: "+ l1.equals(l2));
        System.out.println("Input: new Quantity<>(1.0, FEET).convertTo(INCHES) → Output: "+ l1.convertTo(LengthUnit.INCHES));
        System.out.println("Input: add in FEET → Output: "+ l1.add(l2, LengthUnit.FEET));


        System.out.println("\n--- Volume Operations (UC11) ---");
        Quantity<VolumeUnit> vLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> vMilli = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> vGallon = new Quantity<>(1.0, VolumeUnit.GALLON);

        // Equality
        System.out.println("Input: new Quantity<>(1.0, LITRE).equals(new Quantity<>(1000.0, MILLILITRE)) → Output: " + vLitre.equals(vMilli));
        System.out.println("Input: new Quantity<>(3.78541, LITRE).equals(new Quantity<>(1.0, GALLON)) → Output: " + new Quantity<>(3.78541, VolumeUnit.LITRE).equals(vGallon));

        // Conversion
        System.out.println("Input: new Quantity<>(1.0, LITRE).convertTo(MILLILITRE) → Output: " + vLitre.convertTo(VolumeUnit.MILLILITRE));
        System.out.println("Input: new Quantity<>(2.0, GALLON).convertTo(LITRE) → Output: " + new Quantity<>(2.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE));

        // Addition
        System.out.println("Input: 1.0 LITRE + 1000.0 MILLILITRE (Implicit) → Output: " + vLitre.add(vMilli));
        System.out.println("Input: 1.0 LITRE + 1000.0 MILLILITRE (Explicit MILLILITRE) → Output: " + vLitre.add(vMilli, VolumeUnit.MILLILITRE));


        System.out.println("\n--- Cross Category Incompatibility ---");
        Quantity<LengthUnit> len = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> wt = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        System.out.println("Length vs Weight equals → Output: " + len.equals(wt));
        System.out.println("Volume vs Length equals → Output: " + vLitre.equals(len));
        System.out.println("Volume vs Weight equals → Output: " + vLitre.equals(wt));
    }
}
