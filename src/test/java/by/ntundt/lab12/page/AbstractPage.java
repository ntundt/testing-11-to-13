package by.ntundt.lab12.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage<T extends AbstractPage<T>> {
    public final WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public abstract T openPage();

    public void closePage() {
        driver.close();
    }

    public T agreeWithCookiePolicy() {
        try {
            new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(new By.ById("consent_prompt_submit")));
            driver.findElement(new By.ById("consent_prompt_submit")).click();
        } catch (Exception e) {
            System.out.println("Cookies policy is already accepted");
        }
        return (T)this;
    }
}
