package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    private static Statement establishConnection() {

        try {
            connection = DriverManager.getConnection(
                    getProperties("connection_string"),
                    getProperties("userName"),
                    getProperties("password"));
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException ex) {
            throw new RuntimeException("Could Not Connect to DataBase");
        }
        return statement;
    }

    public static ResultSet queryDataBase(String query) {
        statement = establishConnection();
        try {
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException ex) {
            throw new RuntimeException("Failed to execute query");
        }finally {
            closeConnection(); //we do this to close the connection with our database.
        }
    }

    private static String getProperties(String key) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/test/resources/database.properties")));
        } catch (IOException ex) {
            throw new RuntimeException("Could Not Find properties file");
        }
        return properties.getProperty(key);
    }

    private static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
