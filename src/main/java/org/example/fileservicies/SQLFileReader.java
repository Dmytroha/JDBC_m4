package org.example.fileservicies;

import org.apache.log4j.BasicConfigurator;
import org.example.task2.DatabaseInitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SQLFileReader {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitService.class);

    public String[] getSqlStatements() {
        return sqlStatements;
    }

    private String[] sqlStatements;
    private String sqlFileName;
    private final String fullPathToSQLFile;
    public SQLFileReader(String sqlFileName){
        BasicConfigurator.configure();
        this.sqlFileName=sqlFileName;
        this.fullPathToSQLFile="src\\main\\resources\\sql\\"+this.sqlFileName;
        readSQLFromFile();
    }

    private void readSQLFromFile(){
        StringBuilder stringFromFile = new StringBuilder();

        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(fullPathToSQLFile))){

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringFromFile.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // split into separate expressions
        sqlStatements=stringFromFile.toString().split(";");
        // Output result to screen. Use stream API because I love streams. Firstly, this is nice.
        Arrays.stream(sqlStatements).forEach(logger::info);
    }

}
