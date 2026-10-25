package model;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    private final WebDriver driver;

    private final By loginLink = By.xpath(".//a[@href='/login']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }



    @Step("Клик по ссылке «Войти» на странице восстановления пароля")
    public void clickLoginLink() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginLink))
                .click();
    }
}
