import java.sql.*;

public class Update {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";    //write the database name at last
    private static final String username = "root";
    private static final String password = "root@123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();

            String query = String.format("UPDATE students SET marks = %f where id = %d", 89.5, 4);
            System.out.println(query);
            int rowsAffected = st.executeUpdate(query);
            if(rowsAffected > 0){
                System.out.println("Data Updated Successfully");
            }else{
                System.out.println("Data not Updated!");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
