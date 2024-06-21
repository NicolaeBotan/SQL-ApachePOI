package JDBC;

import org.junit.Test;


import java.sql.*;

public class JdbcIntro {

    @Test
    public void connectionTest() throws SQLException {

        //HERE WE ARE MAKING THE CONNECTION WITH THIS SPECIFIC DATA BASE
        // THAT WE PROVIDED THE URL FOR
        //ALSO WE ADDED THE SQL EXCEPTION WHICH WAS REQUIRED BY THE getConnection() method

        // 3 INTERFACES WE USE TO CONNECT AND QUERY DATA BASE:
        //          1- CONNECTION INTERFACE
        //          2- STATEMENT INTERFACE
        //          3- RESULT SET INTERFACE

        //1- CONNECTION INTERFACE
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@codefish-database-learn.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student", "codefish385");

        //2- STATEMENT INTERFACE
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        //here we made the connection with the database and set that we can read and scroll back and forth
        // through the database

        //3- RESULT SET INTERFACE
        ResultSet result = statement.executeQuery("select * from employees");
        //here we are passing our Queries and Executing

        result.next(); //means every next row. Returns true if there is a next row
        System.out.println(result.getString(1));
        //will print out the result from column 1 , that is the employee_id.


        //here to get all the data from each column and move to the next row we will loop and iterate

        while (result.next()) {
            System.out.println(result.getString(1));
            System.out.println(result.getString(2));
            System.out.println(result.getString(3));
            //THIS WILL PRINT OUT THE EMPLOYEE ID, FIRST NAME AND LAST NAME, THAN MOVE TO THE NEXT
            // ROW AND GET THE SAME INFO FOR THE NEXT EMPLOYEE.
            // THIS WHILE LOOP ITERATE UNTIL THE result.next() will be false
        }
    }

    @Test
    public void queryTest() throws SQLException {
        //this getConnection() method will take 3 parameters, url, userName abd password.
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@codefish-database-learn.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student", "codefish385");

        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //here we will getMetaData about our result, meta data is data about data.
        ResultSetMetaData metaData = resultSet.getMetaData();//alt+Enter to generate data type for the variable

        // result.Next() -- WILL ITERATE THROUGH EVERY ROW ONE BY ONE every time you call the method


        while (resultSet.next()) { //iterating over rows in the table

            System.out.println("|"); //to make the print nice with pipe at the beginning of each row

            for (int i = 1; i <= metaData.getColumnCount(); i++) { //iterating over each column in every row
                System.out.print(resultSet.getObject(i) + " |");//while iterating it will print every row
            }
            System.out.println();
            // we print this just for switching for a new line after each for loop (every row)
        }

    }
}
