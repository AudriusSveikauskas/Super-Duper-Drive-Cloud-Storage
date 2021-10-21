package com.udacity.jwdnd.course1.cloudstorage;

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
public class UserTest {

    private static WebDriver webDriver;

    @LocalServerPort
    private int port;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    @Order(1)
    public void testSignup() {
        webDriver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", webDriver.getTitle());
        SignupPage signupPage = new SignupPage(webDriver);
        signupPage.setSignupPageFirstName("Jonas");
        signupPage.setSignupPageLastName("Jonaitis");
        signupPage.setSignupPageUsername("jonuks");
        signupPage.setSignupPagePassword("qwerty");
        signupPage.clickSignupPageSignupButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("You successfully signed up! Please continue to the login page.", webDriver.findElement(By.id("successSignupMessage")).getText());
        signupPage.clickBackToLoginButton();
    }

    @Test
    @Order(2)
    public void testLogin() {
        Assertions.assertEquals("Login", webDriver.getTitle());
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.setLoginPageUsername("jonuks");
        loginPage.setLoginPagePassword("qwerty");
        loginPage.clickLoginPageLoginButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("Home", webDriver.getTitle());
    }

    @Test
    @Order(3)
    public void testLogout() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickLogoutButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("Login", webDriver.getTitle());
        if (this.webDriver != null) {
            webDriver.quit();
        }
    }

}
