package com.main.exchangeBatch.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExchangeUtils {
    @Value("${exchange.authkey}")
    String authkey;

    @Value("${exchange.data}")
    String data;

    LocalDate currentDate = LocalDate.now();
    String searchdate = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    public Mono<JsonNode> getExchangeDataAsync() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);

        // WebClient를 생성합니다.
        WebClient webClient = WebClient.builder().uriBuilderFactory(factory).build();

        // WebClient를 사용하여 비동기적으로 데이터를 요청합니다.
        return webClient.get()
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
                .flatMap(this::parseJson);
    }

    private Mono<JsonNode> parseJson(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return Mono.just(jsonNode);
        } catch (IOException e) {
            return Mono.error(e);
        }
    }
}