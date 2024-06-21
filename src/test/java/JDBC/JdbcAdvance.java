package JDBC;

import org.junit.Assert;
import org.junit.Test;
import utils.JdbcUtils;

import java.sql.*;
import java.util.*;

public class JdbcAdvance {

    @Test
    public void test1() throws SQLException {

        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@codefish-database-learn.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student", "codefish385");

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from employees");
        //select everything from employees

        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Map<String, Object>> resultData = new ArrayList<>();

        while (resultSet.next()) { //every next row
            Map<String, Object> rowMap = new HashMap<>();
            //we define this rowMap to be able to store each column name as key and value

            for (int i = 1; i <= metaData.getColumnCount(); i++) { //every next column
                rowMap.put(metaData.getColumnName(i), resultSet.getObject(i));
                //meteData.getColumnName will set the key name of each column,
                //as a value our resultSet.getObject will give us the specific value for each specific column
            }
            resultData.add(rowMap);
        }
        System.out.println(resultData.get(0).get("LAST_NAME"));
        //this will give us the map with index 0 from our 'resultDta' List, and this will be all
        // the info about the first employee,
        // .get("LAST_NAME") will give only the last name of the first employee

        for (Map<String, Object> eachEmployee : resultData) {
            String firstName = (String) eachEmployee.get("FIRST_NAME");
            //because we defined that the value is Object, we can not just store as a String, so we will
            // cast the Object data type in a String data type

            if (firstName.equals("TJ")) {
                int salary = Integer.parseInt(eachEmployee.get("SALARY").toString());
                //we need to parse the Integer int data type
                Assert.assertEquals(23000, salary);
            }
        }
    }

    @Test
    public void test2() {
        ResultSet resultSet = JdbcUtils.queryDataBase("select * from employees");
        
    }
}
