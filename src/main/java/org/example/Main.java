package org.example;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;



public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final String PASSWORD_STRING = "tr9g!*Hps";
    public static void main(String[] args) {
        BasicConfigurator.configure();
        String dbUrl = "jdbc:h2:~/test";
        //,"sa",PASSWORD_STRING
        try(Connection conn = DriverManager.getConnection(dbUrl,"sa","");
            Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT table_name FROM INFORMATION_SCHEMA.TABLES" );
            int i=0;
            while(rs.next()){
                if(i==0)logger.info("Table Number | Table Name ");
                logger.info("           {} |             {}",i ,rs.getString("table_name"));
                i++;
            }

      } catch (SQLException e) {
        e.printStackTrace();
    }

        logger.info("Hello JDBC world!");

    }
}