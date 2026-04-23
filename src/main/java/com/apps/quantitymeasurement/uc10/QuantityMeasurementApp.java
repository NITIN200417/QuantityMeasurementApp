package com.apps.quantitymeasurement.uc10;

public class QuantityMeasurementApp {


    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1, Quantity<U> quantity2){

        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit){

        return quantity.convertTo(targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2){

        return quantity1.add(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){

        return quantity1.add(quantity2, targetUnit);
    }

    public static void main(String[] args) {

        //Demonstration equality between the two quantities
        Quantity<WeightUnit> weightInGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> weightInKilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        boolean areEqual = demonstrateEquality(weightInGrams, weightInKilogram);
        System.out.println("Are weight equal? " + areEqual);

        //Demonstration conversion between the two quantities
        Quantity<WeightUnit> ConvertedWeight = demonstrateConversion(weightInGrams, WeightUnit.KILOGRAM);
        System.out.println("Converted Weight " + ConvertedWeight.getValue() + " " + ConvertedWeight.getUnit());

        //Demonstrate addition of two quantities and return the result in the unit of the first quantity
        Quantity<WeightUnit> weightInPound = new Quantity<>(2.20462, WeightUnit.POUND);
        Quantity<WeightUnit> sumWeight = demonstrateAddition(weightInKilogram, weightInPound);
        System.out.println("Sum weight: " + sumWeight.getValue() + " " + sumWeight.getUnit());

        //Demonstrate addition of two quantities and return the result in a specified target unit
        Quantity<WeightUnit> sumWeightInGrams = demonstrateAddition(weightInKilogram, weightInPound, WeightUnit.GRAM);
        System.out.println("Sum Weight in Grams : " + sumWeightInGrams.getValue() + " " + sumWeightInGrams.getUnit());

    }
}