package com.main.exchangeBatch.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    // 하이픈(-) 없이 구성된 휴대폰 번호를 위한 정규식
    private static final String PHONE_NUMBER_REGEX = "^01(?:0|1|[6-9])(\\d{7}|\\d{8})$";

    public boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
