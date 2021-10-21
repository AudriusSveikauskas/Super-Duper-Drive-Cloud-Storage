package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private final JavascriptExecutor javascriptExecutor;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    public void setLoginPageUsername(String username) {
        javascriptExecutor.executeScript("arguments[0].value='" + username + "';", inputUsername);
    }

    public void setLoginPagePassword(String password) {
        javascriptExecutor.executeScript("arguments[0].value='" + password + "';", inputPassword);
    }

    public void clickLoginPageLoginButton() {
        javascriptExecutor.executeScript("arguments[0].click();", loginButton);
    }

}
