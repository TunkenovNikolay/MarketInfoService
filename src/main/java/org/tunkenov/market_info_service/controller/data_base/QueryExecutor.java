package org.tunkenov.market_info_service.controller.data_base;

import org.tunkenov.market_info_service.model.customer.Customer;

import java.util.List;

public abstract class QueryExecutor {
    final ConnectionData connectionData = new ConnectionData();

    abstract public List<Customer> read(String query);
}
