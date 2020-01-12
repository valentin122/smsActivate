package ru.zhigalov.dao;

import ru.vzhigalov.servise.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDatabase {
    private String url;
    private String user;
    private String pwd;

    static final String CREATE = "CREATE TABLE IF NOT EXISTS smsParts (TEXT varchar)";
    static final String CLEAN = "TRUNCATE smsParts";
    static final String INSERT = "INSERT INTO smsParts (TEXT) VALUES (?)";
    static final String SELECT = "SELECT * FROM smsParts";

    Config config = new Config();

    void setUrl() {
        this.url = config.get("jdbc.url");
    }

    void setLogin() {
        this.user = config.get("jdbc.user");
    }

    void setPassword() {
        this.pwd = config.get("jdbc.pwd");
    }

    public void initConfigConnectionToDb() {
        config.init();
        setUrl();
        setLogin();
        setPassword();
    }

    public List<String> createAndClearDb() throws SQLException {
        List<String> result = new ArrayList<>();
        ResultSet rs;
        try (Connection connection = DriverManager.getConnection(url, user, pwd)) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE)) {
                rs = statement.executeQuery();
                while (rs.next()) {

                    result.add(rs.getString(1));
                }
            } catch (SQLException e) {
                System.out.println("Select failed");
                e.printStackTrace();
            }
        }
        System.out.println("Data has been read from DB");
        return result;
    }
}

