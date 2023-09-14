package Lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String

    TITLE = "//*[@resource-id = 'pcs-edit-section-title-description']",
    FOOTER_ELEMENT = "//*[contains(@text, 'Content is available under')]";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page",15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void quickSwipe()
    {
        this.swipeUpQuick();
    }
    public void waitForFooterElement()
    {
        this.waitForElementPresent(By.xpath(FOOTER_ELEMENT),"Cannot find footer", 15);
    }
}
