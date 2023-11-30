package com.main.exchangeBatch.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SmsMessageDTO {
    String to;
    String content;
}
