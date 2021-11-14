package ru.netology.web.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
    @Data
    @AllArgsConstructor
    public static class Card{
        private String number;
        private String viewedNum;

        public static Card getFirstCard() {
            return new Card("5559 0000 0000 0001", "0001");
        }

        public static Card getSecondCard() {
            return new Card("5559 0000 0000 0002", "0002");
        }
    }
}