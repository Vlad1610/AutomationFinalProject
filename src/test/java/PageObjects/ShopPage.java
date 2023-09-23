package PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShopPage {

    WebDriver driver;
    WebDriverWait wait;

    Actions actions;

    //butonul Magazin
    @FindBy(xpath = "//*[@id='menu-item-4749']/a")
    private WebElement shopBtn;

    //primul element de adaugat in cos
    @FindBy(xpath = "//*[@class='product-name']/a[@href='https://animalutulmeu.ro/produse/royal-canin-light-weight-care-plic-85g/']")
    private WebElement productOneElement;
    @FindBy(xpath = "//*[@class='woocommerce add_to_cart_inline']/a[@data-product_id='7771']")
    private WebElement buyBtnProductOne;

    //al doilea element de adaugat in cos
    @FindBy(xpath = "//*[@class='product-name']/a[@href='https://animalutulmeu.ro/produse/hills-pd-canine-on-care-10kg/']")
    private WebElement productTwoElement;
    @FindBy(xpath = "//*[@class='woocommerce add_to_cart_inline']/a[@data-product_id='7761']")
    private WebElement buyBtnProductTwo;

    //primul element pentru wish list
    @FindBy(xpath = "//*[@class='yith-wcwl-add-button']/a[@data-product-id='7771']")
    private WebElement favouriteOneElement;
    @FindBy(xpath = "//*[@class='yith-wcwl-add-button']/a[@data-product-id='7777']")
    private WebElement addToFavouritesProductOneBtn;

    //al doilea element pentru wish list
    @FindBy(xpath = "https://animalutulmeu.ro/produse/royal-canin-urinary-s-o-dog-7-5kg/")
    private WebElement favouriteTwoElement;
    @FindBy(xpath = "//*[@class='yith-wcwl-add-button']/a[@data-product-id='7771']")
    private WebElement addToFavouritesProductTwoBtn;

    //butonul pentru a accesa cosul de cumparaturi
    @FindBy(xpath = "//*[@class='right_section']/a")
    private WebElement goToCartBtn;

    //butonul pentru a accesa wish list
    @FindBy(xpath = "//*[@class='favourite']//span")
    private WebElement goToFarvouritesBtn;


    public ShopPage(WebDriver driver) {
        this.actions = new Actions(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void goToShop() {
        wait.until(ExpectedConditions.elementToBeClickable(shopBtn));
        shopBtn.click();
    }

    public void buyItemOneGoToCart() throws InterruptedException {
        actions.scrollToElement(buyBtnProductOne).perform();
        wait.until(ExpectedConditions.elementToBeClickable(buyBtnProductOne));
        buyBtnProductOne.click();
        actions.scrollToElement(goToCartBtn).perform();
        wait.until(ExpectedConditions.elementToBeClickable(goToCartBtn));
        Thread.sleep(2000);
        goToCartBtn.click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(goToCartBtn));
        goToCartBtn.click();
    }

    public void buyBothItemsGoToCart() {
        actions.scrollToElement(buyBtnProductOne).perform();
        wait.until(ExpectedConditions.elementToBeClickable(buyBtnProductOne));
        buyBtnProductOne.click();
        actions.scrollToElement(buyBtnProductTwo).perform();
        wait.until(ExpectedConditions.elementToBeClickable(buyBtnProductTwo));
        buyBtnProductTwo.click();
        actions.scrollToElement(goToCartBtn).perform();
        wait.until(ExpectedConditions.elementToBeClickable(goToCartBtn));
        goToCartBtn.click();
    }
    public void goToFavourites() {
        actions.scrollToElement(goToFarvouritesBtn).perform();
        wait.until(ExpectedConditions.elementToBeClickable(goToFarvouritesBtn));
        goToFarvouritesBtn.click();
    }


    public void addToWishListTwoItems() {
        wait.until(ExpectedConditions.elementToBeClickable(shopBtn));
        shopBtn.click();
        actions.scrollToElement(addToFavouritesProductOneBtn).perform();
        wait.until(ExpectedConditions.elementToBeClickable(addToFavouritesProductOneBtn));
        addToFavouritesProductOneBtn.click();
        actions.scrollToElement(addToFavouritesProductTwoBtn).perform();
        wait.until(ExpectedConditions.elementToBeClickable(addToFavouritesProductTwoBtn));
        addToFavouritesProductTwoBtn.click();
    }

    public void addOneItemToWishList() {
        wait.until(ExpectedConditions.elementToBeClickable(shopBtn));
        shopBtn.click();
        actions.scrollToElement(addToFavouritesProductOneBtn).perform();
        wait.until(ExpectedConditions.elementToBeClickable(addToFavouritesProductOneBtn));
        addToFavouritesProductOneBtn.click();
    }
}
