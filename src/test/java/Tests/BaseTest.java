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
            System.setProperty("webdriver.chrome.driver",
                    "src/test/resources/Drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (Objects.equals(browserName, ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "edgeBrowser"))){
            System.setProperty("webdriver.edge.driver",
                    "src/test/resources/Drivers/msedgedriver.exe");
            driver = new EdgeDriver();
        } else if (Objects.equals(browserName, ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "firefoxBrowser"))){
            System.setProperty("webdriver.firefox.driver",
                    "src/test/resources/Drivers/geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else {
            System.out.println("This driver is not supported.");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://animalutulmeu.ro/farmacie/");

//        String browser = browserName;
//        if (browser.isEmpty())
//            browser = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "browser");
//        System.out.println("Set up webdriver for browser:" + browser);
//        driver = BrowserUtils.getBrowser(browser);
    }

//    public void startBrowser() {
//        String browserName = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "browser");
//        setUpDriver(browserName);
////        driver.get(baseUrl);
//    }

    @AfterMethod(alwaysRun = true)
    public void cleanUp() {
        if (driver != null)
            driver.quit();
    }
}
