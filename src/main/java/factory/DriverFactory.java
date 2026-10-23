package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver getDriver(){
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        switch (browser){
            case "yandex":
                String yandexPath = "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";
                options.setBinary(yandexPath);
                return new ChromeDriver(options);

            case "chrome":
            default:
                return new ChromeDriver(options);
        }
    }
}
