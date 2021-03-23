package org.tunkenov.market_info_service.controller.json_converter;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.tunkenov.market_info_service.model.criterias.stat.DateInterval;
import org.tunkenov.market_info_service.model.customer.Customer;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class JsonConverterToStat extends JsonConverter {
    long days;

    public JsonConverterToStat(String[] args) {
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
        rootNode.put("totalDays", days);

        return rootNode.putArray("Customers");
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
    public void addResult(List<Customer> customers, ArrayNode outputNode) {
        int queryTotalExpenses = 0;

        for (Customer customer : customers) {
            ObjectNode objectNode = outputNode.addObject();

            objectNode.put("name", customer.getLastName().replace("\"", "") +
                    " " + customer.getFirstName().replace("\"", ""));

            ArrayNode arrayNode = objectNode.putArray("purchases");

            Map<String, Integer> purchasesMap = customer.getPurchases();

            purchasesMap.forEach((name, expenses) -> arrayNode.addObject().put("name", name).put("expenses", expenses));

            objectNode.put("totalExpenses", customer.getTotalExpenses());

            queryTotalExpenses += customer.getTotalExpenses();
        }

        double avgExpenses = ((double) queryTotalExpenses) / customers.size();

        rootNode.put("totalExpenses", queryTotalExpenses).put("avgExpenses", Math.round(avgExpenses * 100.0) / 100.0);

        executeWriteResult();
    }

    public DateInterval getDates() {
        try {
            return objectMapper.readValue(new File(args[1]), DateInterval.class);
        } catch (JsonMappingException jsonMappingException) {
            String errorMessage = "Неверный формат даты.";
            executeWriteError(errorMessage);
            throw new RuntimeException(errorMessage, jsonMappingException);
        } catch (IOException ioException) {
            String errorMessage = "Файл с именем " + args[1] + " не найден.";
            executeWriteError(errorMessage);
            throw new RuntimeException(errorMessage, ioException);
        }
    }

    public void calcWeekDays(final LocalDate start, final LocalDate end) {
        final DayOfWeek startW = start.getDayOfWeek();
        final DayOfWeek endW = end.getDayOfWeek();

        long days = ChronoUnit.DAYS.between(start, end.plusDays(1));
        long daysWithoutWeekends = days - 2 * ((days + startW.getValue()) / 7);

        this.days = daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }
}
