package Tests;

import Utils.BrowserUtils;
import Utils.ConfigUtils;
import Utils.ConstantUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;

    String baseUrl;
    String dbHostname, dbPort, dbUser, dbPassword, dbSchema;

    @BeforeClass
    public void setUp() {
        baseUrl = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "hostname");
        //for the connection to SQL DB
        dbHostname = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "dbHostname");
        dbPort = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "dbPort");
        dbUser = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "dbUser");
        dbPassword = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "dbPassword");
        dbSchema = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "dbSchema");
    }

    public void setUpDriver(String browserName) {
        if(Objects.equals(browserName, ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "browser"))) {

            driver = new ChromeDriver();
        } else if (Objects.equals(browserName, ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "edgeBrowser"))){

            driver = new EdgeDriver();
        } else if (Objects.equals(browserName, ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "firefoxBrowser"))){

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else {
            System.out.println("This driver is not supported.");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://animalutulmeu.ro/farmacie/");
    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        if (driver != null)
            driver.quit();
    }
}
