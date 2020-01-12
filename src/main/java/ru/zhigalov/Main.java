package ru.zhigalov;

import ru.vzhigalov.servise.Config;
import ru.zhigalov.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Config config = new Config();
        config.init();

        Dao dao = new Dao();
        dao.initConfigConnectionToDb();

        List<String> sms = dao.getDataFromDb();
        for (String s : sms) {
            System.out.println(s);
        }
    }
}
