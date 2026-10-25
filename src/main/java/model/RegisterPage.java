package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;

    private final By nameInput = By.xpath(".//label[text()='Имя']/../input");
    private final By emailInput = By.xpath(".//label[text()='Email']/../input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/../input");
    private final By incorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");

    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    private final By loginLink = By.xpath(".//a[@href='/login']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнить имя: {0}")
    public void setName(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(nameInput))
                .sendKeys(name);
    }

    @Step("Заполнить email: {1}")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Заполнить пароль")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Клик по кнопке «Зарегистрироваться»")
    public  void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Заполнить имя: {0}, email: {1} и пароль")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверить отображение ошибки некорректного пароля")
    public boolean isPasswordErrorDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(incorrectPassword))
                .isDisplayed();
    }

    @Step("Клик по ссылке «Войти» на странице регистрации")
    public void  clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
