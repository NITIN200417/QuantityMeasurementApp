package com.apps.quantitymeasurement.uc5;

public class Length {

    private double value;
    private LengthUnit unit;

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETER(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }
    public Length(double value, LengthUnit unit){
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit(){
        double base = value * this.unit.getConversionFactor();

        return Math.round(base * 100)/100;
    }

    private boolean compare(Length thatLength){
        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object obj){

        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        Length that = (Length) obj;
        return compare(that);
    }

    public Length convertTo(LengthUnit targetUnit){

        double baseValue = this.convertToBaseUnit();
        double convertdValue = baseValue / targetUnit.getConversionFactor();
        convertdValue = Math.round(convertdValue * 100) /100;

        return new Length(convertdValue, targetUnit);
    }

    @Override
    public String toString(){
        return value + " " + unit;
    }

    public static void main(String[] args){

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length result1 = length1.convertTo(LengthUnit.INCHES);
        System.out.println("1 FEET -> INCHES = " + result1);

        Length length2 = new Length(3.0, LengthUnit.YARDS);
        Length result2 = length2.convertTo(LengthUnit.FEET);
        System.out.println("3 YARDS -> FEETS = " + result2);

        Length length3 = new Length(100.0, LengthUnit.CENTIMETER);
        Length result3 = length3.convertTo(LengthUnit.INCHES);
        System.out.println("100 CM -> INCHES = " + result3);

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are equal? " + l1.equals(l2));
    }
}
