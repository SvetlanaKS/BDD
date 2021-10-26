package ru.netology.web.data;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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

    public static void transferFromCardToCard(CardInfo fromCard, CardInfo toCard, int amount) {
        ElementsCollection cards = $$("[class=list__item]");
        SelenideElement amountInput = $("[data-test-id=amount] input");
        SelenideElement cardFromInput = $("[data-test-id=from] input");
        SelenideElement transferButton = $("[data-test-id=action-transfer]");
        cards.findBy(text(toCard.getViewedNum())).find("[data-test-id=action-deposit]").click();
        amountInput.shouldBe(visible);
        amountInput.sendKeys(Integer.toString(amount));
        cardFromInput.sendKeys(fromCard.getNumber());
        transferButton.click();
    }

}