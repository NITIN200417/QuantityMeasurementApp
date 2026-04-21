package com.apps.quantitymeasurement.uc8;

public class QuantityMeasurementApp {

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
    }
}