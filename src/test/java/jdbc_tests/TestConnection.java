package jdbc_tests;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws SQLException {
        String dbUrl="jdbc:oracle:thin:@54.84.231.229:1521:XE";
        String dbUsername="hr";
        String dbPassword="hr";

        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from employees");

        //colese in order
        resultSet.close();
        statement.close();
        connection.close();

    }
}
