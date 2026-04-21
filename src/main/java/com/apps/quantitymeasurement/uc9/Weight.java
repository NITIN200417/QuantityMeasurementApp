package com.apps.quantitymeasurement.uc9;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit){

        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    public double getValue(){
        return value;
    }

    public WeightUnit getUnit(){
        return unit;
    }

    @Override
    public boolean equals(Object obj){

        if(this == obj){
            return true;
        }

        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Weight that = (Weight) obj;

        return compare(that);
    }

    public Weight convertTo(WeightUnit targetUnit){

        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseUnit = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseUnit);

        convertedValue = Math.round(convertedValue * 100.0) / 100.0;

        return new Weight(convertedValue, targetUnit);
    }

    public Weight add(Weight thatWeight){
        return addAndConvert(thatWeight, this.unit);
    }

    public Weight add(Weight weight, WeightUnit targetUnit){
        return  addAndConvert(weight, targetUnit);
    }

    private double convertToBaseUnit(){
        double base = value * this.unit.getConversionFactor();

        return Math.round(base * 100.0)/100.0;
    }

    private boolean compare(Weight thatWeight){

        if(thatWeight == null) {
            return false;
        }

        return Double.compare(this.convertToBaseUnit(), thatWeight.convertToBaseUnit()) == 0;
    }

    private Weight addAndConvert(Weight weight, WeightUnit targetUnit){

        double thisWeight = this.convertToBaseUnit();
        double WeightGram = weight.convertToBaseUnit();

        double total = thisWeight + WeightGram;

        double finalValue = convertFrombaseToTargetUnit(total, targetUnit);

        return new Weight(finalValue, targetUnit);
    }

    private double convertFrombaseToTargetUnit(double weightInGrams, WeightUnit targetUnit){

        double value = weightInGrams / targetUnit.getConversionFactor();

        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString(){
        return value + " " + unit;
    }

    public static void main(String[] args) {

        // 🔹 Equality Test
        Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight w2 = new Weight(1000.0, WeightUnit.GRAM);

        System.out.println("1. Equality Test (1 KG == 1000 G): " + w1.equals(w2));

        // 🔹 Conversion Test
        Weight w3 = new Weight(10.0, WeightUnit.KILOGRAM);
        Weight converted = w3.convertTo(WeightUnit.POUND);

        System.out.println("2. 10 KG → POUNDS: " + converted);

        // 🔹 Addition (same unit result)
        Weight w4 = new Weight(2.0, WeightUnit.KILOGRAM);
        Weight w5 = new Weight(500.0, WeightUnit.GRAM);

        System.out.println("3. 2 KG + 500 G (KG): " + w4.add(w5));

        // 🔹 Addition with target unit
        System.out.println("4. 2 KG + 500 G (GRAM): " +
                w4.add(w5, WeightUnit.GRAM));

        // 🔹 Cross unit addition
        Weight w6 = new Weight(1.0, WeightUnit.POUND);
        Weight w7 = new Weight(500.0, WeightUnit.GRAM);

        System.out.println("5. 1 POUND + 500 G (GRAM): " +
                w6.add(w7, WeightUnit.GRAM));

        // 🔹 Chain conversion
        Weight w8 = new Weight(5.0, WeightUnit.KILOGRAM);
        Weight result = w8.convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.POUND);

        System.out.println("6. 5 KG → GRAM → POUND: " + result);
    }
}
