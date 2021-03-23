package org.tunkenov.market_info_service.controller.data_base;

import org.tunkenov.market_info_service.model.customer.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutorToSearch extends QueryExecutor {
    @Override
    public List<Customer> read(String query) {
        List<Customer> customersList = new ArrayList<>();

        try {
            ResultSet resultSet = connectionData.getResultSet(query);

            while (resultSet.next()) {
                customersList.add(new Customer(resultSet.getString(1), resultSet.getString(2)));
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Ошибка подключения к базе данных", sqlException);
        }

        return customersList;
    }
}
