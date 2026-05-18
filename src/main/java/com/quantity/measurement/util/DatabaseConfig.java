package com.quantity.measurement.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConfig {

    public static String URL;

    public static String USERNAME;

    public static String PASSWORD;

    static {

        try {

            // load properties

            Properties prop =
                    new Properties();

            InputStream input =
                    DatabaseConfig.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    "application.properties"
                            );

            prop.load(input);

            URL =
                    prop.getProperty(
                            "db.url"
                    );

            USERNAME =
                    prop.getProperty(
                            "db.username"
                    );

            PASSWORD =
                    prop.getProperty(
                            "db.password"
                    );

            System.out.println(
                    "DATABASE CONFIG LOADED"
            );

            // execute schema.sql

            runSchema();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // create tables

    private static void runSchema()
            throws Exception {

        InputStream schemaInput =
                DatabaseConfig.class
                        .getClassLoader()
                        .getResourceAsStream(
                                "db/schema.sql"
                        );

        String sql =
                new String(
                        schemaInput.readAllBytes()
                );

        Connection con =
                DriverManager.getConnection(
                        URL,
                        USERNAME,
                        PASSWORD
                );

        Statement stmt =
                con.createStatement();

        stmt.execute(sql);

        stmt.close();

        con.close();

        System.out.println(
                "TABLE CREATED"
        );
    }
}