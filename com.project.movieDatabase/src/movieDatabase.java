import java.security.GeneralSecurityException;
import java.sql.*;
import java.util.Scanner;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;


public class movieDatabase {


    static Connection cn = null;
    static PreparedStatement pstat = null;
    static ResultSet rs = null;


    static final String DBAddress = "jdbc:mysql://localhost:3306/moviedatabase?useTimezone=true&serverTimezone=GMT";
    static final String user = "root";
    static final String pass = "glassugrör21";


    public static void main(String[] args) {

        System.out.println("Welcome to the movie database!");
        System.out.println("Do you want to:");
        System.out.println("(1) Add a new movie to the database?");
        System.out.println("(2) View a list of all the movies in the database?");


        Scanner menuScan = new Scanner(System.in);
        int movieMenu = Integer.parseInt(menuScan.next());
        switch (movieMenu) {
        case 1:
        System.out.println("Add a new movie to the database");

        try{
            Scanner scan = new Scanner(System.in);

            System.out.print("Movie Title: ");
            String movies_title = scan.nextLine();
            System.out.print("Year Released: ");
            int movies_year = scan.nextInt();
            scan.nextLine(); 
            System.out.print("Directed By: ");
            String movies_director = scan.nextLine();
            System.out.println("1: Drama ");
            System.out.println("2: Horror ");
            System.out.println("3: Thriller ");
            System.out.println("4: Comedy ");
            System.out.println("5: Action ");
            System.out.println("6: Sci-Fi ");
            System.out.println("7: Fantasy ");
            System.out.println("8: Family ");
            System.out.println("9: Documentary ");
            System.out.println("10: Animation ");
            System.out.println("Movie Genre (pick a number): ");
            int genres_id= scan.nextInt();
            scan.nextLine(); 

            cn = DriverManager.getConnection(DBAddress, user, pass);

            pstat = cn.prepareStatement("CALL addNewMovie(?, ?, ?, ?)");

            pstat.setString(1, movies_title);
            pstat.setInt(2, movies_year);
            pstat.setString(3, movies_director);
            pstat.setInt(4, genres_id);

            pstat.executeUpdate();

            cn.close();
            System.out.println("The movie has been added.");

        } catch (SQLException e) {
            System.out.println(e);
        }

        break;
        case 2:
        System.out.println("List of all movies.");

        try{
        cn = DriverManager.getConnection(DBAddress, user, pass);

            String moviesbyyear = "SELECT * FROM moviedatabase.moviesbyyear;";

            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery(moviesbyyear);

            while (rs.next())
            {
                String movieTitle = rs.getString("movies_title");
                int movieYear = rs.getInt("movies_year");
                String movieDirector = rs.getString("movies_director");
                String movieGenre = rs.getString("genres_title");
              
              System.out.format("%s, %s, %s, %s,\n", movieTitle, movieYear, movieDirector, movieGenre);
            }

            st.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        break;

        default: System.out.println("Invalid choice, try again.");
        
        break;
    }
}

    }
