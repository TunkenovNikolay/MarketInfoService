package org.tunkenov.market_info_service.controller.data_base;

import org.tunkenov.market_info_service.model.customer.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryExecutorToStat extends QueryExecutor {
    @Override
    public List<Customer> read(String query) {
        Map<Integer, Customer> customersList = new HashMap<>();

        try {
            ResultSet resultSet = connectionData.getResultSet(query);

            Customer customer;
            int id;

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                if (!customersList.containsKey(id)) {
                    customer = new Customer(resultSet.getString(2), resultSet.getString(3));
                    customer.putPurchase(resultSet.getString(4), resultSet.getInt(5));
                    customersList.put(id, customer);
                } else {
                    customer = customersList.get(id);
                    customer.putPurchase(resultSet.getString(4), resultSet.getInt(5));
                }
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("Ошибка подключения к базе данных", sqlException);
        }

        return new ArrayList<>(customersList.values());
    }
}
