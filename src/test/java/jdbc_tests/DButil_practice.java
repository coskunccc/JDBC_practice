package jdbc_tests;

import utilities.DButils;

public class DButil_practice {
    public void test1(){
        DButils.createConnection();
        String query="select first_name,last_name,salary,job_id" +
                "from employees where rownum<6";

    }
}
