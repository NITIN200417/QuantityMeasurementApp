package com.apps.quantitymeasurement.uc8;

public class Length {

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // Convert to another unit
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);

        result = Math.round(result * 100.0) / 100.0;

        return new Length(result, targetUnit);
    }

    // Equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Length that = (Length) obj;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = that.unit.convertToBaseUnit(that.value);

        return Double.compare(thisBase, thatBase) == 0;
    }

    // Default addition (result in same unit)
    public Length add(Length other) {
        if (other == null) {
            throw new IllegalArgumentException("Length cannot be null");
        }
        return add(other, this.unit);
    }

    // Addition with target unit
    public Length add(Length other, LengthUnit targetUnit) {

        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double thatBase = other.unit.convertToBaseUnit(other.value);

        double sum = thisBase + thatBase;

        double result = targetUnit.convertFromBaseUnit(sum);
        result = Math.round(result * 100.0) / 100.0;

        return new Length(result, targetUnit);
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}