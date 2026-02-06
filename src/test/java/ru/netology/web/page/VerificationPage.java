package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

// Класс для страницы ввода кода подтверждения
public class VerificationPage {
    // Поле для ввода кода
    private SelenideElement codeField = $("[data-test-id=code] input");
    // Кнопка "Продолжить"
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    // Метод для ввода кода подтверждения
    public DashboardPage validVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
        return new DashboardPage(); // После ввода кода переходим на главную страницу с картами
    }
}