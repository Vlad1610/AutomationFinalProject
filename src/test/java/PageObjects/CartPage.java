package PageObjects;

import CustomExceptions.ProductNotAddedToCartException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @FindBy(xpath = "//*[@class='product-name']/a[@href='https://animalutulmeu.ro/produse/royal-canin-light-weight-care-plic-85g/']")
    private WebElement firstItemTitle;

    @FindBy(xpath = "//*[@class='product-name']/a[@href='https://animalutulmeu.ro/produse/hills-pd-canine-on-care-10kg/']")
    private WebElement secondItemTitle;

    @FindBy(xpath = "//*[@class='product-remove']/a")
    private WebElement removeFirstItemBtn;

    @FindBy(xpath = "//*[@class='product-remove']/a")
    private WebElement removeSecondItemBtn;

    @FindBy(xpath = "//*[@class='woocommerce-message']")
    private WebElement itemRemovedMessage;

    @FindBy(xpath = "//*[@class='woocommerce-message']")
    private WebElement secondItemRemovedMessage;

    @FindBy(xpath = "//*[@class='cart-empty woocommerce-info']")
    private WebElement emptyCartMessage;

    public CartPage(WebDriver driver) {
        this.actions = new Actions(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void removeItemFromCart() throws ProductNotAddedToCartException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(removeFirstItemBtn));
            removeFirstItemBtn.click();
            actions.scrollToElement(itemRemovedMessage).perform();
            wait.until(ExpectedConditions.visibilityOf(itemRemovedMessage));
        } catch (Exception e) {
            throw new ProductNotAddedToCartException("The product was not added to cart.");
        }
    }

    public void scrollToSecondItem() throws ProductNotAddedToCartException {
        try {
            actions.scrollToElement(firstItemTitle).perform();
            actions.scrollToElement(secondItemTitle).perform();
            wait.until(ExpectedConditions.visibilityOf(secondItemTitle));
        } catch (Exception e) {
            throw new ProductNotAddedToCartException("One of the products was not added to cart.");
        }
    }

    public String getFirstItemTitle() {
        try {
            return firstItemTitle.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String getSecondItemTitle() {
        try {
            return secondItemTitle.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String getRemoveItemMessage() {
        try {
            return itemRemovedMessage.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String getEmptyCartMessage() {
        try {
            return emptyCartMessage.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

}
