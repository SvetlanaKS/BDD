package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    //перевод с одной карты на другую
    public void transferFromCardToCard(DataHelper.Card fromCard, int amount) {
        SelenideElement amountInput = $("[data-test-id=amount] input");
        SelenideElement cardFromInput = $("[data-test-id=from] input");
        SelenideElement transferButton = $("[data-test-id=action-transfer]");
        amountInput.shouldBe(visible);
        amountInput.sendKeys(Integer.toString(amount));
        cardFromInput.sendKeys(fromCard.getNumber());
        transferButton.click();
    }
}
