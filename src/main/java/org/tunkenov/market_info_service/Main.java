package org.tunkenov.market_info_service;

import org.tunkenov.market_info_service.controller.json_converter.JsonConverter;
import org.tunkenov.market_info_service.model.types.Search;
import org.tunkenov.market_info_service.model.types.Stat;
import org.tunkenov.market_info_service.model.types.Type;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if(args.length <= 2){
            JsonConverter.writeError("Неверное колличество аргументов.");
        }

        List<Type> typeList = Arrays.asList(new Search(), new Stat());
        for (Type type : typeList) {
            if (args[0].equals(type.getClass().getSimpleName().toLowerCase())) {
                type.start(args);
            }
        }
    }
}
