package org.tunkenov.market_info_service.model.criterias.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.tunkenov.market_info_service.model.customer.Customer;

import java.util.List;

public interface Criteria {
    String getName();

    String getQuery(JsonNode node);

    ArrayNode createHeaderResultingNode(List<Customer> customers, JsonNode inputNode, ArrayNode outputNode);
}
