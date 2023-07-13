package org.example.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * This is Singleton class Database which establish connection to the
 * database specified by DB_URL and provide user with Connection instance.
 *  Example: Connection  conn = Database.getInstance().getConnection();
 *
 */

public class Database {

    private static final Logger loggerDatabase = LoggerFactory.getLogger(Database.class);
    private static final String DB_URL = "jdbc:h2:~/test";
    private static Database databaseTest = new Database();
    private Connection connection;



    private Database(){
          try {
                loggerDatabase.info("establishing connection");
                connection = DriverManager.getConnection(DB_URL, "sa", "");
                loggerDatabase.info("Successfully connected to database {}", DB_URL);
            } catch (SQLException e1) {
                loggerDatabase.error("Database connection failed.");
                e1.printStackTrace();
            }

    }
    public static Database getInstance(){
        if (Objects.isNull(databaseTest)) {
            databaseTest = new Database();
        }
        return databaseTest;

    }

    public Connection getConnection(){
        if (Objects.isNull(connection)) {
            try {
                connection = DriverManager.getConnection(DB_URL, "sa", "");
                loggerDatabase.info("Connected to database {}", DB_URL);
                return connection;
            } catch (SQLException e1) {
                loggerDatabase.error("Database connection failed.");
                e1.printStackTrace();
            }
        }
        return connection;
    }



}
