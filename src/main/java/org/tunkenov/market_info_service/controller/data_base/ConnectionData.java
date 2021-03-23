package org.tunkenov.market_info_service.controller.data_base;

import java.sql.*;

class ConnectionData {
    private static final String url = "jdbc:postgresql://localhost:5432/shop";
    private static final String user = "postgres";
    private static final String password = "admin";

    private final Statement statement;
    private ResultSet resultSet;

    public ConnectionData() {
        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();
        } catch (SQLException sqlException) {
            throw new RuntimeException("Ошибка подключения к базе данных", sqlException);
        }
    }

    public ResultSet getResultSet(String query) {
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return resultSet;
    }
}
