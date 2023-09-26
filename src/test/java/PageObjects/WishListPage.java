package PageObjects;

import CustomExceptions.ProductNotAddedToWishListException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WishListPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @FindBy(xpath = "//*[@class='product-name']/a[@href='https://animalutulmeu.ro/produse/royal-canin-light-weight-care-plic-85g/']")
    private WebElement firstFavItemName;

    @FindBy(xpath = "//*[@class='product-name']/a[@href='https://animalutulmeu.ro/produse/royal-canin-kitten-sterilised-plic-85g/']")
    private WebElement secondFavItemName;

    @FindBy(xpath = "//*[@class='product-remove']//div[1]")
    private WebElement removeFirstFavItemBtn;

    @FindBy(xpath = "//*[@class='product-remove']//div[1]")
    private WebElement removeSecondFavItemBtn;

    @FindBy(xpath = "//*[@class='woocommerce-message']")
    private WebElement itemRemovedMessage;

    @FindBy(xpath = "//*[@class='wishlist-empty']")
    private WebElement noItemsInFavouritesMessage;


    public WishListPage(WebDriver driver) {
        this.actions = new Actions(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void removeFirstFavItem() throws ProductNotAddedToWishListException {
        try {
            actions.scrollToElement(removeFirstFavItemBtn).perform();
            wait.until(ExpectedConditions.elementToBeClickable(removeFirstFavItemBtn));
            removeFirstFavItemBtn.click();
        } catch (Exception e) {
            throw new ProductNotAddedToWishListException("The product was not added to the wish list.");
        }
    }

    public void scrollToSecondItem() throws ProductNotAddedToWishListException {
        try {
            actions.scrollToElement(secondFavItemName).perform();
        } catch (Exception e) {
            throw new ProductNotAddedToWishListException("One of the products was not added to Wish List.");
        }
    }

    public String getFirstFavItemName() {
        try {
            return firstFavItemName.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String getSecondFavItemName() {
        try {
            return secondFavItemName.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String favItemRemovedMessage() {
        try {
            return itemRemovedMessage.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String noFavItemsMessage() {
        try {
            return noItemsInFavouritesMessage.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

}
