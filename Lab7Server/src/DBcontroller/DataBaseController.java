package DBcontroller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseController {
//    private final String url = "jdbc:postgresql://localhost:5432/studs";
//    private final String user = "postgres";
//    private final String password = "iow988";
    private final String url = "jdbc:postgresql://pg:5432/studs";
    private final String user = "s285685";
    private final String password = "pxr339";

    private Connection connection;
    private Statement statement;
    private static UsersDataBase users;
    private static CollectionDataBase collectionDataBase;

    public DataBaseController() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement();
        users = new UsersDataBase(connection);
        collectionDataBase = new CollectionDataBase(connection);
    }

    public static UsersDataBase getUserDataBase() {
        return users;
    }

    public static CollectionDataBase getDataBase() {
        return collectionDataBase;
    }
}