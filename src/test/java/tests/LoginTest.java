package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.ForgotPasswordPage;
import model.LoginPage;
import model.MainPage;
import model.RegisterPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import user.User;
import user.UserApiClient;

import java.time.Duration;

public class LoginTest extends BaseTest{

    private UserApiClient userApiClient;
    private User user;
    private String accessToken;

    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");

    @Before
    public void setUpUser() {
        userApiClient = new UserApiClient();
        Faker faker = new Faker();
        user = new User();

        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(6, 10));

        Response response = userApiClient.register(user);
        accessToken = userApiClient.getAccessToken(response);
    }

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной странице")
    public void loginEnterToAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isOrderButtonDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();

        Assert.assertTrue("Не выполнен вход в приложуху", isOrderButtonDisplayed);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет» на главной странице")
    public void loginPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isOrderButtonDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();

        Assert.assertTrue("Не выполнен вход в приложуху", isOrderButtonDisplayed);
    }

    @Test
    @DisplayName("Вход через ссылку «Войти» в форме регистрации")
    public void loginEnterLinkTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.login(user.getEmail(), user.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isOrderButtonDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();

        Assert.assertTrue("Не выполнен вход в приложуху", isOrderButtonDisplayed);
    }

    @Test
    @DisplayName("Вход через ссылку «Войти» в форме восстановления пароля")
    public void loginViaRecoverButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRestorePasswordLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        loginPage.login(user.getEmail(), user.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isOrderButtonDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();

        Assert.assertTrue("Не выполнен вход в приложуху", isOrderButtonDisplayed);
    }

    @After
    public void tearDownUser() {
        if (accessToken != null) {
            userApiClient.delete(accessToken);
        }
    }

}
