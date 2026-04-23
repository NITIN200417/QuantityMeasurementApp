package com.apps.quantitymeasurement.uc10;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit){

        if(unit == null || !Double.isFinite(value)){
            throw new IllegalArgumentException("cannot be null and infinite");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue(){
        return value;
    }

    public U getUnit(){
        return unit;
    }

    public Quantity<U> convertTo(U targetUnit){

        if(targetUnit == null){
            throw new IllegalArgumentException("TargetUnit cannot be null");
        }

        double baseValue = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Quantity<U>(convertedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other){

        if(other == null){
            throw new IllegalArgumentException("Other cannot be Null");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cannot add different measurement types");
        }

        double thisValue = this.unit.convertToBaseUnit(this.value);
        double thatValue = other.unit.convertToBaseUnit(other.value);

        double sumBase = thisValue + thatValue;

        double result = this.unit.convertFromBaseUnit(sumBase);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit){

        if(other == null || targetUnit == null){
            throw new IllegalArgumentException("Other and TargetUnit cannot be Null");
        }

        double thisValue = this.unit.convertToBaseUnit(this.value);
        double thatValue = other.unit.convertToBaseUnit(other.value);

        double sumBase = thisValue + thatValue;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public int hashCode(){
        double base = unit.convertToBaseUnit(value);
        return Double.valueOf(base).hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;

        if(obj == null || getClass() != obj.getClass())
            return false;

        Quantity<?> that = (Quantity<?>) obj;

        if(!this.unit.getClass().equals(that.unit.getClass()))
            return false;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = that.unit.convertToBaseUnit(that.value);

        return Double.compare(thisBase, thatBase) == 0;
    }

    @Override
    public String toString(){
        return value + " " + unit;
    }

    public static void main(String[] args) {

        //Example Usage
        Quantity<LengthUnit> lengthInFeet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> lengthInInches = new Quantity<>(120.0, LengthUnit.INCHES);
        boolean isEqual = lengthInFeet.equals(lengthInInches);
        System.out.println("Are length equal? " + isEqual);

        //Example usage for WeightUnit
        Quantity<WeightUnit> weightInKilogram = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> weightInGrams = new Quantity<>(1000.0, WeightUnit.GRAM);
        isEqual = weightInKilogram.equals(weightInGrams);
        System.out.println("Are weight equal? " + isEqual);

        //Example Conversion
        Quantity<LengthUnit> convertedLength = lengthInFeet.convertTo(LengthUnit.INCHES);
        System.out.println("10 Feet in Inches: " + convertedLength);

        //Example Addition
        Quantity<LengthUnit> totalLength = lengthInFeet.add(lengthInInches, LengthUnit.FEET);
        System.out.println("Total Length in feet: " + totalLength.getValue() + " " + totalLength.getUnit());

        //Example Addition for WeightUnit
        Quantity<WeightUnit> weightInPounds = new Quantity<>(2.0, WeightUnit.POUND);
        Quantity<WeightUnit> totalWeight = weightInKilogram.add(weightInPounds, WeightUnit.KILOGRAM);
        System.out.println("Total Weight in kilograms: " + totalWeight.getValue() + " " + totalWeight.getUnit());
    }
}
