import java.sql.*;

public class PrepStat {
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


                //INSERTION
                String query1 = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";    //compiled here already
//              Statement st = con.createStatement();
                PreparedStatement ps1 = con.prepareStatement(query1);
                ps1.setString(1,"Ankita");    //first placeholder
                ps1.setInt(2,26);
                ps1.setDouble(3,85.6);
                int rowsAffected1 = ps1.executeUpdate();       //no query pass here, execute here only
                if(rowsAffected1 > 0){
                    System.out.println("Data Inserted Successfully");
                }else{
                    System.out.println("Data not Inserted!");
                }



                //RETRIVAL
                String query2 = "SELECT marks FROM students WHERE id = ?";
                PreparedStatement ps2 = con.prepareStatement(query2);
                ps2.setInt(1, 5);
                ResultSet rs = ps2.executeQuery();
                if(rs.next()){
                    double mark = rs.getDouble("marks");
                    System.out.println("Marks: " + mark);
                }



                //UPDATE
                String query3 = "UPDATE students SET marks = ? WHERE id = ?";
                PreparedStatement ps3 = con.prepareStatement(query3);
                ps3.setDouble(1,70.5);
                ps3.setInt(2,5);
                int rowsAffected2 = ps3.executeUpdate();
                if(rowsAffected2 > 0){
                    System.out.println("Data Updated successfully");
                }else{
                    System.out.println("Data not Updated");
                }




            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

