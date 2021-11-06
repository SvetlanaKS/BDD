package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {

    public static void transferFromCardToCard(DataHelper.Card fromCard, DataHelper.Card toCard, int amount) {
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
