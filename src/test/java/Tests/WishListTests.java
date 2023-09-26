package Tests;

import CustomExceptions.ProductNotAddedToWishListException;
import ObjectModels.WishListModel;
import PageObjects.WishListPage;
import PageObjects.RegistrationPage;
import PageObjects.ShopPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class WishListTests extends BaseTest{
    WishListPage wishListPage;
    ShopPage shopPage;
    RegistrationPage registrationPage;
    @DataProvider(name = "jsonAddFavDp")
    public Iterator<Object[]> jsonAddFavDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/wishListTestsJsonFiles/favouritesTestData.json");

        WishListModel[] wms = objectMapper.readValue(file, WishListModel[].class);

        for (WishListModel wm: wms)
            dp.add(new Object[]{wm});
        return dp.iterator();
    }

    @DataProvider(name = "jsonRemoveFavDp")
    public Iterator<Object[]> jsonRemoveFavDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/wishListTestsJsonFiles/favouritesItemsRemovedData.json");

        WishListModel[] rwms = objectMapper.readValue(file, WishListModel[].class);

        for (WishListModel rwm: rwms)
            dp.add(new Object[]{rwm});
        return dp.iterator();
    }

    @Test(dataProvider = "jsonAddFavDp")
    public void addItemsToWishListTest(WishListModel wm) throws ProductNotAddedToWishListException {
        setUpDriver(wm.getBrowserName());
        wishListWlm(wm);
    }

    private void wishListWlm(WishListModel wm) throws ProductNotAddedToWishListException {
        System.out.println(wm);
        wishList(wm.getBrowserName(), wm.getFirstFavItem(), wm.getSecondFavItem());
    }

    private void wishList(String browserName, String favItemOne, String favItemTwo) throws ProductNotAddedToWishListException {
        System.out.println("Browser used: " + browserName);
        System.out.println("Added to favourites: " + favItemOne + " and " + favItemTwo);

        wishListPage = new WishListPage(driver);
        shopPage = new ShopPage(driver);
        registrationPage = new RegistrationPage(driver);

        registrationPage.acceptCookies();
        shopPage.addToWishListTwoItems();
        shopPage.goToFavourites();
        wishListPage.scrollToSecondItem();

        Assert.assertEquals(favItemOne, wishListPage.getFirstFavItemName());
        Assert.assertEquals(favItemTwo, wishListPage.getSecondFavItemName());
    }

    @Test(dataProvider = "jsonRemoveFavDp")
    public void removeItemsFromWishListTest(WishListModel rwm) throws ProductNotAddedToWishListException {
        setUpDriver(rwm.getBrowserName());
        wishListRemoveWlmr(rwm);
    }

    private void wishListRemoveWlmr(WishListModel rwm) throws ProductNotAddedToWishListException {
        System.out.println(rwm);
        wishList(rwm.getBrowserName(), rwm.getFirstFavItem(), rwm.getSecondFavItem(), rwm.getItemRemovedMessage(),
                rwm.getEmptyCartMessage());
    }

    private void wishList(String browserName, String favItemOne, String favItemTwo, String itemRemovedMsg, String emptyWishListMsg) throws ProductNotAddedToWishListException {
        System.out.println("Browser used: " + browserName);
        System.out.println("Added to favourites: " + favItemOne + " and " + favItemTwo);

        wishListPage = new WishListPage(driver);
        shopPage = new ShopPage(driver);
        registrationPage = new RegistrationPage(driver);

        registrationPage.acceptCookies();
        shopPage.addOneItemToWishList();
        shopPage.goToFavourites();

        wishListPage.removeFirstFavItem();

        Assert.assertEquals(itemRemovedMsg, wishListPage.favItemRemovedMessage());
        Assert.assertEquals(emptyWishListMsg, wishListPage.noFavItemsMessage());
    }
}
