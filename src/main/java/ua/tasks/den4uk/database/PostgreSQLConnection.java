package ua.tasks.den4uk.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {

    private static final Logger logger = LogManager.getLogger(PostgreSQLConnection.class);

    private static final String URL = "jdbc:postgresql://localhost:5432/resume";
    private static final String USER = "postgres";
    private static final String PASSWORD = "08postgreSQL2019";

    private static PostgreSQLConnection instance;
    private Connection connection;

    private PostgreSQLConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static PostgreSQLConnection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new PostgreSQLConnection();
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }

        return instance;
    }

}
