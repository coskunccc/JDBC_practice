package jdbc_tests;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        String dbUrl="jdbc:oracle:thin:@54.211.112.148:1521:XE";
        String dbUsername="hr";
        String dbPassword="hr";

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from regions");

        // read information from "regions",
        // go from table head to first row by next()
        resultSet.next();

        //get inf either by column name or column index
        System.out.println(resultSet.getString("region_name"));
        System.out.println(resultSet.getString(2));
        System.out.println(resultSet.getString(1));

        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        resultSet.next();
        System.out.println(resultSet.getInt("region_id")+" - "+resultSet.getString("region_name"));
        resultSet.next();
        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        }


        //close objects in order
        resultSet.close();
        statement.close();
        connection.close();

    }
}
