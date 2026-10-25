package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.LoginPage;
import model.MainPage;
import model.RegisterPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import user.User;
import user.UserApiClient;

import java.time.Duration;

public class RegisterTest extends BaseTest {

    private UserApiClient userApiClient;
    private User user;
    private String accessToken;

    @Before
    public void setUpRegister() {
        userApiClient = new UserApiClient();
        Faker faker = new Faker();
        user = new User();

        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(6, 10));
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isRedirected = wait.until(ExpectedConditions.urlToBe(BASE_URL + "login"));

        Assert.assertTrue("Не перекинуло на страницу входа", isRedirected);
    }

    @Test
    @DisplayName("Ошибка при вводе некорректного пароля")
    public void registrationWithIncorrectPasswordTest() {
        user.setPassword("123");

        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());

        Assert.assertTrue("Сообщение об ошибке 'Некорректный пароль' не отображается", registerPage.isPasswordErrorDisplayed());
    }

    @After
    public void tearDownRegistration() {
        if (accessToken != null) {
            userApiClient.delete(accessToken);
        } else {
            try {
                Response loginResponse = userApiClient.login(user);
                accessToken = userApiClient.getAccessToken(loginResponse);
                if (accessToken != null) {
                    userApiClient.delete(accessToken);
                }
            } catch (Exception ignored) {

            }
        }
    }
}
