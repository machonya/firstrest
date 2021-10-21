import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
public static Connection getConnection() throws SQLException {
   return DriverManager.getConnection("jdbc:postgresql://localhost:54320/db", "user", "pass");
}

}
