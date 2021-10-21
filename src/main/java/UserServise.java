import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserServise {
    public static void create(User user) throws SQLException {
        final String sqlCommand = String.format("INSERT INTO users (name, age, address) VALUES ('%s', %d, '%s')", user.getName(), user.getAge(), user.getAddress());
        try (Connection connection = ConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sqlCommand);
        }
    }

    public static void delete(Long id) throws SQLException {
        final String sqlCommand = String.format("DELETE FROM users WHERE id = %d", id);
        try (Connection connection = ConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sqlCommand);
        }
    }

    public static void change(Long id, User user) throws SQLException {
        final String sqlCommand = String.format("UPDATE users SET name = '%s', age = %d, address = '%s' WHERE id = %d",
                user.getName(), user.getAge(), user.getAddress(), id);
        try (Connection connection = ConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sqlCommand);
        }
    }
    public static User select(Long id)throws SQLException{
        final String sqlCommand = String.format("SELECT * FROM users WHERE id = %d", id);
        try(Connection connection = ConnectionManager.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()){
                User user = new User(id, resultSet.getString("name"), resultSet.getInt("age"),
                        resultSet.getString("address"));
                return user;
            }
        }
        return null;
    }
}
