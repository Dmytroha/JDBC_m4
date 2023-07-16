package org.example.task4;


import org.example.entities.*;
import org.example.fileservicies.SQLFileReader;
import org.example.task1.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

public class DatabaseQueryService {
    private Connection conn;
    private List<String> sqlList = new ArrayList<>();
    private Map<String,String> sqlMap = new HashMap<>();
    private Set<String> queryFilesSet = new LinkedHashSet<>(Arrays.asList(
            "find_longest_project.sql",
            "find_max_projects_client.sql",
            "find_max_salary_worker.sql",
            "find_youngest_eldest_workers.sql",
            "print_project_prices.sql"
    ));


    public DatabaseQueryService() {

        conn = Database.getInstance().getConnection();

        SQLFileReader sqlFileReader =new SQLFileReader();
        // extract first sql statement from sql file (to read all sql statements demands more time,
        // but i have deadline broken and it is no point )
        queryFilesSet.stream().forEach(
                s->{
                    String sqlStr;
                    sqlFileReader.setSqlFileName(s);
                    sqlStr=sqlFileReader.getSqlStatements()[0];
                    sqlMap.put(s,sqlStr);
                    sqlList.add(sqlStr);
                }
        );

    }

    public List<LongestProject> findLongestProject(){

        List<LongestProject> longestProjects = new ArrayList<>();

        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlMap.get("find_longest_project.sql"));
            while(rs.next()){
                long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                int monthCount = rs.getInt( "MONTH_COUNT");
                longestProjects.add(new LongestProject(id,name,monthCount));
            }
            return longestProjects;
        }catch( SQLException e1){
            e1.printStackTrace();
        }
        return longestProjects;
    }
    public  List<MaxProjectCountClient> findMaxProjectsClient(){
        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();

        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlMap.get("find_max_projects_client.sql"));
            while(rs.next()){
                String name = rs.getString("NAME");
                int projectCount = rs.getInt( "PROJECT_COUNT");
                maxProjectCountClients.add(new MaxProjectCountClient(name, projectCount));
            }
            return maxProjectCountClients;
        }catch( SQLException e1){
            e1.printStackTrace();
        }
        return maxProjectCountClients;

    }
    public List<MaxSalaryWorker> findMaxSalaryWorker(){
        List<MaxSalaryWorker> maxSalaryWorkers = new ArrayList<>();

        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlMap.get("find_max_salary_worker.sql"));
            while(rs.next()){
                String name = rs.getString("NAME");
                Long salary = rs.getLong( "SALARY");
                maxSalaryWorkers.add(new MaxSalaryWorker(name, salary));
            }
            return maxSalaryWorkers;
        }catch( SQLException e1){
            e1.printStackTrace();
        }
        return maxSalaryWorkers;

    }
    public List<YoungestEldestWorkers> findYoungestEldestWorkers(){

        List<YoungestEldestWorkers> youngestEldestWorkers =new ArrayList<>();
        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlMap.get("find_youngest_eldest_workers.sql"));
            while(rs.next()){
                String type=rs.getString("TYPE");
                String name = rs.getString("NAME");
                LocalDate birthday = rs.getDate( "BIRTHDAY").toLocalDate();
                youngestEldestWorkers.add(new YoungestEldestWorkers(type, name, birthday));
            }
            return youngestEldestWorkers;
        }catch( SQLException e1){
            e1.printStackTrace();
        }
        return youngestEldestWorkers;

    }
    public List<PrintProjectPrices> printProjectPrices(){
        List<PrintProjectPrices> printProjectPrices =new ArrayList<>();
        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlMap.get("print_project_prices.sql"));
            while(rs.next()){
                String name = rs.getString("NAME");
                Long price = rs.getLong( "PRICE");
                printProjectPrices.add(new PrintProjectPrices(name, price));
            }
            return printProjectPrices;
        }catch( SQLException e1){
            e1.printStackTrace();
        }
        return printProjectPrices;

    }


}
