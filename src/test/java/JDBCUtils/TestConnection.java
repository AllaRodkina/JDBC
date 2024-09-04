package JDBCUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestConnection {

    public static void main(String[] args) throws SQLException {
        ResultSet resultSet
                = JDBCConnection.executeQuery("select * from employees");
//        ResultSet object is an iterator that looks at each row at a time from  returned data.
//        To get the first row we can use:
        resultSet.next();
//        Next method returns the value from next row, if there is a next row.
//        If there is no next row, next method returns `false`; but if there is a next row, it returns
//        `true` and result set object will be looking at the next row.

//        To get the columns and the values we can use `ResultSetMetaData` object.
        ResultSetMetaData md = resultSet.getMetaData();
//        Number of columns from the row
        System.out.println(md.getColumnCount());
//      Get the name of the specific column; for example from column 5
        System.out.println(md.getColumnName(5));

//        Get the value of the column
//        To do this we should use `ResultSet` object.
//        Getting object from 7th column
        System.out.println(resultSet.getObject(7));
//      Store the column names and corresponding values from 1 st row
//        in a most suitable data type. Then display the data.
       Map<String,Object> firstRow = new HashMap();
//       We'll store column names as keys and values of columns as values in the map.
        // How could I get all the columns and values from the first row?
        for (int i = 1; i <= md.getColumnCount(); i++) {
            String columnName = md.getColumnName(i);
            Object columnValue= resultSet.getObject(i);
            firstRow.put(columnName,columnValue); }

//      Display the data from 1st row
        System.out.println("First row data:");
        System.out.println(firstRow);
        System.out.println("---    END    ---");


        System.out.println(getFullResult("Select * from employees where salary > 5000"));

    }
//      Create a java method to get all data(from all rows) from given query and store in a map object.
//      Then call the method in a main method to make sure it works.


    public static List<Map<String,Object>> getFullResult(String query) throws SQLException {
        List<Map<String,Object>> allRows = new ArrayList();
        ResultSet resultSet = JDBCConnection.executeQuery(query);
        while (resultSet.next())
        {
            Map<String,Object> row = new HashMap();
            // How many column is in this row?
            ResultSetMetaData mData = resultSet.getMetaData();
            for (int i = 1; i <= mData.getColumnCount(); i++) {
                String columnName = mData.getColumnName(i);
                Object columnValue= resultSet.getObject(i);
                row.put(columnName,columnValue);
            }
            allRows.add(row);
        }


        return allRows;
    }









}
