import filereaders.ReadFile;
import sqlexecutors.SqlExecutor;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");

//        setting time zone
//        int timeZoneSuccess = new SqlExecutor().executeSql("SET time_zone='+00:00';");

        String createTable = new ReadFile().readFileIntoSqlString("in.txt");
        int successCreateTable = new SqlExecutor().executeSql(createTable);
        if (successCreateTable >= 0){
            System.out.println("Successfuly created table");
        } else {
            System.out.println("Error creating table");
        }

        String insertIntoTable = new ReadFile().readFileIntoSqlString("insert.txt");
        int successInsert = new SqlExecutor().executeSql(insertIntoTable);
        if(successInsert >=0){
            System.out.println("Successfuly inserted rows: " + successInsert);
        } else {
            System.out.println("Error inserting");
        }
//        new ReadFile().readFileIntoSqlString("insert.txt");

       //prepInsSql("Hellowin and Co","Description");

    }

    private static void prepInsSql(String name, String desc){
        String prepStatement = "INSERT INTO suplier(name, description) values (?,?)";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/20190514_hw", "tikrasvartotojas", "kietasslaptazodis")) {
            try (PreparedStatement statement = conn.prepareStatement(prepStatement)) {

                statement.setString(1, name);
                statement.setString(2, desc);
                int rows = statement.executeUpdate();
                System.out.println("Succesfuly inserted " + rows + " rows");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertSQL() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/20190514_hw", "tikrasvartotojas", "kietasslaptazodis")) {
            try (Statement statement = conn.createStatement()) {
                String querryStatemant = "INSERT INTO suplier(name, description) values ('Valdemar_Co','super patikimas suplieris')";
                statement.executeUpdate(querryStatemant);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
