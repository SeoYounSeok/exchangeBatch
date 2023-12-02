package com.main.exchangeBatch.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.exchangeBatch.dto.SmsMessageDTO;
import com.main.exchangeBatch.dto.SmsRequestDTO;
import com.main.exchangeBatch.dto.SmsResponseDTO;
import org.springframework.beans.factory.annotation.Value;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SmsService {
    private WebClient webClient;

    @Value("${ncloud-sms-accessKey}")
    private String accessKey;

    @Value("${ncloud-sms-secretKey}")
    private String secretKey;

    @Value("${ncloud-sms-serviceId}")
    private String serviceId;

    @Value("${ncloud-sms-fromPhoneNumber}")
    private String fromPhoneNumber;

    public boolean sendMessage(String to, String content) throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String smsURL = "https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages";
        Long currentTime = System.currentTimeMillis();

        // SmsMessageDTO setting
        SmsMessageDTO smsMessageDTO = new SmsMessageDTO(to, content);

        List<SmsMessageDTO> messages = new ArrayList<>();
        messages.add(smsMessageDTO);

        SmsRequestDTO smsRequestDTO = SmsRequestDTO.builder()
            .type("SMS")
            .contentType("COMM")
            .countryCode("82")
            .from(fromPhoneNumber)
            .content(smsMessageDTO.getContent())
            .messages(messages)
            .build();

        String body = new ObjectMapper().writeValueAsString(smsRequestDTO);

        webClient.post().uri(smsURL)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-ncp-apigw-timestamp", currentTime.toString())
                .header("x-ncp-iam-access-key", accessKey)
                .header("x-ncp-apigw-signature-v2", makeSignature(currentTime))
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(SmsResponseDTO.class).block();

        return true;
    }

    public String makeSignature(Long currentTime) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String space = " ";					// one space
        String newLine = "\n";					// new line
        String method = "POST";
        String url = "/sms/v2/services/" + this.serviceId + "/messages";
        String timestamp = currentTime.toString();
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

}
