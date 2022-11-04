package jdbc_tests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapEx {

    String dbUrl = "jdbc:oracle:thin:@54.211.112.148:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id " +
                "from employees where rownum<6");

        DatabaseMetaData dbMetadata=connection.getMetaData();
        ResultSetMetaData rsmd= resultSet.getMetaData();
        resultSet.next();

        List<Map<String,Object>> queryData=new ArrayList<>();

        int columnCount=rsmd.getColumnCount();

        while (resultSet.next()){
            Map<String,Object> row=new HashMap<>();

            for(int i=1; i<=columnCount; i++){
                row.put(rsmd.getColumnName(i),resultSet.getObject(i));
            }

            queryData.add(row);
        }

        for (Map<String,Object> row: queryData){
            System.out.println(row.toString());
        }


        resultSet.close();
        statement.close();
        connection.close();
    }

}
