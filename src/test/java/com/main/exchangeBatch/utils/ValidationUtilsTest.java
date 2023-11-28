package com.main.exchangeBatch.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationUtilsTest {

    @Test
    @DisplayName("휴대폰 유효성 정상")
    void testValidPhoneNumber() {
        ValidationUtils validator = new ValidationUtils();
        String validPhoneNumber1 = "01012345678";
        String validPhoneNumber2 = "01156781234";
        String validPhoneNumber3 = "01687654321";

        assertTrue(validator.isValidPhoneNumber(validPhoneNumber1));
        assertTrue(validator.isValidPhoneNumber(validPhoneNumber2));
        assertTrue(validator.isValidPhoneNumber(validPhoneNumber3));
    }

    @Test
    @DisplayName("휴대폰 유효성 오류")
    void testInvalidPhoneNumber() {
        ValidationUtils validator = new ValidationUtils();
        String invalidPhoneNumber1 = "12345678901";
        String invalidPhoneNumber2 = "abcd23456789";
        String invalidPhoneNumber3 = "017abcdefg";

        assertFalse(validator.isValidPhoneNumber(invalidPhoneNumber1));
        assertFalse(validator.isValidPhoneNumber(invalidPhoneNumber2));
        assertFalse(validator.isValidPhoneNumber(invalidPhoneNumber3));
    }
}
