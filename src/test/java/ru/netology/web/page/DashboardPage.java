package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.CardInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement cardFromInput = $("[data-test-id=from] input");
    private ElementsCollection cards = $$("[class=list__item]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);

    }

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage transferFromCardToCard(CardInfo fromCard, CardInfo toCard, int amount) {
        cards.findBy(text(toCard.getViewedNum())).find("[data-test-id=action-deposit]").click();
        amountInput.shouldBe(visible);
        amountInput.sendKeys(Integer.toString(amount));
        cardFromInput.sendKeys(fromCard.getNumber());
        transferButton.click();
        return new DashboardPage();
    }

    public void checkBalance(CardInfo card) {
        String balanceInfo = "**** **** **** %s, баланс: %s р.";
        cards.findBy(text(card.getViewedNum())).shouldHave(text(String.format(balanceInfo, card.getViewedNum(), card.getBalance())));

    }
}