package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredentialTest {

    private static WebDriver webDriver;
    public String newCredentialUrl = "https://findslime.com/shop/";
    public String newCredentialUsername = "POP-IT";
    public String newCredentialPassword = "FidgetToys";
    public String modifiedCredentialUrl = "https://findslime.com/blog/";
    public String modifiedCredentialUsername = "SLIME";
    public String modifiedCredentialPassword = "StressReliefToy";

    @LocalServerPort
    private int port;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    @Order(1)
    public void testAddCredential() {
        HomePage homePage = signupLogin();
        homePage.clickNavCredentialsTab();
        homePage.clickAddCredentialButton();
        homePage.setCredentialUrl(newCredentialUrl);
        homePage.setCredentialUsername(newCredentialUsername);
        homePage.setCredentialPassword(newCredentialPassword);
        homePage.clickSaveCredentialButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("Success", webDriver.findElement(By.id("messageSuccess")).getText());
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickLinkToContinue();
        homePage.clickNavCredentialsTab();
    }

    @Test
    @Order(2)
    public void testDisplayCredential() {
        HomePage homePage = new HomePage(webDriver);
        Credential credential = homePage.getCredential();
        Assertions.assertEquals(newCredentialUrl, credential.getUrl());
        Assertions.assertEquals(newCredentialUsername, credential.getUsername());
    }

    @Test
    @Order(3)
    public void testEditCredential() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickEditCredentialButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals(newCredentialUrl, webDriver.findElement(By.id("credential-url")).getAttribute("value"));
        Assertions.assertEquals(newCredentialUsername, webDriver.findElement(By.id("credential-username")).getAttribute("value"));
        Assertions.assertEquals(newCredentialPassword, webDriver.findElement(By.id("credential-password")).getAttribute("value"));
        webDriver.findElement(By.id("credential-url")).clear();
        homePage.setCredentialUrl(modifiedCredentialUrl);
        webDriver.findElement(By.id("credential-username")).clear();
        homePage.setCredentialUsername(modifiedCredentialUsername);
        webDriver.findElement(By.id("credential-password")).clear();
        homePage.setCredentialPassword(modifiedCredentialPassword);
        homePage.clickSaveCredentialButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("Success", webDriver.findElement(By.id("messageSuccess")).getText());
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickLinkToContinue();
        homePage.clickNavCredentialsTab();
    }

    @Test
    @Order(4)
    public void testDeleteCredential() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickDeleteCredentialButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("Success", webDriver.findElement(By.id("messageSuccess")).getText());
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickLinkToContinue();
        homePage.clickLogoutButton();
        webDriver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", webDriver.getTitle());
        webDriver.quit();
    }

    protected HomePage signupLogin() {
        webDriver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(webDriver);
        signupPage.setSignupPageFirstName("Petras");
        signupPage.setSignupPageLastName("Petraitis");
        signupPage.setSignupPageUsername("petras");
        signupPage.setSignupPagePassword("123456");
        signupPage.clickSignupPageSignupButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        signupPage.clickBackToLoginButton();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.setLoginPageUsername("petras");
        loginPage.setLoginPagePassword("123456");
        loginPage.clickLoginPageLoginButton();
        return new HomePage(webDriver);
    }

}
