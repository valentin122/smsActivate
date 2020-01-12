package ru.zhigalov.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTestData {
    private String url;
    private String user;
    private String pwd;

    static final String CREATE = "CREATE TABLE IF NOT EXISTS SMSPARTS (" +
            "id integer, " +
            "agentId integer, " +
            "phoneFrom integer, " +
            "phoneTo integer, " +
            "text varchar, " +
            "part integer, " +
            "imei integer, " +
            "date varchar, " +
            "status integer, " +
            "smsId integer" +
            ")";
    static final String CLEAN = "TRUNCATE SMSPARTS";
    static final String INSERT = "INSERT INTO SMSPARTS (TEXT) VALUES (?)";
    //static final String SELECT = "SELECT" + " field FROM SMSPARTS";

    ru.vzhigalov.servise.Config config = new ru.vzhigalov.servise.Config();

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

    public void dbCreateAndClear() {
        //System.out.println(url);
        try (Connection connection = DriverManager.getConnection(url, user, pwd)) {
            executeQuery(connection, CREATE, "db can't create");
            executeQuery(connection, CLEAN, "db cleaning failed");
        } catch (SQLException e) {
            System.out.println("db cleaning failed");
            e.printStackTrace();
        }
    }

    void executeQuery(Connection connection, String sqlQuery, String errorMessage) {
        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(errorMessage);
            e.printStackTrace();
        }
    }
    public void insertTestData() {
        String [][] table = new String[][] {
                {"5","1","900","79874802281","Тест 2 часть 1","1","865794038962595","2019-07-12 16:30:21","0","0"},
                {"6","1","900","79874802281","Тест 2 часть 2","2","865794038962595","2019-07-12 16:31:13","0","0"},
                {"7","1","900","79874802182","Тест 3 часть 1","1","865794031446919","2019-07-12 16:32:44","0","0"},
                {"8","1","900","79874802182","Тест 3 часть 2","2","865794031446919","2019-07-12 16:32:51","0","0"},
                {"9","1","811","79874802182","Тест 4 часть 2","2","865794031446919","2019-07-12 16:42:56","0","0"},
                {"10","1","811","79874802182","Тест 4 часть 1","1","865794031446919","2019-07-12 16:43:12","0","0"},
                {"11","1","811","79874802182","Тест 4 часть 3","3","865794031446919","2019-07-15 14:42:37","0","0"},
                {"12","1","900","79874802281","Тест 1 часть 1","1","865794038962595","2019-07-15 17:00:18","0","0"},
                {"13","1","900","79874802281","Тест 1 часть 2","2","865794038962595","2019-07-15 17:00:27","0","0"},
                {"14","1","811","79874802182","Тест 5 часть 1","1","865794031446919","2019-07-15 17:13:52","0","0"},
                {"15","1","811","79874802182","Тест 5 часть 3","3","865794031446919","2019-07-15 17:13:58","0","0"},
                {"16","1","911","79871234587","Тест 6 часть 1","1","865794031446919","2019-07-15 17:58:51","0","0"}
        };
        try (Connection connection = DriverManager.getConnection(url, user, pwd)) {
            String insert = "INSERT INTO SMSPARTS (id, agentid, phonefrom, phoneto, text, part, imei, date, status, smsid) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insert)) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 12; j++) {
                        preparedStatement.setInt(i, Integer.parseInt((table[i][j])));
                        preparedStatement.addBatch();
                    }

                }
                preparedStatement.executeBatch();
                System.out.println("db inserted!");
                //executeQuery(connection, insert, "db can't create");

            } catch (SQLException e) {
                System.out.println("db inserted failed");
            }

        } catch (SQLException e) {
            System.out.println("db inserted failed");
            e.printStackTrace();
        }


    }

/*    public void dbInsert(List<String> subjects) {
        try (Connection connection = DriverManager.getConnection(url, user, pwd)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                connection.setAutoCommit(false);
                for (int i = 0; i < subjects.size(); i++) {
                    preparedStatement.setString(1, subjects.get(i));
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                System.out.println("db inserted!");

                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("db inserted failed");
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    System.out.println("rollback failed");
                    e.printStackTrace();
                }
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("db connection failed");
            e.printStackTrace();
        }
    }*/

/*    public List<String> getDataFromDb() throws SQLException {
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
    }*/
}
