import baseConfig.CrossBrowserTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static data.Constants.*;
import static util.UtilMethods.*;

public class HolidayPageTests extends CrossBrowserTesting {

    @Test
    public void descendingOrderTest() throws InterruptedException {
        driver.get(SWOOP_PAGE);

        WebElement restBtn = driver.findElement(By.xpath(REST_BUTTON_XPATH));
        js.executeScript(JS_CLICK, restBtn);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIDEBAR_REST_XPATH)));

        WebElement priceOrderDropdown = driver.findElement(By.xpath(PRICE_ORDER_DROPDOWN_XPATH));
        js.executeScript(JS_CLICK, priceOrderDropdown);

        WebElement priceDescending = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PRICE_DESCENDING_XPATH)));
        priceDescending.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ACTUAL_PRICES_XPATH)));

        List<Double> allPrices = extractPrices(ACTUAL_PRICES_XPATH, NEXT_BUTTON_XPATH);

        System.out.println("All Prices List: " + allPrices);

        double maxPrice = allPrices.stream().max(Comparator.naturalOrder()).orElse(0.0);
        System.out.println("Maximum offer is: " + maxPrice + "₾");

        boolean isDescending = isListInDescendingOrder(allPrices);
        if (isDescending) {
            System.out.println("price is in descending order");
        } else {
            System.out.println("ordering is not correct");
        }
        Assert.assertEquals(allPrices.getFirst(), maxPrice, MAX_PRICE_ERR_MSG);
    }

    @Test
    public void ascendingOrderTest() throws InterruptedException {
        driver.get(SWOOP_PAGE);

        WebElement restBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(REST_BUTTON_XPATH)));
        restBtn.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIDEBAR_REST_XPATH)));

        WebElement priceOrderDropdown = driver.findElement(By.xpath(PRICE_ORDER_DROPDOWN_XPATH));

        js.executeScript(JS_CLICK, priceOrderDropdown);

        WebElement priceAscending = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PRICE_ASCENDING_XPATH)));
        js.executeScript(JS_CLICK, priceAscending);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ACTUAL_PRICES_XPATH)));

        List<Double> allPrices = extractPrices(ACTUAL_PRICES_XPATH, NEXT_BUTTON_XPATH);

        System.out.println("All Prices List: " + allPrices);

        double minPrice = allPrices.stream().min(Comparator.naturalOrder()).orElse(0.0);
        System.out.println("Minimum offer is: " + minPrice + "₾");

        if (isListInAscendingOrder(allPrices)) {
            System.out.println("price is in ascending order");
        } else {
            System.out.println("ordering is not correct");
        }
        Assert.assertEquals(allPrices.getFirst(), minPrice, MIN_PRICE_ERR_MSG);
    }

    @Test
    public void filterTest() throws InterruptedException {
        driver.get(SWOOP_PAGE);

        WebElement restBtn = driver.findElement(By.xpath(REST_BUTTON_XPATH));
        js.executeScript(JS_CLICK, restBtn);

        WebElement mountainFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(MOUNTAIN_FILTER_XPATH)));
        js.executeScript(JS_CLICK, mountainFilter);

        WebElement fullPayRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FULLPAY_RADIO_BTN_XPATH)));
        scrollToElement(fullPayRadioButton);

        try {
            js.executeScript(JS_CLICK, fullPayRadioButton);
        } catch (StaleElementReferenceException e) {
            fullPayRadioButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FULLPAY_RADIO_BTN_XPATH)));
            fullPayRadioButton.click();
        }

        WebElement fullPayLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FULLPAY_RADIO_BTN_LABEL_XPATH)));

        Assert.assertTrue(fullPayLabel.isDisplayed());

        WebElement priceOrderDropdown = driver.findElement(By.xpath(PRICE_ORDER_DROPDOWN_XPATH));
        scrollToElement(priceOrderDropdown);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRICE_ORDER_DROPDOWN_XPATH)));
        js.executeScript(JS_CLICK, priceOrderDropdown);

        WebElement priceAscending = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PRICE_ASCENDING_XPATH)));

        wait.until(ExpectedConditions.visibilityOf(priceAscending));
        js.executeScript(JS_CLICK, priceAscending);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ACTUAL_PRICES_XPATH)));

        List<Double> allPrices = extractPrices(ACTUAL_PRICES_XPATH, NEXT_BUTTON_XPATH);

        double leasExpensiveOffer = allPrices.stream().min(Comparator.naturalOrder()).orElse(0.0);
        Assert.assertEquals(leasExpensiveOffer, allPrices.getFirst());
    }

    @Test
    public void priceRangeTest() throws InterruptedException {
        driver.get(SWOOP_PAGE);

        WebElement restBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(REST_BUTTON_XPATH)));
        js.executeScript(JS_CLICK, restBtn);
        WebElement priceRangeFrom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRICE_RANGE_INPUT_FROM_XPATH)));
        WebElement priceRangeTo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRICE_RANGE_INPUT_TO_XPATH)));

        scrollToElement(priceRangeFrom);

        priceRangeFrom.sendKeys("100");
        priceRangeTo.sendKeys("200");

        WebElement priceRangeAccept = driver.findElement(By.xpath(PRICE_RANGE_ACCEPT_XPATH));
        js.executeScript(JS_CLICK, priceRangeAccept);

        List<Double> allPrices = extractPrices(ACTUAL_PRICES_XPATH, NEXT_BUTTON_XPATH);

        allPrices.forEach(price -> Assert.assertTrue(price >= 100 && price <= 200));
    }
}
