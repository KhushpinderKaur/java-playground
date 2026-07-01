import java.sql.*;
import java.util.Scanner;

public class TransHandling {

    private static final String url = "jdbc:mysql://localhost:3306/mydb2";
    private static final String username = "root";
    private static final String password = "root@123";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);

            // Start Transaction
            con.setAutoCommit(false);

            String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE ac_no = ?";
            String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE ac_no = ?";

            PreparedStatement dps = con.prepareStatement(debitQuery);
            PreparedStatement cps = con.prepareStatement(creditQuery);

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Sender Account Number: ");
            int sender = sc.nextInt();

            System.out.print("Enter Receiver Account Number: ");
            int receiver = sc.nextInt();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            if (isSufficient(con, sender, amount)) {

                // Debit Sender
                dps.setDouble(1, amount);
                dps.setInt(2, sender);

                // Credit Receiver
                cps.setDouble(1, amount);
                cps.setInt(2, receiver);

                int debitRows = dps.executeUpdate();
                int creditRows = cps.executeUpdate();

                if (debitRows > 0 && creditRows > 0) {
                    con.commit();
                    System.out.println("Transaction Successful");
                } else {
                    con.rollback();
                    System.out.println("Transaction Failed");
                }

            } else {
                con.rollback();
                System.out.println("Insufficient Balance");
            }

            dps.close();
            cps.close();
            con.close();
            sc.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static boolean isSufficient(Connection con, int ac_no, double amount) {

        String query = "SELECT balance FROM accounts WHERE ac_no = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ac_no);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");

                rs.close();
                ps.close();

                return currentBalance >= amount;
            }

            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}