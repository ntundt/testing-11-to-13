package by.ntundt.lab12.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends AbstractPage<SearchPage> {

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchPage openPage() {
        driver.get("https://www.ti.com/sitesearch/en-us/docs/universalsearch.tsp#numberOfResults=25");
        return this;
    }

    @Override
    public SearchPage agreeWithCookiePolicy() {
        return super.agreeWithCookiePolicy();
    }

    public SearchPage inputQuery(String componentName) {
        new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath("//input[@aria-label='Search']")));
        try {
            Thread.sleep(5000);
        } catch (Exception e) { }
        WebElement searchbar = driver.findElement(new By.ByXPath("//input[@aria-label='Search']"));
        searchbar.sendKeys(componentName);
        return this;
    }

    public SearchPage search() {
        driver.findElement(new By.ByXPath("//a[@aria-label='Search']")).click();
        new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath("//div[@class='search-cell']/p/span/span")));
        return this;
    }

    public List<String> getSearchResults() {
        return driver.findElements(new By.ByXPath("//div[@class='search-cell']/p/span/span")).stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
