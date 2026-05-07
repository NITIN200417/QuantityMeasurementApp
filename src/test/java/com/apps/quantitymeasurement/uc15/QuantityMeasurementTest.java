package com.apps.quantitymeasurement.uc15;

import com.apps.quantitymeasurement.uc15.dto.QuantityDTO;
import com.apps.quantitymeasurement.uc15.repository.QuantityMeasurementRepository;
import com.apps.quantitymeasurement.uc15.service.QuantityMeasurementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementTest {

    private QuantityMeasurementService service;

    // runs before every test

    @BeforeEach
    void setUp() {

        System.out.println(
                "\nTEST : setup running"
        );

        service =
                new QuantityMeasurementService(
                        new QuantityMeasurementRepository()
                );
    }

    // ==============================
    // SAME UNIT COMPARISON
    // ==============================

    @Test
    void testService_CompareEquality_SameUnit_Success() {

        System.out.println(
                "TEST : Same Unit Comparison"
        );

        QuantityDTO q1 =
                new QuantityDTO(
                        1,
                        "FEET",
                        "length"
                );

        QuantityDTO q2 =
                new QuantityDTO(
                        1,
                        "FEET",
                        "length"
                );

        boolean result =
                service.compare(q1, q2);

        assertTrue(result);
    }

    // ==============================
    // DIFFERENT UNIT COMPARISON
    // ==============================

    @Test
    void testService_CompareEquality_DifferentUnit_Success() {

        System.out.println(
                "TEST : Different Unit Comparison"
        );

        QuantityDTO q1 =
                new QuantityDTO(
                        1,
                        "FEET",
                        "length"
                );

        QuantityDTO q2 =
                new QuantityDTO(
                        12,
                        "INCHES",
                        "length"
                );

        boolean result =
                service.compare(q1, q2);

        assertTrue(result);
    }

    // ==============================
    // CROSS CATEGORY ERROR
    // ==============================

    @Test
    void testService_CompareEquality_CrossCategory_Error() {

        System.out.println(
                "TEST : Cross Category Comparison"
        );

        QuantityDTO q1 =
                new QuantityDTO(
                        1,
                        "FEET",
                        "length"
                );

        QuantityDTO q2 =
                new QuantityDTO(
                        1,
                        "CELSIUS",
                        "temperature"
                );

        assertThrows(
                RuntimeException.class,

                () -> service.compare(q1, q2)
        );
    }

    // ==============================
    // INVALID UNIT
    // ==============================

    @Test
    void testService_InvalidUnit_Error() {

        System.out.println(
                "TEST : Invalid Unit"
        );

        QuantityDTO q1 =
                new QuantityDTO(
                        1,
                        "INVALID",
                        "length"
                );

        QuantityDTO q2 =
                new QuantityDTO(
                        12,
                        "INCHES",
                        "length"
                );

        assertThrows(
                RuntimeException.class,

                () -> service.compare(q1, q2)
        );
    }
}