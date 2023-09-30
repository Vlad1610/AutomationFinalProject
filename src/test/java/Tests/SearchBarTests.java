package Tests;

import ObjectModels.SearchBarModel;
import PageObjects.RegistrationPage;
import PageObjects.SearchBarPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SearchBarTests extends BaseTest{
    SearchBarPage searchBarPage;
    RegistrationPage registrationpage;


    @DataProvider(name = "jsonSearchEmptyAndMatchDp")
    public Iterator<Object[]> jsonSearchMatchDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/searchBarTestsJsonFiles/searchBarTestData.json");

        SearchBarModel[] sbMatch = objectMapper.readValue(file, SearchBarModel[].class);

        for (SearchBarModel sbm: sbMatch)
            dp.add(new Object[]{sbm});
        return dp.iterator();
    }

    @DataProvider(name = "jsonSearchNoMatchDp")
    public Iterator<Object[]> jsonSearchNoMatchDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/searchBarTestsJsonFiles/searchBarNoMatchData.json");

        SearchBarModel[] sbNoMatch = objectMapper.readValue(file, SearchBarModel[].class);

        for (SearchBarModel sbNom: sbNoMatch)
            dp.add(new Object[]{sbNom});
        return dp.iterator();
    }


    //Test the search function with empty parameter and a parameter that matches the products the site has
    @Test(dataProvider = "jsonSearchEmptyAndMatchDp")
    public void searchBarUsingEmptyAndMatchInputsTest(SearchBarModel sbm) {
        setUpDriver(sbm.getBrowserName());
        searchBarModelMatchsb(sbm);
    }

    private void searchBarModelMatchsb(SearchBarModel sbm) {
        System.out.println(sbm);
        searchBar(sbm.getBrowserName(), sbm.getSearchKeyWord(),
                sbm.getSearchVisibleElement());
    }

    private void searchBar(String browserName,
                           String productName, String searchVisibleMatch) {
        System.out.println("Browser used: " + browserName);
        System.out.println("Search element: " + productName);

        searchBarPage = new SearchBarPage(driver);
        registrationpage = new RegistrationPage(driver);

        registrationpage.acceptCookies();
        searchBarPage.search(productName);

       searchBarPage.goToSearchVisibleElement();
       Assert.assertEquals(searchBarPage.searchElementTitle(),searchVisibleMatch);
    }


    //test the search function with a not matching parameter
    @Test(dataProvider = "jsonSearchNoMatchDp")
    public void searchBarTestUsingNoMatchInputsTest(SearchBarModel sbNom) {
        setUpDriver(sbNom.getBrowserName());
        searchBarModelNoMatchsb(sbNom);
    }

    private void searchBarModelNoMatchsb(SearchBarModel sbNom) {
        System.out.println(sbNom);
        searchBarNoMatch(sbNom.getBrowserName(), sbNom.getSearchKeyWord(), sbNom.getNoMatchSearchError());
    }

    private void searchBarNoMatch(String browserName, String productName, String noMatchMsg) {
        System.out.println("Browser used: " + browserName);
        System.out.println("Search element: " + productName);

        searchBarPage = new SearchBarPage(driver);
        registrationpage = new RegistrationPage(driver);

        registrationpage.acceptCookies();
        searchBarPage.search(productName);

        Assert.assertEquals(noMatchMsg, searchBarPage.noMatchSearchMess());
    }
}
