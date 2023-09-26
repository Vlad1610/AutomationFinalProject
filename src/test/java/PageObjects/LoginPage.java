package PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@class='header-login-form']/span")
    private WebElement loginPageBtn;

    @FindBy(id = "user_login")
    //xpath = //*[@class='login-username'']/input
    private WebElement emailInput;

    @FindBy(id= "user_pass")
    //xpath = //*[@class='login-password']/input
    private WebElement passwordInput;
    @FindBy(id = "wp-submit")
    //xpath = //*[@class="login-submit"]/input[@id='wp-submit']
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@class='acc-link']/a[1]")
    private WebElement lostPasswordButton;


    @FindBy(xpath = "//*[@class='acc-link']/a[2]")
    private WebElement createAccBtn;

    @FindBy(xpath = "//*[@class='login-remember']/label")
    private WebElement rememberMeBtn;

    @FindBy(xpath = "//*[@id='login_error']")
    //we have the visibility of the second error, so we know for sure both are displayed
    private WebElement loginErrorElement;

    @FindBy(xpath = "//*[@class='col-xs-12 col-sm-9 myaccount-content']/p[1]")
    private WebElement successfullLoginMessage;

    @FindBy(xpath = "//*[@class='acc-btn']")
    private WebElement myAccountButton;



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void goToLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(loginPageBtn));
        loginPageBtn.click();
    }

    public void createAccount() {
        wait.until(ExpectedConditions.visibilityOf(loginPageBtn));
        loginPageBtn.click();
        wait.until(ExpectedConditions.visibilityOf(createAccBtn));
        createAccBtn.click();
    }


    public void login(String email, String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(pass);
        loginBtn.click();
    }

    public String getloginError() {
        try {
            return loginErrorElement.getText().replaceAll("(\\r|\\n)", "");
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public String getSuccessLoginMsg() {
        try {
            return loginErrorElement.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public void goToMyAccount() {
        wait.until(ExpectedConditions.visibilityOf(myAccountButton));
        myAccountButton.click();
    }

    public String getSuccessfullLoginMessage() {
        try {
            return successfullLoginMessage.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }

    public void goToRegistrationPage() {
        createAccBtn.click();
    }
}
