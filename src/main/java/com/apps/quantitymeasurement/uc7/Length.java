package com.apps.quantitymeasurement.uc7;

public class Length {

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

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit){
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit(){
        double base =value * this.unit.getConversionFactor();

        return Math.round(base * 100)/100;
    }

    private boolean compare(Length thatLength){
        if(thatLength == null) return false;
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

        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double baseValue = this.convertToBaseUnit();
        double convertdValue = baseValue / targetUnit.getConversionFactor();

        //convertdValue = Math.round(convertdValue * 100)/100;

        return new Length(convertdValue, targetUnit);
    }

    public Length add(Length thatLength){

        if(thatLength == null){
            throw new IllegalArgumentException("Length cannot be null");
        }

        if(!Double.isFinite(this.value) || !Double.isFinite(thatLength.value)){
            throw new IllegalArgumentException("length cannot be infinite");
        }

        return addAndConvert(thatLength, this.unit);
    }

    public Length add(Length length, LengthUnit targetUnit){

        if(length == null || targetUnit == null){
            throw new IllegalArgumentException("Both length and targetUnit cannot be null");
        }

        if(!Double.isFinite(this.value) || !Double.isFinite(length.value)){
            throw new IllegalArgumentException("Length values cannot be NaN or Infinite");
        }

        return addAndConvert(length, targetUnit);
    }

    private Length addAndConvert(Length length, LengthUnit targetUnit){

        double thisInches = this.convertToBaseUnit();
        double LengthInches = length.convertToBaseUnit();

        double total = thisInches + LengthInches;

        double finalValue = convertFromBaseToTargetUnit(total, targetUnit);

        return new Length(finalValue, targetUnit);
    }

    private double convertFromBaseToTargetUnit(double LengthInInches, LengthUnit targetUnit){

        double value = LengthInInches / targetUnit.getConversionFactor();

        return Math.round(value * 100.0)/ 100.0;
    }

    @Override
    public String toString(){
        return value + " " + unit;
    }

    public static void main(String[] args){

//        Length length1 = new Length(1.0, LengthUnit.FEET);
//        Length result1 = length1.convertTo(LengthUnit.INCHES);
//        System.out.println("1 FEET -> INCHES = " + result1);
//
//        Length length2 = new Length(3.0, LengthUnit.YARDS);
//        Length result2 = length2.convertTo(LengthUnit.FEET);
//        System.out.println("3 YARDS -> FEETS = " + result2);
//
//        Length length3 = new Length(100.0, LengthUnit.CENTIMETER);
//        Length result3 = length3.convertTo(LengthUnit.INCHES);
//        System.out.println("100 CM -> INCHES = " + result3);
//
//        Length l1 = new Length(1.0, LengthUnit.FEET);
//        Length l2 = new Length(12.0, LengthUnit.INCHES);
//        System.out.println("Are equal? " + l1.equals(l2));
//
//        Length ln1 = new Length(1.0, LengthUnit.FEET);
//        Length ln2 = new Length(12.0, LengthUnit.INCHES);
//
//        Length result = ln1.add(ln2);
//
//        System.out.println("1 FEET + 12 INCHES = " + result);

        System.out.println("=== UC7: Addition with Target Unit ===\n");

        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length inches12 = new Length(12.0, LengthUnit.INCHES);

        // UC7 Examples - Different Target Units
        System.out.println("1 FEET + 12 INCHES (Target: FEET)   = " + feet1.add(inches12, LengthUnit.FEET));
        System.out.println("1 FEET + 12 INCHES (Target: INCHES) = " + feet1.add(inches12, LengthUnit.INCHES));
        System.out.println("1 FEET + 12 INCHES (Target: YARDS)  = " + feet1.add(inches12, LengthUnit.YARDS));

        System.out.println("\n--- Other Test Cases ---");

        Length yard1 = new Length(1.0, LengthUnit.YARDS);
        Length feet3 = new Length(3.0, LengthUnit.FEET);
        System.out.println("1 YARD + 3 FEET (Target: YARDS)     = " + yard1.add(feet3, LengthUnit.YARDS));
        System.out.println("1 YARD + 3 FEET (Target: FEET)      = " + yard1.add(feet3, LengthUnit.FEET));

        Length cm254 = new Length(2.54, LengthUnit.CENTIMETER);
        Length inch1 = new Length(1.0, LengthUnit.INCHES);
        System.out.println("2.54 CM + 1 INCH (Target: CENTIMETER) = " + cm254.add(inch1, LengthUnit.CENTIMETER));

        // Using the old add method (UC6 style - no target unit)
        System.out.println("\n--- UC6 Style (Result in first length unit) ---");
        System.out.println("1 FEET + 12 INCHES = " + feet1.add(inches12));
    }
}