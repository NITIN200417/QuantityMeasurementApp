package com.apps.quantitymeasurement.uc13;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable>{

    private double value;
    private U unit;

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

        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit){

        if(targetUnit == null){
            throw new IllegalArgumentException("TargetUnit cannot be null");
        }

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performArithmetic(other, targetUnit, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other){
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit){

        validateArithmeticOperands(other, targetUnit, true);

        double resultBase = performArithmetic(other, targetUnit, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    public double divide(Quantity<U> other){

        validateArithmeticOperands(other, null, false);

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = other.unit.convertToBaseUnit(other.value);

        if (thatBase == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }

        return thisBase / thatBase;
    }

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired){

        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }

        // 2. Check unit compatibility (Length != Weight)
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Incompatible unit types");
        }

        // 3. Check numeric values are valid
        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Values must be finite numbers");
        }

        // 4. Check target unit only if required
        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    private enum ArithmeticOperation{
        ADD((a,b) -> a + b),
        SUBTRACT((a,b) -> a-b),
        DIVIDE((a,b) -> {
            if(b == 0.0) throw new ArithmeticException("Divide by zero");
            return a/b;
        });

        private final DoubleBinaryOperator operation;

        ArithmeticOperation(DoubleBinaryOperator operation){
            this.operation = operation;
        }

        public double compute(double a, double b){
            return operation.applyAsDouble(a,b);
        }

    }

    private double performArithmetic(Quantity<U> other, U targetUnit, ArithmeticOperation operation){

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = other.unit.convertToBaseUnit(other.value);

        // Step 2: Perform operation
        switch (operation) {

            case ADD:
                return thisBase + thatBase;

            case SUBTRACT:
                return thisBase - thatBase;

            case DIVIDE:
                if (thatBase == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return thisBase / thatBase;

            default:
                throw new UnsupportedOperationException("Invalid operation");
        }
    }

}