package com.main.exchangeBatch.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.exchangeBatch.dto.ExchangeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExchangeUtils {
    @Autowired
    private WebClient webClient;

    @Value("${exchange.authkey}")
    String authkey;

    @Value("${exchange.data}")
    String data;

    LocalDate currentDate = LocalDate.now();
    String searchdate = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    public JsonNode getExchangeDataSync() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        // WebClient를 생성합니다.
        webClient = WebClient.builder().uriBuilderFactory(factory).build();

        // WebClient를 사용하여 동기적으로 데이터를 요청하고, 바로 parseJson 함수를 호출합니다.
        String responseBody = webClient.get()
                .uri(builder -> builder
                        .scheme("https")
                        .host("www.koreaexim.go.kr")
                        .path("/site/program/financial/exchangeJSON")
                        .queryParam("authkey", authkey)
                        .queryParam("searchdate", searchdate)
                        .queryParam("data", data)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block(); // 동기적으로 결과를 얻음

        return parseJson(responseBody);
    }

    private JsonNode parseJson(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(responseBody);
        } catch (IOException e) {
            // 예외 처리 필요
            e.printStackTrace();
            return null;
        }
    }
    public List<ExchangeDto> getExchangeDataAsDtoList() {
        JsonNode jsonNode = getExchangeDataSync();

        if (jsonNode != null && jsonNode.isArray()) {
            List<ExchangeDto> exchangeDtoList = new ArrayList<>();

            for (JsonNode node : jsonNode) {
                ExchangeDto exchangeDto = convertJsonToExchangeDto(node);
                exchangeDtoList.add(exchangeDto);
            }

            return exchangeDtoList;
        }

        return Collections.emptyList();
    }

    private ExchangeDto convertJsonToExchangeDto(JsonNode jsonNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.treeToValue(jsonNode, ExchangeDto.class);
        } catch (JsonProcessingException e) {
            // 예외 처리 필요
            e.printStackTrace();
            return null;
        }
    }
}