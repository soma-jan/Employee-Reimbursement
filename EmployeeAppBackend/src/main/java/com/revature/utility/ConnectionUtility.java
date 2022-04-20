package com.revature.utility;


import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtility {
    //TODO 2-Create connection utility class to return connection obj to use in DAO
    public static Connection getConnection() throws SQLException {
        //TODO 3-Grab connection url,password,dbname
        String url = System.getenv("db_url");
        String username = System.getenv("db_username");
        String password = System.getenv("db_password");

        // TODO 4: Register the Postgres driver
        DriverManager.registerDriver(new Driver());

        // TODO 5: Get the Connection object from the DriverManager
        Connection connection = DriverManager.getConnection(url, username, password);

        return connection;
    }

}

