package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TransferPage;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.open;


class MoneyTransferTest {

    @BeforeEach
    void setup() {
        Configuration.browser = "chrome";
        open("http://localhost:9999");
    }


    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        int amount = 100;
        val loginPage = new LoginPage();
        val transferPage = new TransferPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);

        DataHelper.Card balanceFirstCard = new DataHelper.Card("5559 0000 0000 0001", "0001", dashboardPage.getFirstCardBalance());
        DataHelper.Card balanceSecondCard = new DataHelper.Card("5559 0000 0000 0002", "0002", dashboardPage.getSecondCardBalance());

        //Подсчитать предполагаемый результат
        val expectedResultFirstCard = dashboardPage.getFirstCardBalance() - amount;
        val expectedResultSecondCard = dashboardPage.getSecondCardBalance() + amount;
        //Переводим
        TransferPage.transferFromCardToCard(balanceFirstCard, balanceSecondCard, amount);

        //Фактический результат
        DataHelper.Card newBalanceFirstCard = new DataHelper.Card("5559 0000 0000 0001", "0001", dashboardPage.getFirstCardBalance());
        val actualResultsFirstCard = newBalanceFirstCard.getBalance();
        DataHelper.Card newBalanceSecondCard = new DataHelper.Card("5559 0000 0000 0002", "0002", dashboardPage.getSecondCardBalance());
        val actualResultsSecondCard = newBalanceSecondCard.getBalance();

        //Сравниваем ожидаемый и фактический резльтат
        assertEquals(expectedResultFirstCard, actualResultsFirstCard);
        assertEquals(expectedResultSecondCard, actualResultsSecondCard);
    }


}