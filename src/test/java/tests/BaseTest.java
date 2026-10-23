package tests;

import factory.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;
    protected final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    @Before
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();

        driver.get(BASE_URL);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
