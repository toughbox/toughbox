package io.toughbox.auth.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OtpCodeUtilTest {
    
    @Test@DisplayName("6자리 랜덤 숫자가 생성되어야 함")
    public void test1() {
        //given & when
        String otp = OtpCodeUtil.generateOtpCode();

        //then
        //1. 숫자값이어야 함
        Assertions.assertTrue(otp.chars().allMatch(Character::isDigit));

        //2. 6자리여야 함
        Assertions.assertEquals(6, otp.length());
    }
}