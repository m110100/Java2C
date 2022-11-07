package ReflectionAPI;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataSource {
    private static final String URL = "jdbc:postgresql://localhost:5432/java_database";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    private static Connection connection = null;

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null) {
            DriverManager.registerDriver(new Driver());

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }

        return connection;
    }

}
