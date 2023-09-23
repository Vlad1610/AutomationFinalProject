package Tests;

import CustomExceptions.ProductNotAddedToCartException;
import ObjectModels.CartModel;
import PageObjects.CartPage;
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

public class ShoppingCartTests extends BaseTest {
    ShopPage shopPage;
    CartPage cartPage;
    RegistrationPage registrationPage;

    @DataProvider(name = "jsonCartAddItemsDp")
    public Iterator<Object[]> jsonCartAddDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/cartTestsJsonFiles/cartMessageTestData.json");

        CartModel[] cms = objectMapper.readValue(file, CartModel[].class);

        for (CartModel cm : cms)
            dp.add(new Object[]{cm});
        return dp.iterator();
    }

    @DataProvider(name = "jsonCartRemoveDp")
    public Iterator<Object[]> jsonCartRemoveDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/cartTestsJsonFiles/cartRemoveItemMessage.json");

        CartModel[] cmsRemove = objectMapper.readValue(file, CartModel[].class);

        for (CartModel cmRemove : cmsRemove)
            dp.add(new Object[]{cmRemove});
        return dp.iterator();
    }


    //Test to see if both items are added to cart
    @Test(dataProvider = "jsonCartAddItemsDp")
    public void addToCartTest(CartModel cm) throws ProductNotAddedToCartException {
        setUpDriver(cm.getBrowserName());
        cartModelcm(cm);
    }

    private void cartModelcm(CartModel cm) throws ProductNotAddedToCartException {
        System.out.println(cm);
        cart(cm.getBrowserName(), cm.getProductOneName(), cm.getProductTwoName());
    }

    private void cart(String browserName, String productOneName, String productTwoName) throws ProductNotAddedToCartException {
        System.out.println("Product added to cart: " + productOneName + " and " + productTwoName);

        cartPage = new CartPage(driver);
        shopPage = new ShopPage(driver);
        registrationPage = new RegistrationPage(driver);

        registrationPage.acceptCookies();
        shopPage.goToShop();
        shopPage.buyBothItemsGoToCart();
        driver.navigate().refresh();
        cartPage.scrollToSecondItem();

        Assert.assertEquals(cartPage.getFirstItemTitle(), productOneName);
        Assert.assertEquals(cartPage.getSecondItemTitle(), productTwoName);
    }

    //Test to see if the remove from cart function works
    @Test(dataProvider = "jsonCartRemoveDp")
    public void removeItemFromCartTest(CartModel cmR) throws ProductNotAddedToCartException, InterruptedException {
        setUpDriver(cmR.getBrowserName());
        cartModelRm(cmR);
    }

    private void cartModelRm(CartModel cmRs) throws ProductNotAddedToCartException, InterruptedException {
        System.out.println(cmRs);
        cartRemoveOneItem(cmRs.getBrowserName(), cmRs.getProductOneName(), cmRs.getProductTwoName(),
                cmRs.getItemRemovedFromCartMsg(), cmRs.getEmptyCartMsg());
    }

    private void cartRemoveOneItem(String browserName, String productOneName, String productTwoName,
                                   String itemRemovedCartMsg,
                                   String emptyCartMsg) throws ProductNotAddedToCartException, InterruptedException {
        System.out.println("Product added to cart: " + productOneName);

        cartPage = new CartPage(driver);
        shopPage = new ShopPage(driver);
        registrationPage = new RegistrationPage(driver);

        registrationPage.acceptCookies();
        shopPage.goToShop();
        shopPage.buyItemOneGoToCart();
        driver.navigate().refresh();
        cartPage.removeItemFromCart();

        Assert.assertEquals(cartPage.getRemoveItemMessage(), itemRemovedCartMsg);
        Assert.assertEquals(cartPage.getEmptyCartMessage(),emptyCartMsg);
    }
}

