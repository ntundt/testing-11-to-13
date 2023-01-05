package by.ntundt.lab12.page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends AbstractPage<ProductsPage> {
    private final String url = "https://www.ti.com/amplifier-circuit/comparators/products.html";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductsPage openPage() {
        driver.get(url);
        return this;
    }

    @Override
    public ProductsPage agreeWithCookiePolicy() {
        return super.agreeWithCookiePolicy();
    }

    public ProductsPage applySorting() {
        new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath("//thead[1]/tr[1]/th[5]")));
        driver.findElement(new By.ByXPath("//thead[1]/tr[1]/th[5]")).click();
        return this;
    }

    public ProductsPage loadMore() {
        new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/div[1]/ti-data-table/div[3]/ti-rst-button/button")));
        new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/div[1]/ti-data-table/div[3]/ti-rst-button/button")));
        WebElement element = driver.findElement(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/div[1]/ti-data-table/div[3]/ti-rst-button/button"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",element);

        new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath("//table[@id=\"tblResults\"]/tbody[1]/tr[26]")));
        return this;
    }

    public ProductsPage checkForComparison(int index) {
        new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath("//table[@id=\"tblResults\"]//span[@name=\"phchk\"]/span[1]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var chk = Array.from(document.getElementsByName(\"phchk\")).map(e => e.children[0]); chk[" + index + "].click();");
        return this;
    }

    public ComparisonWindow goToComparison() {
        new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(new By.ById("compare_btn")));
        driver.findElement(new By.ById("compare_btn")).click();
        //JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("document.getElementById('compare_btn').click();");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String mwh = driver.getWindowHandle();
        for (String wh : driver.getWindowHandles()) {
            if (!wh.equals(mwh)) {
                driver.switchTo().window(wh);
            }
        }

        return new ComparisonWindow(driver);
    }

    public List<String> getProductNames() {
        new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath("//a[@name=\"phlink\"]")));
        return driver.findElements(new By.ByXPath("//a[@name=\"phlink\"]"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getValues() {
        List<WebElement> elements = driver.findElements(new By.ByXPath("//table[@id=\"tblResults\"]/tbody[1]/tr/td[5]/span[1]"));
        return elements.stream().map(element -> Double.valueOf(element.getText())).collect(Collectors.toList());
    }
}
