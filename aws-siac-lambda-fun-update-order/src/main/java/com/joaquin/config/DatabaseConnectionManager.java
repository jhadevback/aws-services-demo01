package com.joaquin.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static Connection connection = null;

    private static Connection createConnection() {

        Properties properties = new Properties();
        try (InputStream inputStream = DatabaseConnectionManager.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            properties.load(inputStream);

            String jdbcUrl = properties.getProperty("db.url");
            String username = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
