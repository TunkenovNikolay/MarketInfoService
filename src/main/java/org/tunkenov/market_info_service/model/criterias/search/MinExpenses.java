package org.tunkenov.market_info_service.model.criterias.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.tunkenov.market_info_service.model.customer.Customer;

import java.util.List;

public class MinExpenses implements Criteria {
    String name = "minExpenses";

    public String getQuery(JsonNode node) {
        return "SELECT customers.last_name, customers.first_name\n" +
                "FROM products\n" +
                "LEFT JOIN purchases\n" +
                "ON products.id = purchases.product_id\n" +
                "LEFT JOIN customers\n" +
                "ON purchases.customer_id = customers.id\n" +
                "GROUP BY customers.id\n" +
                "HAVING SUM(products.price) >= " + node.get("minExpenses") + " AND SUM(products.price) <= " + node.get("maxExpenses");
    }

    public ArrayNode createHeaderResultingNode(List<Customer> customers, JsonNode inputNode, ArrayNode outputNode) {
        int minExpenses = inputNode.get("minExpenses").intValue();
        int maxExpenses = inputNode.get("maxExpenses").intValue();

        ObjectNode objectNode = outputNode.addObject();
        objectNode.putObject("criteria").put("minExpenses", minExpenses).put("maxExpenses", maxExpenses);
        return objectNode.putArray("results");
    }

    public String getName() {
        return name;
    }
}
