package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Cosmin on 4/6/2017.
 */
public class AuthorsRepository implements ICRUDRepository {
    private Connection dbConnection;
    public AuthorsRepository(Connection connection) {
        this.dbConnection = connection;
    }
    public boolean login(String user, String password) throws SQLException {
        PreparedStatement preparedStatement = this.dbConnection.prepareStatement(
                "select * from `authors` where username=? and password=?");
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        // exista input-uri cu username si password
        if (resultSet.next())
            return true;
        else return false;
    }
}
