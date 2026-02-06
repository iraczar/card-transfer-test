package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

// Класс для страницы логина (входа в приложение)
public class LoginPage {
    // Поле для ввода логина
    private SelenideElement loginField = $("[data-test-id=login] input");
    // Поле для ввода пароля
    private SelenideElement passwordField = $("[data-test-id=password] input");
    // Кнопка "Продолжить"
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    // Метод для входа в систему
    // Принимает логин и пароль, вводит их и нажимает кнопку
    public VerificationPage validLogin(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        loginButton.click();
        return new VerificationPage(); // После логина переходим на страницу ввода кода
    }
}