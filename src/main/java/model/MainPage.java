package model;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage  {

    private final WebDriver driver;

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//a[@href='/account']");

    private final By bunTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By sauceTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingTab = By.xpath(".//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке «Личный кабинет»")
    public void clickPersonalAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(personalAccountButton))
                .click();
    }

    @Step("Клик по кнопке «Войти в аккаунт»")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    @Step("Клик по табу «Булки»")
    public void clickBunTab() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(bunTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    @Step("Клик по табу «Соусы»")
    public void clickSauceTab() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(sauceTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    @Step("Клик по табу «Начинки»")
    public void clickFillingTab() {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(fillingTab));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    @Step("Проверить, что вкладка «Булки» активна")
    public boolean isBunTabActive() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(driver -> driver.findElement(bunTab).getAttribute("class").contains("current"));
    }

    @Step("Проверяем, что вкладка «Соусы» активна")
    public boolean isSauceTabActive() {
        String classAttribute = driver.findElement(sauceTab).getAttribute("class");
        return classAttribute.contains("current");
    }

    @Step("Проверить, что вкладка «Начинки» активна")
    public boolean isFillingTabActive() {
        String classAttribute = driver.findElement(fillingTab).getAttribute("class");
        return classAttribute.contains("current");
    }
}
