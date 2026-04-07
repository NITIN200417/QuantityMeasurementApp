package com.apps.quantitymeasurement.uc3;

public class Length {

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor){
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor(){
            return conversionFactor;
        }
    }

    private double value;
    private LengthUnit unit;

    public Length(double value, LengthUnit unit){
        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit(){
        return this.value * this.unit.getConversionFactor();
    }

    public boolean compare(Length thatLength){
        return Double.compare(this.convertToBaseUnit(),thatLength.convertToBaseUnit()) == 0;
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


    public static void main(String[] args){

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length1.equals(length2));
    }
}
