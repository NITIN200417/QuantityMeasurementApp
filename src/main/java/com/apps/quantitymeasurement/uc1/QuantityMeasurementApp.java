package com.apps.quantitymeasurement.uc1;

public class QuantityMeasurementApp {
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }
}