package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final JavascriptExecutor javascriptExecutor;
    private final WebDriverWait webDriverWait;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        javascriptExecutor = (JavascriptExecutor) webDriver;
        webDriverWait = new WebDriverWait(webDriver, 100);
    }

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id = "addCredentialButton")
    private WebElement addCredentialButton;

    @FindBy(id = "editNoteButton")
    private WebElement editNoteButton;

    @FindBy(id = "editCredentialButton")
    private WebElement editCredentialButton;

    @FindBy(id = "deleteNoteButton")
    private WebElement deleteNoteButton;

    @FindBy(id = "deleteCredentialButton")
    private WebElement deleteCredentialButton;

    @FindBy(id = "saveNoteButton")
    private WebElement saveNoteButton;

    @FindBy(id = "saveCredentialButton")
    private WebElement saveCredentialButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id="nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "tableNoteTitle")
    private WebElement tableNoteTitle;

    @FindBy(id = "tableNoteDescription")
    private WebElement tableNoteDescription;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(id = "tableCredentialUrl")
    private WebElement tableCredentialUrl;

    @FindBy(id = "tableCredentialUsername")
    private WebElement tableCredentialUsername;

    @FindBy(id = "tableCredentialPassword")
    private WebElement tableCredentialPassword;



    public void clickDeleteNoteButton() {
        javascriptExecutor.executeScript("arguments[0].click();", deleteNoteButton);
    }

    public Note getNote() {
        String noteTitleFromTable = webDriverWait.until(ExpectedConditions.elementToBeClickable(tableNoteTitle)).getText();
        String noteDescriptionFromTable = tableNoteDescription.getText();
        return new Note(noteTitleFromTable, noteDescriptionFromTable);
    }

    public Credential getCredential() {
        String credentialUrlFromTable = webDriverWait.until(ExpectedConditions.elementToBeClickable(tableCredentialUrl)).getText();
        String credentialUsernameFromTable = tableCredentialUsername.getText();
        String credentialPasswordFromTable = tableCredentialPassword.getText();
        return new Credential(credentialUrlFromTable, credentialUsernameFromTable, credentialPasswordFromTable);
    }

    public void clickEditNoteButton() {
        javascriptExecutor.executeScript("arguments[0].click();", editNoteButton);
    }

    public void clickSaveNoteButton() {
        javascriptExecutor.executeScript("arguments[0].click();", saveNoteButton);
    }

    public void setNoteDescription(String username) {
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", noteDescription);
    }

    public void setNoteTitle(String username) {
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", noteTitle);
    }

    public void clickLogoutButton() {
        javascriptExecutor.executeScript("arguments[0].click();", logoutButton);
    }

    public void clickNavToNotesTab() {
        javascriptExecutor.executeScript("arguments[0].click();", navNotesTab);
    }

    public void clickNavCredentialsTab() {
        javascriptExecutor.executeScript("arguments[0].click();", navCredentialsTab);
    }

    public void clickAddNoteButton() {
        javascriptExecutor.executeScript("arguments[0].click();", addNoteButton);
    }

    public void clickAddCredentialButton() {
        javascriptExecutor.executeScript("arguments[0].click();", addCredentialButton);
    }

    public void clickEditCredentialButton() {
        javascriptExecutor.executeScript("arguments[0].click();", editCredentialButton);
    }

    public void clickDeleteCredentialButton() {
        javascriptExecutor.executeScript("arguments[0].click();", deleteCredentialButton);
    }

    public void clickSaveCredentialButton() {
        javascriptExecutor.executeScript("arguments[0].click();", saveCredentialButton);
    }

    public void setCredentialUrl(String username) {
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", credentialUrl);
    }

    public void setCredentialUsername(String username) {
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", credentialUsername);
    }

    public void setCredentialPassword(String username) {
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", credentialPassword);
    }


}
