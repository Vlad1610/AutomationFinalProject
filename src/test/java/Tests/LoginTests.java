package Tests;

import ObjectModels.LoginModel;
import ObjectModels.LoginModelForJson;
import PageObjects.LoginPage;
import PageObjects.RegistrationPage;
import Utils.Tools;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LoginTests extends BaseTest{
    String browser = "chrome";
    LoginPage loginPage;

    RegistrationPage registrationPage;

    @DataProvider(name = "mySqlDp")
    public Iterator<Object[]> sqlDpCollection() throws Exception {
        System.out.println("Use dbHostname:" + dbHostname);
        System.out.println("Use dbUser:" + dbUser);
        System.out.println("Use dbPort:" + dbPort);
        System.out.println("Use dbSchema:" + dbSchema);
        Collection<Object[]> dp = new ArrayList<>();

        Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHostname + ":" + dbPort +
                "/" + dbSchema, dbUser, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM login");
        while (resultSet.next()) {
            LoginModel lm = new LoginModel(getEscapedElement(resultSet, "email"),
                    getEscapedElement(resultSet, "pass"),
                    getEscapedElement(resultSet,"login_error"));
            dp.add(new Object[]{lm});
        }
        return dp.iterator();
    }
    private String getEscapedElement(ResultSet resultSet, String element) throws SQLException {
        return Tools.replaceElements(resultSet.getString(element), "''", "");
    }

    @DataProvider (name = "jsonLoginSuccessDp")
    public Iterator<Object[]> jsonLoginSuccDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/loginSuccessfullTestsJsonFiles/loginSuccessfullData.json");

        LoginModelForJson[] lm = objectMapper.readValue(file, LoginModelForJson[].class);

        for (LoginModelForJson lmJson: lm)
            dp.add(new Object[]{lmJson});
        return dp.iterator();
    }

    @DataProvider (name = "jsonValidCredentialsDp")
    public Iterator<Object[]> jsonValidDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/loginSuccessfullTestsJsonFiles/loginValidCredentialsFailTestData.json");

        LoginModelForJson[] lvm = objectMapper.readValue(file, LoginModelForJson[].class);

        for (LoginModelForJson lvmJson: lvm)
            dp.add(new Object[]{lvmJson});
        return dp.iterator();
    }


    //Test the Login Errors for invalid inputs using SQL DataBase
    @Test(dataProvider = "mySqlDp")
    public void loginInvalidInputsTest(LoginModel lm) {
       loginLm(lm);
    }

    private void loginLm(LoginModel lm)  {
        System.out.println(lm);
        loginLm(lm.getUsername(), lm.getPassword(), lm.getLoginError());
    }

    private void loginLm(String email, String password, String loginError) {
        System.out.println("Login with email: " + email + " and password: " + password + " on browser: " +
                browser);
        setUpDriver(browser);

        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        registrationPage.acceptCookies();
        loginPage.goToLoginPage();
        loginPage.login(email, password);

        System.out.println("Login Finished, verify error message");
        Assert.assertEquals(loginError, loginPage.getloginError());
    }


    @Test(dataProvider = "jsonValidCredentialsDp")
    public void validCredentialsLoginTest(LoginModelForJson lvmJson) {
        setUpDriver(lvmJson.getBrowserName());
        validLogin(lvmJson);
    }

    private void validLogin(LoginModelForJson lvmJson) {
        System.out.println(lvmJson);
        validLogin(lvmJson.getBrowserName(), lvmJson.getUsernameJs(), lvmJson.getPasswordJs(),
                lvmJson.getSuccessfulLoginMsgJs());
    }
    private void validLogin(String browserName, String username, String password,
                                 String successfulLoginMsg) {
        System.out.println("Browser used: " + browserName);
        System.out.println("Try to login using username: " + username + " and password: " + password );

        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        registrationPage.acceptCookies();
        loginPage.goToLoginPage();
        loginPage.login(username, password);

        Assert.assertEquals(successfulLoginMsg, loginPage.getSuccessLoginMsg());
    }

    //Test Successful Login Using Json File as Data Provider after retrying to log in
    @Test(dataProvider = "jsonLoginSuccessDp")
    public void validCredentialsRetryLoginTest(LoginModelForJson lmJson) {
        setUpDriver(lmJson.getBrowserName());
        successLogin(lmJson);
    }

    private void successLogin(LoginModelForJson lmJson) {
        System.out.println(lmJson);
        successfulLogin(lmJson.getBrowserName(), lmJson.getUsernameJs(), lmJson.getPasswordJs(),
                lmJson.getLoginErrorJs(), lmJson.getSuccessfulLoginMsgJs());
    }
    private void successfulLogin(String browserName, String username, String password,
                                 String loginError, String successfulLoginMsg) {
        System.out.println("Browser used: " + browserName);
        System.out.println("Try to login using username: " + username + " and password: " + password );

        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        registrationPage.acceptCookies();
        loginPage.goToLoginPage();
        loginPage.login(username, password);

        System.out.println("We will first get a login Error: " + loginError);
        Assert.assertEquals(loginError, loginPage.getloginError());

        System.out.println("Retry to login using the same credential:" + username + " and " + password);
        loginPage.login(username, password);
        loginPage.goToLoginPage();
        loginPage.goToMyAccount();

        Assert.assertEquals(successfulLoginMsg, loginPage.getSuccessfullLoginMessage());

    }
}
