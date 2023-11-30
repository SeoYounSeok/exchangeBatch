package com.main.exchangeBatch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ExchangeDTO {
    private Integer result; // 결과
    private String cur_unit; // 통화코드
    private String cur_nm; // 국가/통화명
    private String ttb; // 전신환(송금) 받으실 때
    private String tts; // 전신환(송금) 보내실 때
    private String deal_bas_r; // 매매 기준율
    private String bkpr; // 장부가격
    private String yy_efee_r; // 년환가료율
    private String ten_dd_efee_r; // 10일환가료율
    private String kftc_bkpr; // 서울외국환중개 매매기준율
    private String kftc_deal_bas_r; // 서울외국환중개장부가격
}