package org.example.fileservicies;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SQLFileReader {
    private static final Logger logger = LoggerFactory.getLogger(SQLFileReader.class);
    private String sqlFileName;
    private  String fullPathToSQLFile;
    private static final String PathToSQLFile="src\\main\\resources\\sql\\";

    public String[] getSqlStatements() {
        return sqlStatements;
    }


    public SQLFileReader() {

    }

    private String[] sqlStatements;

    public void setSqlFileName(String sqlFileName) {
        this.sqlFileName = sqlFileName;
        this.fullPathToSQLFile=PathToSQLFile+this.sqlFileName;
        readSQLFromFile();
    }


    public SQLFileReader(String sqlFileName){
        BasicConfigurator.configure();
        this.sqlFileName=sqlFileName;
        this.fullPathToSQLFile=PathToSQLFile+this.sqlFileName;
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
        // split into separate SQL expressions
        sqlStatements=stringFromFile.toString().split(";");
        // Output result to screen. Use stream API because I love streams. Firstly, this is nice.
        Arrays.stream(sqlStatements).forEach(logger::info);
    }

}
