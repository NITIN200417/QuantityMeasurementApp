package com.apps.quantitymeasurement.uc11;

public class Quantity<U extends IMeasurable>{

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
}
