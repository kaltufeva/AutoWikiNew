package Lib.ui;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

//В этом классе содержатся методы, которые используются для поиска
// Этот класс наследуется (дочерний) из класса MainPageObject (используется  extends)
public class SearchPageObject extends MainPageObject {
// Необходимо инициализировать драйвер
    private static final String
    SKIP_ONBOARDING = "org.wikipedia:id/fragment_onboarding_skip_button",
    SKIP_ONBOARDING_AFTER_SEARCH = "//*[@text = 'History']",
    SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
    SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TEMPLATE = "//*[@text = '{SUBSTRING}']",
    SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']" + "//*[@resource-id='org.wikipedia:id/page_list_item_title']",
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text = 'No results']";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
//      таким образом мы берем драйвер из MainPageObject
    }

    /* Template метод (шаблон) */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TEMPLATE.replace("{SUBSTRING}",substring);
    }
    /* Template метод (шаблон) */

    public void initSkipOnboarding()
    {
        this.waitForElementAndClick(By.id(SKIP_ONBOARDING), "Cannot find 'Skip' button", 5);
    }
    public void initSearchInput()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find and click search init element", 5);
    }
    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),search_line,"Cannot find and type into search input", 5);
    }
    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find search result with substring " + substring);
    }
    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),"Cannot find and click search result with substring " + substring, 10);
    }
    public void clickByOnboardingButton()
    {
        this.waitForElementAndClick(By.xpath(SKIP_ONBOARDING_AFTER_SEARCH),"Cannot find 'Got it' button", 20);
    }
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by request ", 15);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }
    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }
    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }
}
