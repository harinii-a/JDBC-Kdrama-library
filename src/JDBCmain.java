import java.sql.*;
import java.util.Scanner;

public class JDBCmain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n~~~~~~~ Welcome to kdrama library!! ~~~~~~~\n");
            System.out.println("1.View K-Drama");
            System.out.println("2.Add K-Drama");
            System.out.println("3.Search K-Drama by Genre");
            System.out.println("4.Update status of K-Drama");
            System.out.println("5.Delete K-Drama");
            System.out.println("6.Exit\n");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            System.out.println();
            sc.nextLine();

            switch (choice) {
                    case 1:
                        viewDrama();
                        break;
                   case 2:
                       addDrama();
                       break;
                  case 3:
                      searchDrama();
                      break;
                  case 4:
                       update();
                     break;
                   case 5:
                     deleteDrama();
                      break;
                   case 6:
                       System.out.println("Goodbye!");
                       return;
            }

        }
    }

    static String URL = "jdbc:mysql://localhost:3306/kdramalist";
    static String USER = "root";
    static String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection connect() throws Exception{
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

    public static void viewDrama() {
        try(Connection con = connect()){
            String sql= "select * from kdrama";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            System.out.println("+----------------------+-------------+-----------+");
            System.out.printf("| %-20s | %-11s | %-9s |\n","Title", "Genre", "Status");
            System.out.println("+----------------------+-------------+-----------+");
            while(rs.next()){
                System.out.printf("| %-20s | %-11s | %-9s |\n",rs.getString(1),rs.getString(2),rs.getString(3));
            }
            System.out.println("+----------------------+-------------+-----------+");
        }
        catch (Exception e){
            System.out.println("Error Viewing Drama: "+e);
        }
    }

    public static void addDrama() {
        try(Connection con = connect()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("----Enter Details----");
            System.out.print("Enter K-Drama Title: ");
            String title = sc.nextLine();
            System.out.print("Enter K-Drama Genre: ");
            String genre = sc.nextLine();
            System.out.print("Enter K-Drama Status: ");
            String status = sc.nextLine();


            String sql = "insert into kdrama values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,title);
            pst.setString(2,genre);
            pst.setString(3,status);
            pst.executeUpdate();
            System.out.println("Successfully added K-Drama!!");
        }
        catch (Exception e){
            System.out.println("Error Adding K-Drama: "+e);
        }
    }

    public static void searchDrama() {
        try (Connection con = connect()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("----Search Drama based on Genre----");
            String sql = "SELECT DISTINCT genre FROM kdrama";
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery(sql);
            while (rs1.next()) {
                System.out.println(rs1.getString(1));
            }

            System.out.println("Enter Genre: ");
            System.out.println();
            String genre = sc.nextLine();
            String sql2 = "select * from kdrama where genre = ?";
            PreparedStatement pst = con.prepareStatement(sql2);
            pst.setString(1, genre);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                System.out.println("Genre not found!");
            } else {
                System.out.println("+----------------------+-------------+-----------+");
                System.out.printf("| %-20s | %-11s | %-9s |\n","Title", "Genre", "Status");
                System.out.println("+----------------------+-------------+-----------+");
                while (rs.next()) {
                    System.out.printf("| %-20s | %-11s | %-9s |\n", rs.getString(1), rs.getString(2), rs.getString(3));

                }
                System.out.println("+----------------------+-------------+-----------+");
            }
        }
        catch (Exception e){
            System.out.println("Error Searching Drama: "+e);
        }
    }

    public static void update() {
        try (Connection con = connect()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("----Update K-Drama based on Name----");
            System.out.print("Enter K-Drama Title: ");
            String title = sc.nextLine();
            System.out.print("Completed/Ongoing ? : " );
            String res = sc.nextLine();
            String sql = "UPDATE kdrama SET status = ? WHERE title = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,res);
            pst.setString(2,title);
            pst.executeUpdate();
            System.out.println("Successfully updated K-Drama!");
        }
        catch (Exception e){
            System.out.println("Error Updating K-Drama: "+e);
        }
    }

    public static void deleteDrama(){
        try(Connection con = connect()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("----Delete K-Drama based on Title----");
            System.out.print("Enter K-Drama Title: ");
            String title = sc.nextLine();
            String sql = "DELETE FROM kdrama WHERE title = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,title);
            //pst.executeUpdate();
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully deleted K-Drama!");
            } else {
                System.out.println("No K-Drama found with that title.");
            }
        }
        catch (Exception e){
            System.out.println("Error Deleting K-Drama: "+e);
        }

    }

}
