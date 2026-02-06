package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Класс с тестами на перевод денег
class MoneyTransferTest {
    // Данные для входа (захардкожены в приложении)
    private static final String LOGIN = "vasya";
    private static final String PASSWORD = "qwerty123";
    private static final String VERIFICATION_CODE = "12345";

    // Номера карт (последние 4 цифры для идентификации)
    private static final String CARD_1 = "0001";
    private static final String CARD_2 = "0002";

    // Полные номера карт для перевода
    private static final String CARD_1_NUMBER = "5559 0000 0000 0001";
    private static final String CARD_2_NUMBER = "5559 0000 0000 0002";

    DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        // Открываем страницу приложения
        open("http://localhost:9999");
        // Логинимся
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(LOGIN, PASSWORD);
        dashboardPage = verificationPage.validVerify(VERIFICATION_CODE);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        // Получаем начальные балансы
        var firstCardBalance = dashboardPage.getCardBalance(CARD_1);
        var secondCardBalance = dashboardPage.getCardBalance(CARD_2);

        // Сумма для перевода
        var amount = 5000;

        // Выбираем первую карту для пополнения
        var transferPage = dashboardPage.selectCardToTransfer("92df3f1c-a033-48e6-8390-206f6b1f56c0");
        // Переводим деньги со второй карты на первую
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), CARD_2_NUMBER);

        // Получаем новые балансы
        var firstCardBalanceAfter = dashboardPage.getCardBalance(CARD_1);
        var secondCardBalanceAfter = dashboardPage.getCardBalance(CARD_2);

        // Проверяем, что баланс первой карты увеличился
        assertEquals(firstCardBalance + amount, firstCardBalanceAfter);
        // Проверяем, что баланс второй карты уменьшился
        assertEquals(secondCardBalance - amount, secondCardBalanceAfter);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        // Аналогичный тест, но переводим с первой карты на вторую
        var firstCardBalance = dashboardPage.getCardBalance(CARD_1);
        var secondCardBalance = dashboardPage.getCardBalance(CARD_2);

        var amount = 3000;

        var transferPage = dashboardPage.selectCardToTransfer("0f3f5c2a-249e-4c3d-8287-09f7a039391d");
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), CARD_1_NUMBER);

        var firstCardBalanceAfter = dashboardPage.getCardBalance(CARD_1);
        var secondCardBalanceAfter = dashboardPage.getCardBalance(CARD_2);

        assertEquals(firstCardBalance - amount, firstCardBalanceAfter);
        assertEquals(secondCardBalance + amount, secondCardBalanceAfter);
    }
}
