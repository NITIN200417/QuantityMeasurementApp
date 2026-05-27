package com.apps.quantitymeasurement.uc18.service;

import com.apps.quantitymeasurement.uc18.entity.QuantityDTO;
import com.apps.quantitymeasurement.uc18.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.uc18.repository.IQuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private IQuantityMeasurementRepository repository;

    // =====================================================
    // UNIT CONVERSION MAPS (all units → base unit)
    // =====================================================

    private static final Map<String, Double> LENGTH_TO_METER = new HashMap<>();
    private static final Map<String, Double> WEIGHT_TO_KG = new HashMap<>();
    private static final Map<String, Double> VOLUME_TO_LITER = new HashMap<>();
    private static final Map<String, Double> TEMP_TO_CELSIUS = new HashMap<>();

    static {
        // LENGTH → METER
        LENGTH_TO_METER.put("METER",       1.0);
        LENGTH_TO_METER.put("KILOMETER",   1000.0);
        LENGTH_TO_METER.put("CENTIMETER",  0.01);
        LENGTH_TO_METER.put("MILLIMETER",  0.001);
        LENGTH_TO_METER.put("MILE",        1609.34);
        LENGTH_TO_METER.put("YARD",        0.9144);
        LENGTH_TO_METER.put("FEET",        0.3048);
        LENGTH_TO_METER.put("INCH",        0.0254);

        // WEIGHT → KILOGRAM
        WEIGHT_TO_KG.put("KILOGRAM",   1.0);
        WEIGHT_TO_KG.put("GRAM",       0.001);
        WEIGHT_TO_KG.put("MILLIGRAM",  0.000001);
        WEIGHT_TO_KG.put("POUND",      0.453592);
        WEIGHT_TO_KG.put("OUNCE",      0.0283495);
        WEIGHT_TO_KG.put("TON",        1000.0);

        // VOLUME → LITER
        VOLUME_TO_LITER.put("LITER",        1.0);
        VOLUME_TO_LITER.put("MILLILITER",   0.001);
        VOLUME_TO_LITER.put("CUBIC_METER",  1000.0);
        VOLUME_TO_LITER.put("GALLON",       3.78541);
        VOLUME_TO_LITER.put("FLUID_OUNCE",  0.0295735);
        VOLUME_TO_LITER.put("CUP",          0.236588);
    }

    // =====================================================
    // HELPER — convert any value to base unit in meters/kg/liters
    // =====================================================

    private double toBase(QuantityDTO q) {
        String type = q.getType().toUpperCase();
        String unit = q.getUnit().toUpperCase();
        double value = q.getValue();

        switch (type) {
            case "LENGTH":
                return value * LENGTH_TO_METER.getOrDefault(unit, 1.0);
            case "WEIGHT":
                return value * WEIGHT_TO_KG.getOrDefault(unit, 1.0);
            case "VOLUME":
                return value * VOLUME_TO_LITER.getOrDefault(unit, 1.0);
            case "TEMPERATURE":
                if (unit.equals("CELSIUS"))    return value;
                if (unit.equals("FAHRENHEIT")) return (value - 32) * 5.0 / 9.0;
                if (unit.equals("KELVIN"))     return value - 273.15;
            default:
                return value;
        }
    }

    private double fromBase(double baseValue, QuantityDTO targetUnit) {
        String type = targetUnit.getType().toUpperCase();
        String unit = targetUnit.getUnit().toUpperCase();

        switch (type) {
            case "LENGTH":
                return baseValue / LENGTH_TO_METER.getOrDefault(unit, 1.0);
            case "WEIGHT":
                return baseValue / WEIGHT_TO_KG.getOrDefault(unit, 1.0);
            case "VOLUME":
                return baseValue / VOLUME_TO_LITER.getOrDefault(unit, 1.0);
            case "TEMPERATURE":
                if (unit.equals("CELSIUS"))    return baseValue;
                if (unit.equals("FAHRENHEIT")) return baseValue * 9.0 / 5.0 + 32;
                if (unit.equals("KELVIN"))     return baseValue + 273.15;
            default:
                return baseValue;
        }
    }

    // =====================================================
    // CONVERT
    // =====================================================

    @Override
    public double convert(QuantityDTO q1, QuantityDTO q2) {

        System.out.println("SERVICE : convert() running");

        double base = toBase(q1);
        return fromBase(base, q2);
    }

    // =====================================================
    // COMPARE
    // =====================================================

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        System.out.println("SERVICE : compare() running");

        double base1 = toBase(q1);
        double base2 = toBase(q2);

        boolean result = Math.abs(base1 - base2) < 0.0001;

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("COMPARE", q1.getValue(), q2.getValue(), result);

        repository.save(entity);

        return result;
    }

    // =====================================================
    // ADD
    // =====================================================

    @Override
    public double add(QuantityDTO q1, QuantityDTO q2) {

        System.out.println("SERVICE : add() running");

        double base1 = toBase(q1);
        double base2 = toBase(q2);
        double result = base1 + base2;

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", q1.getValue(), q2.getValue(), true);

        repository.save(entity);

        return result;
    }

    // =====================================================
    // SUBTRACT
    // =====================================================

    @Override
    public double subtract(QuantityDTO q1, QuantityDTO q2) {

        System.out.println("SERVICE : subtract() running");

        double base1 = toBase(q1);
        double base2 = toBase(q2);
        double result = base1 - base2;

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("SUBTRACT", q1.getValue(), q2.getValue(), true);

        repository.save(entity);

        return result;
    }

    // =====================================================
    // MULTIPLY
    // =====================================================

    @Override
    public double multiply(QuantityDTO q1, QuantityDTO q2) {

        System.out.println("SERVICE : multiply() running");

        double base1 = toBase(q1);
        double base2 = toBase(q2);
        double result = base1 * base2;

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("MULTIPLY", q1.getValue(), q2.getValue(), true);

        repository.save(entity);

        return result;
    }

    // =====================================================
    // DIVIDE
    // =====================================================

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        System.out.println("SERVICE : divide() running");

        double base1 = toBase(q1);
        double base2 = toBase(q2);

        if (base2 == 0) throw new ArithmeticException("Cannot divide by zero");

        double result = base1 / base2;

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("DIVIDE", q1.getValue(), q2.getValue(), true);

        repository.save(entity);

        return result;
    }
}