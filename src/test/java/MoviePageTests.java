import baseConfig.CrossBrowserTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static data.Constants.*;
import static util.UtilMethods.*;

public class MoviePageTests extends CrossBrowserTesting {
    @Test
    public void MoviePageTest() {
        driver.get(SWOOP_PAGE);

        WebElement cookieAccept = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(COOKIE_ACCEPT_XPATH)));
        cookieAccept.click();

        WebElement movies = driver.findElement(By.xpath(MOVIES_SECTION_XPATH));
        movies.click();

        List<WebElement> movieTitles = extractMovieTitles(ACTUAL_MOVIES_XPATH);
        js.executeScript(JS_CLICK, movieTitles.getFirst());

        String firstMovieTitle = movieTitles.getFirst().getText();

        WebElement caveaEastPoint = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CAVEA_EAST_POINT_XPATH)));
        String caveaEastPointText = caveaEastPoint.getText();

        wait.until(ExpectedConditions.visibilityOf(caveaEastPoint));
        scrollToElement(caveaEastPoint);

        List<WebElement> timeAndDate = driver.findElements(By.xpath(DATE_TIME));

        String time = timeAndDate.getFirst().getText();
        String date = timeAndDate.getLast().getText().replaceAll("\\b0([1-9])\\b", "$1");

        try {
            WebElement lastElOfCavea = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LAST_SESSION_OF_CAVEA_XPATH)));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LAST_SESSION_OF_CAVEA_XPATH)));
            lastElOfCavea.click();

        } catch (Exception e) {
            List<WebElement> weekdays = driver.findElements(By.xpath(WEEKDAYS_XPATH));

            boolean lastElFound = false;
            for (WebElement weekday : weekdays) {

                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LAST_SESSION_OF_CAVEA_XPATH)));
                js.executeScript(JS_CLICK, weekday);
                try {
                    WebElement lastElOFCavea = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LAST_SESSION_OF_CAVEA_XPATH)));
                    lastElFound = true;
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LAST_SESSION_OF_CAVEA_XPATH)));
                    js.executeScript(JS_CLICK, lastElOFCavea);
                    break;
                } catch (Exception ex) {
                    System.out.println("Last element not found after clicking " + weekday.getText());
                    ex.printStackTrace();
                }
            }
            if (!lastElFound) {
                System.out.println(LAST_EL_OF_CAVER_ERR_MSG);
            }
        }

        WebElement cinemaPopupName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CINEMA_NAME_IN_POPUP_XPATH)));
        WebElement moviePopupName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MOVIE_NAME_IN_POPUP_XPATH)));
        WebElement dateAndTimePopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DATE_AND_TIME_POPUP_XPATH)));

        String[] dateAndTimePopupString = dateAndTimePopup.getText().split(",");

        Assert.assertEquals(time.trim(), dateAndTimePopupString[1].trim(), TIMES_ERR_MSG);
        Assert.assertTrue(dateAndTimePopupString[0].contains(date), DATES_ERR_MSG);
        Assert.assertEquals(caveaEastPointText, cinemaPopupName.getText(), CINEMA_ERR_MSG);
        Assert.assertEquals(firstMovieTitle, moviePopupName.getText(), MOVIE_TITLE_ERR_MSG);

        WebElement emptySit = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(EMPTY_SEAT_CSS_SEL)));
        WebElement legendChartEmptySeat = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(LEGEND_CHART_XPATH)));

        String emptySeatColor = emptySit.getCssValue("background-color");
        String legendChartColor = legendChartEmptySeat.getCssValue("background-color");

//        Assert.assertEquals(emptySeatColor, legendChartColor, "Empty seat and, legend chart colors doesn't match");

        emptySit.click();

        WebElement accCreation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ACCOUNT_CREATION_XPATH)));

        accCreation.click();

        // Registration Form
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
        WebElement repeatPasswordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("PasswordRetype")));
        WebElement genderMale = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MALE_RADIO_BTN_XPATH)));
        WebElement firstNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
        WebElement lastNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("surname")));
        WebElement chooseBirthYear = driver.findElement(By.xpath(CHOOSE_BIRTHYEAR_DROPDOWN_XPATH));
        js.executeScript(JS_CLICK, chooseBirthYear);
        chooseBirthYear.click();
        WebElement myBirthYearSelected = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MY_BIRTHYEAR_XPATH)));
        myBirthYearSelected.click();
        WebElement phoneNumberField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Phone")));

        emailField.sendKeys("wrong email");
        passwordField.sendKeys("P@ssw0rd123");
        repeatPasswordField.sendKeys("P@ssw0rd123");
        firstNameField.sendKeys("Shota");
        lastNameField.sendKeys("Nanishvili");
        phoneNumberField.sendKeys("599870919");
        genderMale.click();

        WebElement errorFirstNameMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(EMAIL_ERROR_MESSAGE_XPATH)));

        Assert.assertTrue(errorFirstNameMessage.isDisplayed(), INVALID_EMAIL_ERR);
    }
}
