import java.sql.*;
import java.util.Scanner;

public class BP {
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

//            Statement st = con.createStatement();
          Scanner sc = new Scanner(System.in);
//            while(true){
//                System.out.print("Enter name: ");
//                String name = sc.next();
//                System.out.print("Enter age: ");
//                int age = sc.nextInt();
//                System.out.print("Enter marks: ");
//                double marks = sc.nextDouble();
//                System.out.print("Enter more data(Y/N): ");
//                String choice = sc.next();
//
//                String query = String.format("INSERT INTO students(name, age, marks) VALUES('%s', %d, %f)", name, age, marks);
//                st.addBatch(query);
//
//                if(choice.toUpperCase().equals("N")){
//                    break;
//                }
//            }
//
//                int[] arr = st.executeBatch();    //values will be in array (batch of 5)  1 1 0 0 1
//                for(int i = 0; i < arr.length; i++){
//                    if(arr[i] == 0){
//                        System.out.println("Query: " + i + " not executed Successfully!");
//                    }
//                }





                String query1 = "INSERT INTO students(name, age, marks) VALUES(?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query1);
            while(true){
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Enter age: ");
                int age = sc.nextInt();
                System.out.print("Enter marks: ");
                double marks = sc.nextDouble();
                System.out.print("Enter more data(Y/N): ");
                String choice = sc.next();
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setDouble(3, marks);

                ps.addBatch();          //dont pass query here

                if(choice.toUpperCase().equals("N")){
                    break;
                }
            }
            int[] arr1 = ps.executeBatch();
            for(int i = 0; i < arr1.length; i++){
                if(arr1[i] == 0){
                    System.out.println("Query: " + i + " not executed Successfully!");
                }
            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
