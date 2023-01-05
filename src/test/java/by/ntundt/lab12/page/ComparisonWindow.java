package by.ntundt.lab12.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class ComparisonWindow extends AbstractPage<ComparisonWindow> {

    public ComparisonWindow(WebDriver driver) {
        super(driver);
    }

    @Override
    public ComparisonWindow openPage() {
        return this;
    }

    public List<String> getProductNames() {
        new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath("//h4[@class=\"u-header-5 u-padding-top-m u-text-nowrap\"]/a[1]")));
        return driver.findElements(new By.ByXPath("//h4[@class=\"u-header-5 u-padding-top-m u-text-nowrap\"]/a"))
                .stream()
                .map(WebElement::getText)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }


}
