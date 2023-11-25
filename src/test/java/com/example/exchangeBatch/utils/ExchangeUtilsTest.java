package com.example.exchangeBatch.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.exchangeBatch.dto.ExchangeDto;
import com.main.exchangeBatch.utils.ExchangeUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExchangeUtilsTest {

    @InjectMocks
    private ExchangeUtils exchangeUtils;

    @Mock
    private WebClient webClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("API 동기 방식 테스트 ")
    void getExchangeDataSyncTest() {
        // 테스트 대상 메서드 호출
        JsonNode result = exchangeUtils.getExchangeDataSync();
        // 결과 검증
        assertNotNull(result);
    }

    @Test
    @DisplayName("parse 테스트")
    void parseJsonTest() throws IOException {
        // 테스트 대상 메서드 호출
        JsonNode result = exchangeUtils.parseJson("{\"key\":\"value\"}");

        // 결과 검증
        assertNotNull(result);
        assertEquals("value", result.get("key").asText());
    }

    @Test
    @DisplayName("Dto 리스트 테스트 ")
    void testGetExchangeDataAsDtoList() {
        List<ExchangeDto> exchangeDtoList = exchangeUtils.getExchangeDataAsDtoList();
        assertNotNull(exchangeDtoList);
        assertFalse(exchangeDtoList.isEmpty());
    }

    @Test
    @DisplayName("Json 데이터 Getter 확인 테스트")
    void convertJsonToExchangeDtoTest() throws IOException {
        // 테스트 대상 메서드 호출
        ExchangeDto result = exchangeUtils.convertJsonToExchangeDto(new ObjectMapper().readTree("{\"cur_nm\":\"USD\",\"deal_bas_r\":1200}"));

        // 결과 검증
        assertNotNull(result);
        assertEquals("USD", result.getCur_nm());
        assertEquals("1200", result.getDeal_bas_r());
    }

    @Test
    @DisplayName("날짜 설정 테스트")
    void getSearchdateTest() {
        // 테스트 대상 메서드 호출
        String result = exchangeUtils.getSearchdate();
        // 결과 검증 (Null)
        assertNotNull(result);
    }
}