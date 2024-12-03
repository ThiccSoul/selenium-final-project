package util;

import baseConfig.CrossBrowserTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static data.Constants.JS_SCROLL_TO_ELEMENT;

public class UtilMethods extends CrossBrowserTesting {

    public static void scrollToElement(WebElement element) {
        js.executeScript(JS_SCROLL_TO_ELEMENT, element);
    }

    public static boolean isListInDescendingOrder(List<Double> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) < list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isListInAscendingOrder(List<Double> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static List<Double> extractPrices(String pricesXPath, String nextButtonXPath) throws InterruptedException {

        Thread.sleep(5000);
        List<Double> allPrices = new ArrayList<>();
        while (true) {

            List<WebElement> priceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(pricesXPath)));

            priceElements.forEach(webElementPrice -> {
                String priceText = webElementPrice.getText();
                try {
                    double priceValue = Double.parseDouble(priceText.replaceAll("[^\\d.]", "").trim());
                    allPrices.add(priceValue);
                } catch (NumberFormatException e) {
                    System.out.println("Failed to parse price: " + priceText);
                }
            });
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            WebElement nextBtn = driver.findElement(By.xpath(nextButtonXPath));
            if (!Objects.requireNonNull(nextBtn.getAttribute("class")).contains("opacity-50")) {
                nextBtn.click();
            } else {
                break;
            }
        }
        return allPrices;
    }

    public static List<WebElement> extractMovieTitles(String movieTitlesXPath) {

        List<WebElement> movieTitleElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(movieTitlesXPath)));

        return new ArrayList<>(movieTitleElements);
    }


}
