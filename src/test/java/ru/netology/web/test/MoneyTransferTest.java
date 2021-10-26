package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.CardInfo;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;


class MoneyTransferTest {

    @BeforeEach
    void setup() {
        Configuration.browser = "chrome";
        open("http://localhost:9999");
    }


    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        int amount = 1;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCard = dashboardPage.getFirstCardBalance();
        val balanceSecondCard = dashboardPage.getSecondCardBalance();
        CardInfo firstCard = new CardInfo("5559 0000 0000 0001", "0001", balanceFirstCard);
        CardInfo secondCard = new CardInfo("5559 0000 0000 0002", "0002", balanceSecondCard);

        DataHelper.transferFromCardToCard(firstCard, secondCard, amount);

        CardInfo newBalanceFirstCard = new CardInfo("5559 0000 0000 0001", "0001", dashboardPage.getFirstCardBalance());
        CardInfo newBalanceSecondCard = new CardInfo("5559 0000 0000 0001", "0001", dashboardPage.getSecondCardBalance());
        dashboardPage.checkBalance(newBalanceFirstCard);
        dashboardPage.checkBalance(newBalanceSecondCard);
    }


}