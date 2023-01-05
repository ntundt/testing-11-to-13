package by.ntundt.lab12.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() { }

    public static WebDriver getInstance() {
        if (driver == null) {
            if (System.getProperty("browser") == null) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                return driver;
            }
            switch (System.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "firefox-remote":
                    FirefoxOptions options = new FirefoxOptions();
                    options.setHeadless(true);
                    try {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "firefox":
                default:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                    break;
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }

}
