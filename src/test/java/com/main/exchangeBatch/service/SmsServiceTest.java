package com.main.exchangeBatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SmsServiceTest {
    @Autowired
    private SmsService smsService;

    @Test
    void sendMessage() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        smsService.sendMessage("01077777777","exchageBatch message test");
    }
}