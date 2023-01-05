package by.ntundt.lab12.test;

import by.ntundt.lab12.page.ProductsPage;
import by.ntundt.lab12.page.SearchPage;
import com.google.common.collect.Ordering;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

@Listeners({by.ntundt.lab12.util.TestListener.class})
public class SearchPageTests extends CommonConditions {
    @Test
    public void searchResultsContainSearchTerm() {
        final String componentName = "TMS320F28002x";

        List<String> searchResults = new SearchPage(driver)
                .openPage()
                .agreeWithCookiePolicy()
                .inputQuery(componentName)
                .search()
                .getSearchResults();

        assertTrue(searchResults.stream().anyMatch(searchResult -> searchResult.contains(componentName)));
    }
}
