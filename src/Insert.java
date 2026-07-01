import java.sql.*;

public class Insert {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
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

            String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %d, %f)" , "Rahul", 23, 74.5);
            int rowsAffected = st.executeUpdate(query);
            if(rowsAffected > 0){
                System.out.println("Data Inserted Successfully");
            }else{
                System.out.println("Data not Inserted!");
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
