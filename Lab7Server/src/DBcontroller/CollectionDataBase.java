package DBcontroller;


import controller.DragonCollection;
import dragon.Color;
import dragon.Coordinates;
import dragon.Dragon;
import dragon.Person;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Map;

public class CollectionDataBase {
    Statement statement;
    PreparedStatement preparedStatement;
    private static Connection connection;

    public CollectionDataBase(Connection connection) throws SQLException {
        CollectionDataBase.connection = connection;
        this.statement = connection.createStatement();
        this.createDB();
        loadCollection(DragonCollection.collection);
    }


    public static long getId() throws SQLException {
        String sql = " SELECT nextval('id_sequence')";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            long id = rs.getLong(1);
            System.out.println(id);
            return id;
        }
        return 0;
    }

    public void createDB() {
        try {
            try {
                String createSequence = "CREATE SEQUENCE id_sequence start 1 increment 1;";
                statement.execute(createSequence);
            } catch (Exception e) {
            }
            String createTableSQL = "CREATE TABLE dragons" +
                    "(key BIGINT PRIMARY KEY NOT NULL ," +
                    " username TEXT NOT NULL , " +
                    " name TEXT NOT NULL , " +
                    " x DOUBLE PRECISION  NOT NULL , " +
                    " y REAL NOT NULL , " +
                    " creationDate TEXT NOT NULL , " +
                    " age BIGINT  , " +
                    " description TEXT , " +
                    " speaking BOOLEAN, " +
                    " color TEXT)";
            statement.execute(createTableSQL);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        try {
            String createTableSQL = "CREATE TABLE killers" +
                    "(key BIGINT PRIMARY KEY NOT NULL ," +
                    " name TEXT NOT NULL , " +
                    " height REAL NOT NULL , " +
                    " eyeColor TEXT NOT NULL , " +
                    " hairColor TEXT NOT NULL )";
            statement.execute(createTableSQL);
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }

    public void loadCollection(Hashtable<Long, Dragon> coll) {
        try {
            String sql = "TRUNCATE dragons;";
            statement.execute(sql);
            sql = "TRUNCATE killers;";
            statement.execute(sql);
            for (Map.Entry<Long, Dragon> entry : coll.entrySet()) {
                this.insertObject(entry.getValue());
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }


    public Hashtable<Long, Dragon> getCollection() {
        try {
            Hashtable<Long, Dragon> collection = new Hashtable();
            String sql = " SELECT * FROM dragons";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Dragon dragon = new Dragon();
                long id = rs.getLong("key");
                dragon.setId(id);
                dragon.setName(rs.getString("name"));
                dragon.setUsername(rs.getString("username"));
                Coordinates coordinates = new Coordinates();
                coordinates.setX(rs.getDouble("x"));
                coordinates.setY(rs.getFloat("y"));
                dragon.setCoordinates(coordinates);
                dragon.setCreationDate(LocalDateTime.parse(rs.getString("creationDate")));
                dragon.setAge(rs.getLong("age"));
                dragon.setDescription(rs.getString("description"));
                dragon.setSpeaking(rs.getBoolean("speaking"));
                dragon.setColor(Color.valueOf(rs.getString("color")));
                collection.put(id, dragon);
            }
            sql = " SELECT * FROM killers";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                Person killer = new Person();
                killer.setName(rs.getString("name"));
                killer.setHeight(rs.getFloat("height"));
                killer.setEyeColor(Color.valueOf(rs.getString("eyeColor")));
                killer.setHairColor(Color.valueOf(rs.getString("hairColor")));
                collection.get(rs.getLong("key")).setKiller(killer);
            }
            return collection;
        } catch (Exception e) {
            e.printStackTrace();
            return new Hashtable<Long, Dragon>();
        }
    }

    public void insertObject(Dragon dragon) throws SQLException {
        try {
            String sql = "INSERT INTO dragons (key, username, name,  x, y, creationDate, age, description, speaking, " +
                    "color) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, dragon.getId());
            preparedStatement.setString(2, dragon.getUsername());
            preparedStatement.setString(3, dragon.getName());
            preparedStatement.setDouble(4, dragon.getCoordinates().getX());
            preparedStatement.setFloat(5, dragon.getCoordinates().getY());
            LocalDateTime localDate = dragon.getCreationDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedString = localDate.format(formatter);
            preparedStatement.setString(6, formattedString);
            preparedStatement.setLong(7, dragon.getAge());
            preparedStatement.setString(8, dragon.getDescription());
            preparedStatement.setBoolean(9, dragon.isSpeaking());
            try {
                preparedStatement.setString(10, dragon.getColor().toString());
            } catch (Exception e) {
                preparedStatement.setString(10, null);
            }
            preparedStatement.execute();
        } catch (Exception e) {
        }
        try {
            Person killer = dragon.getKiller();
            if (killer != null) {
                String sql = "INSERT INTO killers (key, name,  height, eyeColor, hairColor) " +
                        "VALUES (?, ?, ?, ?, ?);";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, dragon.getId());
                preparedStatement.setString(2, killer.getName());
                preparedStatement.setFloat(3, killer.getHeight());
                preparedStatement.setString(4, killer.getEyeColor().toString());
                preparedStatement.setString(5, killer.getHairColor().toString());
                preparedStatement.execute();
            }
        } catch (Exception e) {
        }
    }
}
