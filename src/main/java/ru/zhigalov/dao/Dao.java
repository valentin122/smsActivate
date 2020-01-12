package ru.zhigalov.dao;

import ru.vzhigalov.servise.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private String url;
    private String user;
    private String pwd;

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

    public List<String> getDataFromDb() throws SQLException {
        List<String> result = new ArrayList<>();
        ResultSet rs;
        try (Connection connection = DriverManager.getConnection(url, user, pwd)) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT)) {
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
