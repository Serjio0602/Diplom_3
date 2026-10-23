package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    private WebDriver driver;

    private final By emailInput = By.xpath(".//label[text()='Email']/../input");
    private final By recoverButton = By.xpath(".//button[text()='Восстановить']");

    private final By loginLink = By.xpath(".//a[@href='/login']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailInput))
                .sendKeys(email);
    }

    public void clickRecoverButton() {
        driver.findElement(recoverButton).click();
    }

    public void clickLoginLink() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginLink))
                .click();
    }
}
