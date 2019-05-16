package sqlexecutors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlExecutor {
    public int executeSql(String sqlStatement){
        System.out.println(sqlStatement);
        String jdbcString = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/20190514_hw" + jdbcString, "tikrasvartotojas", "kietasslaptazodis")) {
            try (Statement statement = conn.createStatement()) {
                //String sqlStatement = "INSERT INTO suplier(name, description) values ('Valdemar_Co','super patikimas suplieris')";
                int rows = statement.executeUpdate(sqlStatement);
//                conn.close();
                return rows;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
