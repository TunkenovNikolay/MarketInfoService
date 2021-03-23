package org.tunkenov.market_info_service.model.types;

import org.tunkenov.market_info_service.controller.data_base.QueryExecutorToStat;
import org.tunkenov.market_info_service.controller.json_converter.JsonConverterToStat;
import org.tunkenov.market_info_service.model.criterias.stat.DateInterval;
import org.tunkenov.market_info_service.model.customer.Customer;

import java.util.List;

public class Stat implements Type {
    @Override
    public void start(String[] inputArgs) {
        JsonConverterToStat jsonConverter = new JsonConverterToStat(inputArgs);

        DateInterval dateInterval = jsonConverter.getDates();

        jsonConverter.calcWeekDays(dateInterval.getStartDate(), dateInterval.getEndDate());

        String query = dateInterval.getQuery();

        QueryExecutorToStat queryExecutorToStat = new QueryExecutorToStat();

        List<Customer> customerList = queryExecutorToStat.read(query);

        jsonConverter.addResult(customerList, jsonConverter.createNodeHeaderObject());
    }
}
