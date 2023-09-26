package Tests;

import ObjectModels.RegistrationModel;
import PageObjects.LoginPage;
import PageObjects.RegistrationPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class RegistrationTests extends BaseTest {

    RegistrationPage registrationPage;
    LoginPage loginPage;

    @DataProvider (name = "jsonRegAbsentEmailDp")
    public Iterator<Object[]> jsonRegDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/registrationTestsJsonFiles/testRegistrationData.json");

        RegistrationModel[] rms = objectMapper.readValue(file, RegistrationModel[].class);

        for (RegistrationModel rm: rms)
            dp.add(new Object[]{rm});
        return dp.iterator();
    }

    @DataProvider (name = "jsonRegSuccessDp")
    public Iterator<Object[]> jsonSuccessDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/registrationTestsJsonFiles/successRegistrationTestData.json");

        RegistrationModel[] rss = objectMapper.readValue(file, RegistrationModel[].class);

        for (RegistrationModel rs: rss)
            dp.add(new Object[]{rs});
        return dp.iterator();
    }

    @DataProvider (name = "jsonRegEUsedDp")
    public Iterator<Object[]> jsonRegDpsCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/Data/registrationTestsJsonFiles/testRegistrationEmailUsedData.json");

        RegistrationModel[] rmsUsed = objectMapper.readValue(file, RegistrationModel[].class);

        for (RegistrationModel rmUsed: rmsUsed)
            dp.add(new Object[]{rmUsed});
        return dp.iterator();
    }

    //Test registration with empty email and the successful registration
    @Test(dataProvider = "jsonRegAbsentEmailDp")
    public void registerUsingEmptyEmailTest(RegistrationModel rm) {
        setUpDriver(rm.getBrowserName());
        registrationRm(rm);
    }

    private void registrationRm(RegistrationModel rm) {
        System.out.println(rm);
        registration(rm.getBrowserName(), rm.getRegisterEmailInput(), rm.getAbsentEmailError(),
                rm.getSuccessRegistrationMessage());
    }

    private void registration(String browserName, String registerEmail, String absentEmailErr,
                              String successRegisterMsg) {
        System.out.println("Browser used: " + browserName);
        System.out.println("Try to register using email: " + registerEmail);

        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        registrationPage.acceptCookies();
        loginPage.createAccount();
        registrationPage.register(registerEmail);

        System.out.println("Registration finished, verify message");
        Assert.assertEquals(absentEmailErr, registrationPage.getAbsentEmailError());
        Assert.assertEquals(successRegisterMsg, registrationPage.getSuccessfulRegisterMessage());
    }

    //test registration with already used email
    @Test(dataProvider = "jsonRegEUsedDp")
    public void registerUsingAlreadyUsedEmailTest(RegistrationModel rmUsed) {
        setUpDriver(rmUsed.getBrowserName());
        registrationEUsed(rmUsed);
    }

    private void registrationEUsed(RegistrationModel rmUsed) {
        System.out.println(rmUsed);
        registrationUsedEmail(rmUsed.getBrowserName(), rmUsed.getRegisterEmailInput(), rmUsed.getEmailAlreadyRegisteredError());
    }

    private void registrationUsedEmail(String browserName, String registerEmail, String emailAlreadyRegisteredError) {
        System.out.println("Browser used: " + browserName);
        System.out.println("Try to register using email: " + registerEmail);

        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        registrationPage.acceptCookies();
        loginPage.createAccount();
        registrationPage.register(registerEmail);

        System.out.println("Registration finished, verify message");
        Assert.assertEquals(emailAlreadyRegisteredError, registrationPage.getEmailAlreadyRegisteredError());
    }

    @Test(dataProvider = "jsonRegSuccessDp")
    public void registerUsingValidEmailTest(RegistrationModel rs) {
        setUpDriver(rs.getBrowserName());
        registrationRm(rs);
    }

    private void registrationRs(RegistrationModel rs) {
        System.out.println(rs);
        registrationRs(rs.getBrowserName(), rs.getRegisterEmailInput(), rs.getAbsentEmailError(),
                rs.getSuccessRegistrationMessage());
    }

    private void registrationRs(String browserName, String registerEmail, String absentEmailErr,
                              String successRegisterMsg) {
        System.out.println("Browser used: " + browserName);
        System.out.println("Try to register using email: " + registerEmail);

        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        registrationPage.acceptCookies();
        loginPage.createAccount();
        registrationPage.register(registerEmail);

        System.out.println("Registration finished, verify message");
        Assert.assertEquals(absentEmailErr, registrationPage.getAbsentEmailError());
        Assert.assertEquals(successRegisterMsg, registrationPage.getSuccessfulRegisterMessage());
    }
}
