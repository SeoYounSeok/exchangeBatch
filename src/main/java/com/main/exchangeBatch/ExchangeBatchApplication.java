package com.main.exchangeBatch;

import com.fasterxml.jackson.databind.JsonNode;
import com.main.exchangeBatch.dto.ExchangeDto;
import com.main.exchangeBatch.utils.ExchangeUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootApplication
public class ExchangeBatchApplication {


	public static void main(String[] args) {
		// ExchangeUtils를 생성합니다.
		ExchangeUtils exchangeUtils = new ExchangeUtils();

		// ExchangeUtils의 getExchangeDataAsDtoList 메서드를 호출하여 데이터를 가져옵니다.
		List<ExchangeDto> exchangeDtoList = exchangeUtils.getExchangeDataAsDtoList();

		// 가져온 데이터를 사용하는 로직을 추가합니다.
		useExchangeDtoList(exchangeDtoList);
	}

	private static void useExchangeDtoList(List<ExchangeDto> exchangeDtoList) {
		// 여기에 ExchangeDtoList를 사용하는 코드를 추가합니다.
		// 예를 들어, 각 ExchangeDto의 값을 출력하는 등의 작업을 수행할 수 있습니다.
		for (ExchangeDto exchangeDto : exchangeDtoList) {
			System.out.println("Currency: " + exchangeDto.getCur_nm());
			System.out.println("Rate: " + exchangeDto.getTts());
			// 추가적인 필드가 있다면 출력 또는 활용
		}
	}
}
