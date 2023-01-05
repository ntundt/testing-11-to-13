package by.ntundt.lab12.test;

import by.ntundt.lab12.page.ComparisonWindow;
import by.ntundt.lab12.page.ProductsPage;
import by.ntundt.lab12.page.SearchPage;
import com.google.common.collect.Ordering;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

@Listeners({by.ntundt.lab12.util.TestListener.class})
public class ProductsPageTests extends CommonConditions {
    @Test
    public void sortingTest() {
        List<Double> values = new ProductsPage(driver)
                .openPage()
                .agreeWithCookiePolicy()
                .applySorting()
                .getValues();

        assertTrue(Ordering.natural().reverse().isOrdered(values));
    }

    @Test
    public void reverseSortingTest() {
        List<Double> values = new ProductsPage(driver)
                .openPage()
                .agreeWithCookiePolicy()
                .applySorting()
                .applySorting()
                .getValues();

        assertTrue(Ordering.natural().isOrdered(values));
    }

    @Test
    public void loadMoreTest() {
        ProductsPage page = new ProductsPage(driver)
                .openPage()
                .agreeWithCookiePolicy();

        int initialCount = page.getValues().size();

        page.loadMore();

        int finalCount = page.getValues().size();

        assertTrue(initialCount < finalCount);
    }

    @Test
    public void loadMorePreservesSortingTest() {
        ProductsPage page = new ProductsPage(driver)
                .openPage()
                .agreeWithCookiePolicy()
                .applySorting()
                .loadMore();

        List<Double> finalValues = page.getValues();

        assertTrue(Ordering.natural().reverse().isOrdered(finalValues));
    }

    @Test
    public void comparisonTest() {
        ProductsPage page = new ProductsPage(driver)
                .openPage()
                .agreeWithCookiePolicy()
                .checkForComparison(0)
                .checkForComparison(1);

        List<String> productNames = page.getProductNames();

        ComparisonWindow window = page.goToComparison();

        List<String> comparisonProductNames = window.getProductNames();

        assertTrue(productNames.containsAll(comparisonProductNames));
    }


}
