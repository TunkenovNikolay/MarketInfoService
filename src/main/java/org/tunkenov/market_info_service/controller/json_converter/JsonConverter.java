package org.tunkenov.market_info_service.controller.json_converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.tunkenov.market_info_service.model.customer.Customer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class JsonConverter {
    final String[] args;

    static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    static final ObjectNode rootNode = objectMapper.createObjectNode();

    public JsonConverter(String[] args) {
        this.args = args;
    }

    public static void writeError(String errorMessage) {
        rootNode.removeAll().put("error", errorMessage);
        try {
            objectMapper.writeValue(new File("output.json"), rootNode);
            System.exit(0);
        } catch (IOException ioException) {
            throw new RuntimeException("Не удалось записать файл.", ioException);
        }
    }

    abstract public JsonNode getRoot();

    abstract public ArrayNode createNodeHeaderObject();

    abstract public void executeWriteError(String errorMessage);

    abstract public void executeWriteResult();

    abstract public void addResult(List<Customer> customers, ArrayNode arrayResultNode);
}
