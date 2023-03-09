import java.sql.*;
import java.util.Scanner;
import java.sql.JDBCType;

public class movieDatabase {


    static Connection cn = null;
    static PreparedStatement pstat = null;
    static ResultSet rs = null;


    static final String DBAddress = "jdbc:mysql://localhost:3306/sys?useTimezone=true&serverTimezone=GMT";
    static final String user = "root";
    static final String pass = "glassugrör21";
    
    public static void main(String[] args) {
        System.out.println("Add a new movie to the database");

        try{
            Scanner scan = new Scanner(System.in);

            System.out.print("Movie Title: ");
            String moviesTitle = scan.nextLine();
            System.out.print("Year Released: ");
            int moviesYear = scan.nextInt();
            scan.nextLine(); 
            System.out.print("Directed By: ");
            String moviesDirector = scan.nextLine();
            System.out.print("Genre Id: ");
            int genresId= scan.nextInt();
            scan.nextLine(); 

            cn = DriverManager.getConnection(DBAddress, user, pass);

            pstat = cn.prepareStatement("CALL addNewMovie(?, ?, ?, ?)");

            pstat.setString(1, moviesTitle);
            pstat.setInt(2, moviesYear);
            pstat.setString(3, moviesDirector);
            pstat.setInt(4, genresId);

            pstat.executeUpdate();

            cn.close();
            System.out.println("The info has been added.");

        } catch (SQLException e) {
            System.out.println(e);
        }
}

    }