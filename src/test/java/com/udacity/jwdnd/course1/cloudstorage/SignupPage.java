package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    private final JavascriptExecutor javascriptExecutor;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "signupButton")
    private WebElement signupButton;

    @FindBy(id = "backToLoginButton")
    private WebElement backToLoginButton;

    public void setSignupPageFirstName(String firstName) {
        javascriptExecutor.executeScript("arguments[0].value='" + firstName + "';", inputFirstName);
    }

    public void setSignupPageLastName(String lastName) {
        javascriptExecutor.executeScript("arguments[0].value='" + lastName + "';", inputLastName);
    }

    public void setSignupPageUsername(String username) {
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", inputUsername);
    }

    public void setSignupPagePassword(String password) {
        javascriptExecutor.executeScript("arguments[0].value='" + password + "';", inputPassword);
    }

    public void clickSignupPageSignupButton() {
        javascriptExecutor.executeScript("arguments[0].click();", signupButton);
    }

    public void clickBackToLoginButton() {
        javascriptExecutor.executeScript("arguments[0].click();", backToLoginButton);
    }

}
