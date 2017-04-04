package main.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Cosmin on 4/4/2017.
 */
public class Database {
    private Connection connection;
    private String path;

    public void execute(String sql) throws SQLException {
        //System.out.println("Executing sql " + sql);
        connection.prepareStatement(sql);
    }

    public Database(String path) throws ClassNotFoundException, SQLException {
        //System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath !", e);
        }
        //Class.forName("org.mysql.JDBC");
        this.path = "jdbc:mysql:" + path;

    }
    public boolean startConnection(String user, String password) throws SQLException {
        if ((this.connection = DriverManager.getConnection(this.path, user, password)) != null)
            return true;
        else
            return false;
    }
    public Connection getConnection() {
        return connection;
    }
}
