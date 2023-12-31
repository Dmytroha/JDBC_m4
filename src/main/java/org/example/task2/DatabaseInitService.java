package org.example.task2;

import org.apache.log4j.BasicConfigurator;
import org.example.fileservicies.SQLFileReader;
import org.example.task1.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;


public class DatabaseInitService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitService.class);
    private  Connection conn;
   private  String[] sqlStatementList;

    DatabaseInitService(){
        conn = Database.getInstance().getConnection();
        sqlStatementList=(new SQLFileReader("init_db.sql")).getSqlStatements();
    }


    public static void main(String[] args) throws SQLException {
        // configure logger
        BasicConfigurator.configure();
        DatabaseInitService dbInitService = new DatabaseInitService();
        dbInitService.executeInit_db_SQL();
    }

    public void executeInit_db_SQL() throws SQLException {
        try(Statement stmt = conn.createStatement()) {
            for(String sqlString: sqlStatementList) {
                stmt.execute(sqlString);
                 logger.info("SQL statement: {} \n executed successfully",sqlString );
            }

        }catch(SQLException e1){
            e1.printStackTrace();
        }finally {
            logger.info("Close connection");
            conn.close();
        }


    }

}
