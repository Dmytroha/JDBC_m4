package org.example.task3;

import org.apache.log4j.BasicConfigurator;
import org.example.task1.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {
    private static final Logger logger = LoggerFactory.getLogger(DatabasePopulateService.class);
    public static void main(String[] args) {
        BasicConfigurator.configure();
        logger.info("this is placeholder method for the future");
    }
}
