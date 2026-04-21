package com.apps.quantitymeasurement.uc9;

public class QuantityMeasurementApp {

    public static boolean demonstrateWeightEquality(Weight weight1, Weight weight2){

        if(weight1 == null || weight2 == null){
            throw new IllegalArgumentException("Cannot be null");
        }
        return weight1.equals(weight2);
    }

    public static boolean demonstrateWeightComparison(double value1, WeightUnit unit1, double value2, WeightUnit unit2){

        Weight w1 = new Weight(value1, unit1);
        Weight w2 = new Weight(value2, unit2);

        return w1.equals(w2);
    }

    public static Weight demonstrateWeightConversion(double value, WeightUnit fromUnit, WeightUnit toUnit){
        Weight weight = new Weight(value, fromUnit);

        return weight.convertTo(toUnit);
    }

    public static Weight demonstrateWeightConversion(Weight weight, WeightUnit toUnit){

        return weight.convertTo(toUnit);
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2){
        return weight1.add(weight2);
    }

    public static Weight demonstrateWeightAddition(Weight weight1, Weight weight2, WeightUnit targetUnit){
        return weight1.add(weight2, targetUnit);
    }

    public static boolean demonstrateLengthEquality(Length length1, Length length2){
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthEquality(){
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        return demonstrateLengthEquality(l1, l2);
    }

    public static Length demonstrateLengthConversion(){
        Length length = new Length(3.0, LengthUnit.YARDS);

        return length.convertTo(LengthUnit.FEET);
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit){
        return length.convertTo(toUnit);
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2){

        if(length1 == null || length2 == null){
            throw new IllegalArgumentException("Length cannot be null");
        }

        return length1.add(length2);
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit){

        if(length1 == null || length2 == null || targetUnit == null){
            throw new IllegalArgumentException("Lengths and targetUnit cannot be null");
        }

        return length1.add(length2, targetUnit);
    }

    public static void main(String[] args){

        System.out.println("=== Quantity Measurement App Demo ===\n");

        // Equality Demo
        System.out.println("1. Equality Test (1 ft == 12 in): "
                + demonstrateLengthEquality());

        // Conversion Demo
        Length yard = new Length(3.0, LengthUnit.YARDS);
        System.out.println("2. 3 YARDS → FEET: "
                + demonstrateLengthConversion(yard, LengthUnit.FEET));

        // Addition Demo
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("3. 1 FEET + 12 INCHES = "
                + demonstrateLengthAddition(l1, l2));

        // Addition with target unit
        System.out.println("4. 1 FEET + 12 INCHES (Target: YARDS) = "
                + demonstrateLengthAddition(l1, l2, LengthUnit.YARDS));

        System.out.println("5. 1 YARD + 3 FEET (Target: FEET) = "
                + demonstrateLengthAddition(
                new Length(1.0, LengthUnit.YARDS),
                new Length(3.0, LengthUnit.FEET),
                LengthUnit.FEET));

        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        System.out.println("5. Weight Equality (1 KG == 1000 G): "
                + demonstrateWeightEquality(w1, w2));

        System.out.println("6. Weight Conversion (5 KG → POUND): "
                + demonstrateWeightConversion(new Weight(5.0, WeightUnit.KILOGRAM), WeightUnit.POUND));

        System.out.println("7. Weight Addition (KG): "
                + demonstrateWeightAddition(new Weight(2.0, WeightUnit.KILOGRAM),
                new Weight(500.0, WeightUnit.GRAM)));

        System.out.println("8. Weight Addition (Target GRAM): "
                + demonstrateWeightAddition(new Weight(2.0, WeightUnit.KILOGRAM),
                new Weight(500.0, WeightUnit.GRAM),
                WeightUnit.GRAM));
    }
}