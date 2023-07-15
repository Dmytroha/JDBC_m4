package org.example.task4;


import org.example.entities.LongestProject;
import org.example.fileservicies.SQLFileReader;
import org.example.task1.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DatabaseQueryService {
    //private static final Logger loggerDQS = LoggerFactory.getLogger(DatabaseQueryService.class);
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
        //BasicConfigurator.configure();
        //loggerDQS.info("Constructor is working!");

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
    public void find_max_projects_client(){

    }
    public void find_max_salary_worker(){

    }
    public void find_youngest_eldest_workers(){

    }
    public void print_project_prices(){

    }


}
