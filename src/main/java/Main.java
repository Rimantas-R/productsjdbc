import java.sql.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");

       prepInsSql("Hellowin and Co","Description");

    }

    private static void prepInsSql(String name, String desc){
        String prepStatement = "INSERT INTO suplier(name, description) values (?,?)";

        try (
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/20190514_hw", "tikrasvartotojas", "kietasslaptazodis");
            PreparedStatement statement = conn.prepareStatement(prepStatement);){

            statement.setString(1,name);
            statement.setString(2,desc);
            int rows = statement.executeUpdate();
            System.out.println("Succesfuly inserted " + rows + " rows");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void insertSQL() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/20190514_hw", "tikrasvartotojas", "kietasslaptazodis");
            Statement statement = conn.createStatement();
            String querryStatemant = "INSERT INTO suplier(name, description) values ('Valdemar_Co','super patikimas suplieris')";
            statement.executeUpdate(querryStatemant);
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
