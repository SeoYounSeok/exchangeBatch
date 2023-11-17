package com.main.exchangeBatch;

import com.fasterxml.jackson.databind.JsonNode;
import com.main.exchangeBatch.utils.ExchangeUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ExchangeBatchApplication {

	public static void main(String[] args) {
		// ExchangeUtils 인스턴스 생성
		ExchangeUtils exchangeUtils = new ExchangeUtils();

		// 비동기적으로 데이터를 가져옵니다.
		Mono<JsonNode> exchangeDataMono = exchangeUtils.getExchangeDataAsync();

		// 결과를 처리합니다.
		try {
			JsonNode jsonNode = exchangeDataMono.block(); // block()을 사용하여 비동기 작업을 동기적으로 기다립니다.

			System.out.println("Exchange Data: " + jsonNode);
			// 여기에서 필요한 작업을 수행합니다.

		} catch (Exception e) {
			System.err.println("Error retrieving exchange data: " + e);
		} finally {
			// 자원을 정리하거나 기타 작업을 수행할 수 있습니다.
		}
	}
}
