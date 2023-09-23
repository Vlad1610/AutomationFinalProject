package PageObjects;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchBarPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    JavaScriptExecutor js = (JavaScriptExecutor) driver;
    @FindBy(xpath = "//*[@class='widget woocommerce widget_product_search']//input[1]")
    private WebElement searchBarElement;

    @FindBy(xpath = "//*[@class='ion-ios-search-strong']")
    private WebElement searchExecuteBtn;

    //search parameter royal
    @FindBy(xpath = "//*[@class='product-name']/a[@href='https://animalutulmeu.ro/produse/royal-canin-light-weight-care-plic-85g/']")
    private WebElement firstVisibleSearchElement;

    @FindBy(xpath = "//*[@class='woocommerce add_to_cart_inline']/a[@href='?add-to-cart=7771']")
    private WebElement goToFirstItem;

    @FindBy(xpath = "//*[@class='product-name']/a[@href='https://animalutulmeu.ro/produse/royal-canin-hypoallergenic-puppy-3-5kg/']")
    private WebElement secondVisibleSearchElement;

    @FindBy(xpath = "//*[@class='woocommerce add_to_cart_inline']/a[@href='?add-to-cart=7682']")
    private WebElement goToSecondItem;

    @FindBy(xpath = "//*[@class='woocommerce-info']")
    private WebElement noMatchSearchMessage;


    public SearchBarPage(WebDriver driver) {
        this.actions = new Actions(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }
    public void search(String product) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBarElement));
        searchBarElement.clear();
        searchBarElement.sendKeys(product);
        searchExecuteBtn.click();
    }

    public String firstVisibleSearchEl() {
        try{
            return firstVisibleSearchElement.getText();
        }catch (NoSuchElementException ex) {
            return "";
        }
    }
    public void moveToFirstEl() {
        actions.scrollToElement(goToFirstItem).perform();
        wait.until(ExpectedConditions.visibilityOf(goToFirstItem));
    }

    public String secondVisibleSearchEl() {
        try{
            return secondVisibleSearchElement.getText();
        }catch (NoSuchElementException ex) {
            return "";
        }
    }
    public void moveToSecondElement() {
        actions.moveToElement(goToSecondItem);
        wait.until(ExpectedConditions.visibilityOf(goToSecondItem));
    }
    public String noMatchSearchMess() {
        try{
            return noMatchSearchMessage.getText();
        }catch (NoSuchElementException ex) {
            return "";
        }
    }

}
