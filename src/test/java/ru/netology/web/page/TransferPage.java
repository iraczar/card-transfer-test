package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

// Класс для страницы перевода денег
public class TransferPage {
    // Поле для ввода суммы
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    // Поле для ввода номера карты, с которой переводим
    private SelenideElement fromInput = $("[data-test-id=from] input");
    // Поле для ввода номера карты, на которую переводим (для проверки)
    private SelenideElement toInput = $("[data-test-id=to] input");
    // Кнопка "Пополнить"
    private SelenideElement transferButton = $(byText("Пополнить"));
    // Кнопка "Отмена"
    private SelenideElement cancelButton = $(byText("Отмена"));
    // Сообщение об ошибке
    private SelenideElement errorMessage = $("[data-test-id='error-message']");

    // Метод для перевода денег
    public DashboardPage makeValidTransfer(String amountToTransfer, String fromCardNumber) {
        amountInput.setValue(amountToTransfer);
        fromInput.setValue(fromCardNumber);
        transferButton.click();
        return new DashboardPage();
    }

    // Метод для поиска ошибки на странице (используем для поиска багов)
    public void findError(String expectedText) {
        errorMessage.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(com.codeborne.selenide.Condition.exactText(expectedText));
    }
}