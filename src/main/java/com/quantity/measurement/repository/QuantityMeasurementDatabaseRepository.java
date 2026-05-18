package com.quantity.measurement.repository;

// cache repository

import com.quantity.measurement.entity.QuantityMeasurementEntity;
import com.quantity.measurement.util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;

// database repository

// JDBC repository

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    // connection pool object

    private final
    ConnectionPool pool =
            new ConnectionPool();

    // constructor

    public QuantityMeasurementDatabaseRepository() {

        System.out.println(
                "DATABASE REPOSITORY CREATED"
        );
    }

    // save into database

    @Override
    public void save(
            QuantityMeasurementEntity entity) {

        System.out.println(
                "REPOSITORY : save() running"
        );

        String sql =
                "INSERT INTO measurement " +
                        "(operation, value1, value2, result) " +
                        "VALUES (?, ?, ?, ?)";

        try {

            // STEP 1
            // get connection

            Connection con =
                    pool.getConnection();

            // STEP 2
            // prepare query

            PreparedStatement ps =
                    con.prepareStatement(sql);

            // STEP 3
            // set values

            ps.setString(
                    1,
                    entity.getOperation()
            );

            ps.setDouble(
                    2,
                    entity.getValue1()
            );

            ps.setDouble(
                    3,
                    entity.getValue2()
            );

            ps.setBoolean(
                    4,
                    entity.getResult()
            );

            // STEP 4
            // execute query

            ps.executeUpdate();

            System.out.println(
                    "DATA SAVED INTO DATABASE"
            );

            // STEP 5
            // close resources

            ps.close();

            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}