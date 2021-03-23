package org.tunkenov.market_info_service.model.criterias.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.tunkenov.market_info_service.model.customer.Customer;

import java.util.List;

public class ProductName implements Criteria {
    String name = "productName";

    public String getQuery(JsonNode node) {
        return "SELECT customers.last_name, customers.first_name\n" +
                "FROM products\n" +
                "LEFT JOIN purchases\n" +
                "ON products.id = purchases.product_id\n" +
                "LEFT JOIN customers\n" +
                "ON purchases.customer_id = customers.id\n" +
                "WHERE name = '" + String.valueOf(node.get("productName")).replace("\"", "") + "'\n" +
                "GROUP BY customers.id\n" +
                "HAVING COUNT(*) >= " + node.get("minTimes");
    }

    public ArrayNode createHeaderResultingNode(List<Customer> customers, JsonNode inputNode, ArrayNode outputNode) {
        String productName = inputNode.get("productName").textValue();
        int minTimes = Integer.parseInt(String.valueOf((inputNode.get("minTimes"))));

        ObjectNode objectNode = outputNode.addObject();
        objectNode.putObject("criteria").put("productName", productName.replace("\"", "")).put("minTimes", minTimes);
        return objectNode.putArray("results");
    }

    public String getName() {
        return name;
    }
}
