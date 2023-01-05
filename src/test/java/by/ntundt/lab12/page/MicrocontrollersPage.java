package by.ntundt.lab12.page;

import by.ntundt.lab12.model.MicrocontrollerFilters;
import by.ntundt.lab12.model.MicrocontrollerTableLine;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MicrocontrollersPage extends AbstractPage<MicrocontrollersPage> {

    public MicrocontrollersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MicrocontrollersPage openPage() {
        String microcontrollersPageUrl = "https://www.ti.com/microcontrollers-mcus-processors/microcontrollers/products.html";
        driver.get(microcontrollersPageUrl);
        return this;
    }

    @Override
    public MicrocontrollersPage agreeWithCookiePolicy() {
        return super.agreeWithCookiePolicy();
    }

    public MicrocontrollersPage agreeWithCookiesPolicy() {
        try {
            new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(new By.ById("consent_prompt_submit")));
            driver.findElement(new By.ById("consent_prompt_submit")).click();
        } catch (Exception e) {
            System.out.println("Cookies policy is already accepted");
        }
        return this;
    }

    public MicrocontrollersPage inputFilters(MicrocontrollerFilters filters) {
        WebElement freqMin = driver.findElement(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/ti-quick-searches/div[1]/div/div/div[1]/div[1]/div[1]/div/ti-rst-range-slider/div[2]/input"));
        WebElement freqMax = driver.findElement(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/ti-quick-searches/div[1]/div/div/div[1]/div[1]/div[1]/div/ti-rst-range-slider/div[3]/input"));
        WebElement flashMin = driver.findElement(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/ti-quick-searches/div[1]/div/div/div[1]/div[2]/div[1]/div/ti-rst-range-slider/div[2]/input"));
        WebElement flashMax = driver.findElement(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/ti-quick-searches/div[1]/div/div/div[1]/div[2]/div[1]/div/ti-rst-range-slider/div[3]/input"));
        WebElement ramMin = driver.findElement(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/ti-quick-searches/div[1]/div/div/div[1]/div[2]/div[2]/div/ti-rst-range-slider/div[2]/input"));
        WebElement ramMax = driver.findElement(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/ti-quick-searches/div[1]/div/div/div[1]/div[2]/div[2]/div/ti-rst-range-slider/div[3]/input"));

        inputClear(freqMin);
        inputClear(freqMax);
        inputClear(flashMin);
        inputClear(flashMax);
        inputClear(ramMin);
        inputClear(ramMax);

        freqMin.sendKeys(Integer.toString(filters.getFrequencyMinMhz()));
        freqMax.sendKeys(Integer.toString(filters.getFrequencyMaxMhz()));
        flashMin.sendKeys(Integer.toString(filters.getFlashMinKb()));
        flashMax.sendKeys(Integer.toString(filters.getFlashMaxKb()));
        ramMin.sendKeys(Double.toString(filters.getRamMinKb()));
        ramMax.sendKeys(Double.toString(filters.getRamMaxKb()));

        driver.findElement(new By.ByXPath("/html/body/main/div[3]/div/h1")).click();
        return this;
    }

    public MicrocontrollersPage applyFilters() {
        driver.findElement(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/ti-quick-searches/div[1]/div/div/div[2]/div/ti-rst-button/button")).click();
        return this;
    }

    public List<MicrocontrollerTableLine> getMicrocontrollersTable() {
        List<MicrocontrollerTableLine> lines = new ArrayList<>();

        WebElement tableResults = driver.findElement(new By.ById("tblResults"));
        List<WebElement> tableRows = tableResults.findElements(new By.ByTagName("tr"));
        for (WebElement row : tableRows) {
            List<WebElement> tableCells = row.findElements(new By.ByTagName("td"));
            if (tableCells.size() == 0) {
                continue;
            }

            String flash = tableCells.get(2).findElement(new By.ByTagName("span")).getText();
            int flashv;
            try {
                flashv = Integer.parseInt(flash);
            } catch (NumberFormatException e) {
                flashv = 0;
            }

            MicrocontrollerTableLine line = new MicrocontrollerTableLine(
                    row.getAttribute("id"),
                    Integer.parseInt(tableCells.get(1).findElement(new By.ByTagName("span")).getText()),
                    Double.parseDouble(tableCells.get(3).findElement(new By.ByTagName("span")).getText()),
                    flashv
            );
            lines.add(line);
        }

        return lines;
    }

    public boolean getApplyFiltersButtonActive() {
        WebElement applyFiltersButton = driver.findElement(new By.ByXPath("/html/body/main/div[4]/div/div/ti-selection-tool/ti-quick-searches/div[1]/div/div/div[2]/div/ti-rst-button/button"));
        return applyFiltersButton.isEnabled();
    }

    private static void inputClear(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);
    }
}
