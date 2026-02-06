package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// Класс для главной страницы со списком карт
public class DashboardPage {
    // Заголовок страницы (чтобы убедиться, что мы на нужной странице)
    private SelenideElement heading = $("[data-test-id=dashboard]");
    // Все карточки карт на странице
    private ElementsCollection cards = $$(".list__item div");
    // Строка с кнопкой "Пополнить"
    private String balanceStart = "баланс: ";
    private String balanceFinish = " р.";

    // Конструктор: проверяет, что страница загрузилась
    public DashboardPage() {
        heading.shouldBe(visible);
    }

    // Метод для получения баланса карты по её номеру (последние 4 цифры)
    public int getCardBalance(String cardId) {
        val text = cards.findBy(com.codeborne.selenide.Condition.text(cardId)).getText();
        return extractBalance(text);
    }

    // Вспомогательный метод: вырезает число баланса из текста
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    // Метод для выбора карты и нажатия кнопки "Пополнить"
    public TransferPage selectCardToTransfer(String cardId) {
        cards.findBy(com.codeborne.selenide.Condition.attribute("data-test-id", cardId)).$("button").click();
        return new TransferPage();
    }
}