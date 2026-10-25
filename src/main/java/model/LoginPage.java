package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    private final By emailInput = By.xpath(".//label[text()='Email']/../input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/../input");

    private final By loginButton = By.xpath(".//button[text()='Войти']");

    private final By registerLink = By.xpath(".//a[@href='/register']");
    private final By restorePasswordLink = By.xpath(".//a[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести email: {0}")
    public void setEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailInput))
                .sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Клик по кнопке «Войти»")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Авторизоваться с email: {0}")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Клик по ссылке «Зарегистрироваться»")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    @Step("Клик по ссылке «Восстановить пароль»")
    public void clickRestorePasswordLink() {
        driver.findElement(restorePasswordLink).click();
    }
}
