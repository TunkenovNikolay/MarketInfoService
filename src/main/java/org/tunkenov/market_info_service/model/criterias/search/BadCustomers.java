package org.tunkenov.market_info_service.model.criterias.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.tunkenov.market_info_service.model.customer.Customer;

import java.util.List;

public class BadCustomers implements Criteria {
    String name = "badCustomers";

    public String getQuery(JsonNode node) {
        return "SELECT customers.last_name, customers.first_name, SUM(products.price) AS purchasesCost\n" +
                "FROM purchases\n" +
                "LEFT JOIN products\n" +
                "ON products.id = purchases.product_id\n" +
                "LEFT JOIN customers\n" +
                "ON purchases.customer_id = customers.id\n" +
                "GROUP BY customers.id\n" +
                "ORDER BY purchasesCost\n" +
                "LIMIT " + node.get("badCustomers");
    }

    public ArrayNode createHeaderResultingNode(List<Customer> customers, JsonNode inputNode, ArrayNode outputNode) {
        int badCustomers = inputNode.get("badCustomers").asInt();

        ObjectNode objectNode = outputNode.addObject();
        objectNode.putObject("criteria").put("badCustomers", badCustomers);
        return objectNode.putArray("results");
    }

    public String getName() {
        return name;
    }
}
