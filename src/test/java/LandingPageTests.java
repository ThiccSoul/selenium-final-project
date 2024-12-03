import baseConfig.CrossBrowserTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static data.Constants.*;

public class LandingPageTests extends CrossBrowserTesting {
    @Test
    public void activeCategoryTest() {
        driver.get(SWOOP_PAGE);

        WebElement category = driver.findElement(By.xpath(CATEGORY_XPATH));
        category.click();

        WebElement categorySport = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CATEGORY_SPORT_XPATH)));
        action.moveToElement(categorySport).perform();

        WebElement sportCarting = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SPORT_CARTING_XPATH)));
        sportCarting.click();

        wait.until(ExpectedConditions.urlToBe(EXPECTED_URL));
        Assert.assertEquals(driver.getCurrentUrl(), EXPECTED_URL);

        WebElement breadcrumb = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BREADCRUMB_XPATH)));

        String breadcrumbText = breadcrumb.getText();

        System.out.println("Breadcrumb Text: " + breadcrumbText);

        Assert.assertTrue(breadcrumbText.contains("კარტინგი"), BREADCRUMB_ERR_MSG);
    }

    @Test
    public void logoTest() {
        driver.get(SWOOP_PAGE);

        WebElement restBtn = driver.findElement(By.xpath(REST_BUTTON_XPATH));
        restBtn.click();

        WebElement logo = driver.findElement(By.xpath(LOGO_XPATH));
        js.executeScript(JS_CLICK, logo);

        wait.until(ExpectedConditions.urlToBe(SWOOP_PAGE));

        Assert.assertEquals(driver.getCurrentUrl(), SWOOP_PAGE, CURRENT_PAGE_ERR_MSG);
    }
}
