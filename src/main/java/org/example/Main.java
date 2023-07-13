package org.example;
import org.apache.log4j.BasicConfigurator;
import org.example.task1.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class Main {
    private static final Logger loggerMain = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {

        // configure logger
        BasicConfigurator.configure();
        // get database connection
        Connection  conn = Database.getInstance().getConnection();
        // get list of database tables
        try(Statement stmt = conn.createStatement()) {
            loggerMain.info("Execute SQL statement");
            ResultSet rs = stmt.executeQuery("SELECT table_name FROM INFORMATION_SCHEMA.TABLES");
            // output result to screen
            int i=0;
            String getStr;
            while(rs.next()){
                if(i==0) loggerMain.info("Table Number | Table Name ");
                getStr=rs.getString("table_name");
                loggerMain.info(" {}      |             {}",i ,getStr);
                i++;
            }
        } catch(SQLException e1){
            loggerMain.error("Error retrieving data from database. Check SQL expression.");

        }
        loggerMain.info("Application executed successfully");

    }
}