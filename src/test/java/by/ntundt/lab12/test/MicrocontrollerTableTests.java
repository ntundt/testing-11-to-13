package by.ntundt.lab12.test;

import by.ntundt.lab12.model.MicrocontrollerFilters;
import by.ntundt.lab12.model.MicrocontrollerTableLine;
import by.ntundt.lab12.page.MicrocontrollersPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static com.sun.javafx.fxml.expression.Expression.greaterThanOrEqualTo;
import static com.sun.javafx.fxml.expression.Expression.lessThanOrEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

@Listeners({by.ntundt.lab12.util.TestListener.class})
public class MicrocontrollerTableTests extends CommonConditions {

    @Test
    public void filterWorks() {
        MicrocontrollerFilters filters = new MicrocontrollerFilters(300, 500, 1024, 4096, 0, 1024);

        List<MicrocontrollerTableLine> values = new MicrocontrollersPage(driver)
                .openPage()
                .agreeWithCookiesPolicy()
                .inputFilters(filters)
                .applyFilters()
                .getMicrocontrollersTable();

        for (MicrocontrollerTableLine line : values) {
            assertThat(line.getFlash()).isGreaterThanOrEqualTo(filters.getFlashMinKb());
            assertThat(line.getFlash()).isLessThanOrEqualTo(filters.getFlashMaxKb());
            assertThat(line.getRam()).isGreaterThanOrEqualTo(filters.getRamMinKb());
            assertThat(line.getRam()).isLessThanOrEqualTo(filters.getRamMaxKb());
            assertThat(line.getFrequency()).isGreaterThanOrEqualTo(filters.getFrequencyMinMhz());
            assertThat(line.getFrequency()).isLessThanOrEqualTo(filters.getFrequencyMaxMhz());
        }
    }

    @Test
    public void filterAcceptsCorrectParameter() {
        MicrocontrollerFilters filters = new MicrocontrollerFilters(300, 500, 1024, 4096, 0, 1024);

        boolean enabled = new MicrocontrollersPage(driver)
                .openPage()
                .agreeWithCookiesPolicy()
                .inputFilters(filters)
                .getApplyFiltersButtonActive();

        assertThat(enabled).isTrue();
    }

    @Test
    public void filterDoesNotAcceptIncorrectParameter() {
        MicrocontrollerFilters filters = new MicrocontrollerFilters(500, 300, 1024, 4096, 0, 1024);

        boolean enabled = new MicrocontrollersPage(driver)
                .openPage()
                .agreeWithCookiesPolicy()
                .inputFilters(filters)
                .getApplyFiltersButtonActive();

        assertThat(enabled).isFalse();
    }


}
