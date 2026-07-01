import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
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

            String query = "Delete FROM students WHERE ID = 3";

            int rowsAffected = st.executeUpdate(query);
            if(rowsAffected > 0){
                System.out.println("Deleted Successfully");
            }else{
                System.out.println("Not Deleted!");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
