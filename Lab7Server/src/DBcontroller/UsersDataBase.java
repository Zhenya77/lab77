package DBcontroller;


import java.sql.*;

public class UsersDataBase {
    Statement statement;
    Connection connection;

    public UsersDataBase(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
        this.createUsersDB();
    }

    public void createUsersDB() throws SQLException {
        try {
            String createTableSQL = "CREATE TABLE users " +
                    "(login TEXT, " +
                    " password TEXT)";
            statement.execute(createTableSQL);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void addUser(String login, String password) {
        try {
            String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.execute();
        } catch (Exception e) {

        }
    }

    public boolean isValue(String word, String value) {
        try {
            ResultSet rs = statement.executeQuery("SELECT " + word + " FROM users");
            while (rs.next())
                if (value.equals(rs.getString(1)))
                    return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean checkLoginAndPassword(String login, String hashedPassword) throws SQLException {
        String sql = "SELECT * FROM users where login = \'" + login + "\';";
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            return resultSet.getString("password").equals(hashedPassword);
        }
        return false;
    }
}


