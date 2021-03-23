package org.tunkenov.market_info_service.controller.json_converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.tunkenov.market_info_service.model.customer.Customer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonConverterToSearch extends JsonConverter {

    public JsonConverterToSearch(String[] args) {
        super(args);
    }

    @Override
    public JsonNode getRoot() {
        try {
            return objectMapper.readTree(new File(args[1]));
        } catch (IOException ioException) {
            String error = "Не найден входной файл.";
            executeWriteError(error);
            throw new RuntimeException(error, ioException);
        }
    }

    @Override
    public ArrayNode createNodeHeaderObject() {
        rootNode.put("type", args[0]);

        return rootNode.putArray("results");
    }

    @Override
    public void executeWriteError(String errorMessage) {
        rootNode.removeAll().put("type", "error").put("message", errorMessage);
        executeWriteResult();
    }

    @Override
    public void executeWriteResult() {
        try {
            objectMapper.writeValue(new File(args[2]), rootNode);
        } catch (IOException ioException) {
            String error = "Ошибка записи в файл.";
            executeWriteError(error);
            throw new RuntimeException(error, ioException);
        }
    }

    @Override
    public void addResult(List<Customer> customers, ArrayNode arrayResultNode) {
        for (Customer customer : customers) {
            arrayResultNode.addObject().put("lastName", customer.getLastName())
                    .put("firstName", customer.getFirstName());
        }
    }
}
