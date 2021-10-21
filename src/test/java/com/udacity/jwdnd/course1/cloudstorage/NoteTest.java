package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
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
public class NoteTest {

    private static WebDriver webDriver;
    public String newNoteTitle = "My First Note";
    public String newNoteDescription = "My First Note Description";
    public String modifiedNoteTitle = "Modified First Note";
    public String modifiedNoteDescription = "Modified First Note Description";

    @LocalServerPort
    private int port;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    @Order(1)
    public void testAddNote() {
        HomePage homePage = signupLogin();
        homePage.clickNavToNotesTab();
        homePage.clickAddNoteButton();
        homePage.setNoteTitle(newNoteTitle);
        homePage.setNoteDescription(newNoteDescription);
        homePage.clickSaveNoteButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("Success", webDriver.findElement(By.id("messageSuccess")).getText());
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickLinkToContinue();
        homePage.clickNavToNotesTab();
    }

    @Test
    @Order(2)
    public void testDisplayNote() {
        HomePage homePage = new HomePage(webDriver);
        Note note = homePage.getNote();
        Assertions.assertEquals(newNoteTitle, note.getNoteTitle());
        Assertions.assertEquals(newNoteDescription, note.getNoteDescription());
    }

    @Test
    @Order(3)
    public void testEditNote() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickEditNoteButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals(newNoteTitle, webDriver.findElement(By.id("note-title")).getAttribute("value"));
        Assertions.assertEquals(newNoteDescription, webDriver.findElement(By.id("note-description")).getAttribute("value"));
        webDriver.findElement(By.id("note-title")).clear();
        homePage.setNoteTitle(modifiedNoteTitle);
        webDriver.findElement(By.id("note-description")).clear();
        homePage.setNoteDescription(modifiedNoteDescription);
        homePage.clickSaveNoteButton();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("Success", webDriver.findElement(By.id("messageSuccess")).getText());
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickLinkToContinue();
        homePage.clickNavToNotesTab();
    }

    @Test
    @Order(4)
    public void testDeleteNote() {
        HomePage homePage = new HomePage(webDriver);
        homePage.clickDeleteNoteButton();
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
