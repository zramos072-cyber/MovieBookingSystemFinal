import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection connect() {

        try {

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/movie_booking",
                    "root",
                    "IMPUPpw321!"
            );

            return connection;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
