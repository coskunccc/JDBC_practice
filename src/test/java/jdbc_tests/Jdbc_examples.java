package jdbc_tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class Jdbc_examples {

    String dbUrl = "jdbc:oracle:thin:@54.211.112.148:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from departments");

        // resultSet.next();
        // get rows like "10 - Administration - 200 - 1700"
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString(1)
//                    + " - " + resultSet.getString(2)
//                    + " - " + resultSet.getString(3)
//                    + " - " + resultSet.getString(4));
//        }

        resultSet = statement.executeQuery("select * from regions");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1)
                    + " - " + resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }

    @DisplayName("Scrool freely")
    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

        resultSet.last();
        System.out.println(resultSet.getRow()); //getRow() gives the number of current row.

        resultSet.previous();
        System.out.println(resultSet.getRow());

        // to move beforefirst
        resultSet.beforeFirst();

        resultSet.close();
        statement.close();
        connection.close();

    }
    @Test
    public void test3() throws SQLException{
        Connection connection=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=statement.executeQuery("select * from departments");

        DatabaseMetaData dbMetadata=connection.getMetaData();

//        System.out.println(dbMetadata.getUserName());
//        System.out.println(dbMetadata.getDatabaseProductName());
//        System.out.println(dbMetadata.getDatabaseProductVersion());
//        System.out.println(dbMetadata.getDriverName());
//        System.out.println(dbMetadata.getDriverVersion());

        ResultSetMetaData rsMetadata=resultSet.getMetaData();
        System.out.println(rsMetadata.getColumnCount());
        System.out.println(rsMetadata.getTableName(3));
        System.out.println(rsMetadata.getColumnName(1));

        resultSet.close();
        statement.close();
        connection.close();

    }

}
