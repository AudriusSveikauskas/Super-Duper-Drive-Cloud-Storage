package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    private final JavascriptExecutor javascriptExecutor;

    public ResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    @FindBy(id = "linkToContinue")
    private WebElement linkToContinue;

    public void clickLinkToContinue() {
        javascriptExecutor.executeScript("arguments[0].click();", linkToContinue);
    }


}
