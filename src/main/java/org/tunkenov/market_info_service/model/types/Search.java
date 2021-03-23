package org.tunkenov.market_info_service.model.types;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.tunkenov.market_info_service.controller.data_base.QueryExecutor;
import org.tunkenov.market_info_service.controller.data_base.QueryExecutorToSearch;
import org.tunkenov.market_info_service.controller.json_converter.JsonConverter;
import org.tunkenov.market_info_service.controller.json_converter.JsonConverterToSearch;
import org.tunkenov.market_info_service.model.criterias.search.*;
import org.tunkenov.market_info_service.model.customer.Customer;

import java.util.Arrays;
import java.util.List;

public class Search implements Type {
    @Override
    public void start(String[] inputArgs) {
        JsonConverter jsonConverter = new JsonConverterToSearch(inputArgs);

        JsonNode rootInputData = jsonConverter.getRoot();

        List<Criteria> criteriaList = Arrays.asList(new BadCustomers(), new LastName(), new ProductName(), new MinExpenses());

        QueryExecutor queryExecutorToSearch = new QueryExecutorToSearch();

        ArrayNode nodeOutputData = jsonConverter.createNodeHeaderObject();

        rootInputData.get("criterias").forEach(node -> {
            for (Criteria criteria : criteriaList) {
                if (node.has(criteria.getName())) {
                    List<Customer> customerList = queryExecutorToSearch.read(criteria.getQuery(node));
                    jsonConverter.addResult(customerList, criteria.createHeaderResultingNode(customerList, node, nodeOutputData));
                }
            }
        });

        jsonConverter.executeWriteResult();
    }
}
