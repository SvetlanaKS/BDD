package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.CardInfo;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;


class MoneyTransferTest {

    @BeforeEach
    void setup() {
        Configuration.browser = "chrome";
        open("http://localhost:9999");
    }

    private int amount = 1;
    DashboardPage dashboardPages = new DashboardPage();
    int balance = dashboardPages.getFirstCardBalance();

    CardInfo firstCard = new CardInfo("5559 0000 0000 0001", "0001", balance);
    CardInfo secondCard = new CardInfo("5559 0000 0000 0002", "0002", balance);

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);

        CardInfo.transferFromCardToCard(firstCard, secondCard, amount);
        dashboardPage.checkBalance(firstCard);
        dashboardPage.checkBalance(secondCard);
    }


}