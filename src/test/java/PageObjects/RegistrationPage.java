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

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @FindBy(id = "reg_email")
    private WebElement registerEmailInput;

    @FindBy(xpath = "//*[@class='woocommerce-notices-wrapper']//li")
    private WebElement absentEmailError;

    @FindBy(xpath = "//*[@class='woocommerce-notices-wrapper']//li")
    private WebElement emailAlreadyRegisteredError;

    @FindBy(xpath = "//*[@class='col-xs-12 col-sm-9 myaccount-content']/p[1]")
    private WebElement successfullRegistrationMessage;

    @FindBy(xpath = "//*[@class='woocommerce-form-row form-row']/button")
    private WebElement registerButton;

    @FindBy(xpath = "//*[@id='cookie-law-info-bar']/span/a[1]")
    private WebElement cookiesbuttonElement;


    public RegistrationPage(WebDriver driver) {
        this.actions = new Actions(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookiesbuttonElement));
        cookiesbuttonElement.click();
    }

    public void register(String email) {
        actions.scrollToElement(registerButton);
        wait.until(ExpectedConditions.elementToBeClickable(registerEmailInput));
        registerEmailInput.clear();
        registerEmailInput.sendKeys(email);
//        actions.scrollToElement(registerButton);
        registerButton.click();
    }

    public String getAbsentEmailError() {
        try {
            return absentEmailError.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String getEmailAlreadyRegisteredError() {
        try {
            return emailAlreadyRegisteredError.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String getSuccessfulRegisterMessage() {
        try {
            return successfullRegistrationMessage.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }


}
