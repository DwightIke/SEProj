package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dragos on 5/8/2017.
 */
public class ReviewerRepository implements ICRUDRepository {
    private Connection dbConnection;
    public ReviewerRepository(Connection connection) {
        this.dbConnection = connection;
    }
    public boolean login(String user, String password) throws SQLException {
        PreparedStatement preparedStatement = this.dbConnection.prepareStatement(
                "select * from `cm` where username=? and password=?");
        preparedStatement.setString(1, user);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        //not saved
        // exista input-uri cu username si password
        if (resultSet.next())
            return true;
        return false;
    }
}
