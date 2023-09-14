package Tests;

import Lib.CoreTestCase;
import Lib.ui.ArticlePageObject;
import Lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeUpConditionTest extends CoreTestCase
{
    @Test
    public void testChangeScreenOrientationOnSearchResults()
    {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSkipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        SearchPageObject.clickByOnboardingButton();
        SearchPageObject.clickByOnboardingButton();

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String tittle_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String tittle_after_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Arcticle title have been changes after screen rotation",
                tittle_before_rotation,
                tittle_after_rotation
        );

        this.rotateScreenPortrait();
        String tittle_after_second_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Arcticle title have been changes after screen rotation",
                tittle_before_rotation,
                tittle_after_second_rotation
        );


//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@text = 'History']"),
//                "Cannot find 'Got it' button",
//                5
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@text = 'History']"),
//                "Cannot find 'Got it' button",
//                5
//        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSkipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.waitForSearchResult("Automation for Apps");

        this.backgroundApp(2);

        SearchPageObject.waitForSearchResult("Automation for Apps");
    }
}
