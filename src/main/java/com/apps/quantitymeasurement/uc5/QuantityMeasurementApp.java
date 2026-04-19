package com.apps.quantitymeasurement.uc5;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length length1, Length length2){
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthEquality(){
        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        return demonstrateLengthEquality(l1, l2);
    }

    public static Length demonstrateLengthConversion(){
        Length length = new Length(3.0, Length.LengthUnit.YARDS);

        return length.convertTo(Length.LengthUnit.FEET);
    }

    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit){
        return length.convertTo(toUnit);
    }

    public static void main(String[] args){

        System.out.println("Equality (1 ft == 12 in): " + demonstrateLengthEquality());

        System.out.println("3 YARDS → FEET: " + demonstrateLengthConversion());

        Length length = new Length(100.0, Length.LengthUnit.CENTIMETER);
        Length result = demonstrateLengthConversion(length, Length.LengthUnit.INCHES);

        System.out.println("100 CM → INCHES: " + result);
    }
}
