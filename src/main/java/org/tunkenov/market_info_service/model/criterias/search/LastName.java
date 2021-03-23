package org.tunkenov.market_info_service.model.criterias.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.tunkenov.market_info_service.model.customer.Customer;

import java.util.List;

public class LastName implements Criteria {
    String name = "lastName";

    public String getQuery(JsonNode node) {
        return "SELECT last_name, first_name FROM customers WHERE last_name = '" + String.valueOf(node.get("lastName")).replace("\"", "") + "'";
    }

    public ArrayNode createHeaderResultingNode(List<Customer> customers, JsonNode inputNode, ArrayNode outputNode) {
        String lastName = inputNode.get("lastName").textValue();

        ObjectNode objectNode = outputNode.addObject();
        objectNode.putObject("criteria").put("lastName", lastName.replace("\"", ""));
        return objectNode.putArray("results");
    }

    public String getName() {
        return name;
    }
}
