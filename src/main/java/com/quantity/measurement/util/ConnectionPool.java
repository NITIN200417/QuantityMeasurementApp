package com.quantity.measurement.util;

import java.sql.Connection;
import java.sql.DriverManager;

// connection pool class

public class ConnectionPool {

    // get database connection

    public Connection getConnection()
            throws Exception {

        System.out.println(
                "CONNECTION CREATED"
        );

        return DriverManager.getConnection(

                DatabaseConfig.URL,

                DatabaseConfig.USERNAME,

                DatabaseConfig.PASSWORD
        );
    }
}
