import java.sql.*;

public class Show {
    private static final String url = "jdbc:mysql://localhost:3306/mydb";    //write the database name at last
    private static final String username = "root";
    private static final String password = "root@123";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String query = "select * from students";
           ResultSet resultset =  statement.executeQuery(query);     //resultset interface will hold the table we get
           while(resultset.next()){
               int id = resultset.getInt("id");         //storing table id into id
               String name = resultset.getString("name");
               int age = resultset.getInt("age");
               Double marks = resultset.getDouble("marks");
               System.out.println("ID: " + id);
               System.out.println("NAME: " + name);
               System.out.println("AGE: " + age);
               System.out.println("MARKS: " + marks);
           }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}