package testsuite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials(){
        // Find log in link and click on login link
        sendTextToElement(By.name("username"),"tomsmith");
        // Find the password field element
        sendTextToElement(By.name("password"),"SuperSecretPassword!");
// Find the login button and click on it
        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        String expectedMessage = "Secure Area";
        String actualMessage = getTextFromElement(By.xpath("//h2[text() = ' Secure Area']"));
    }
    @Test
    public void verifyTheUsernameErrorMessage() {
        // Find log in link and click on login link
        sendTextToElement(By.name("username"),"Tomsmith");
        // Find the password field element
        sendTextToElement(By.name("password"), "SuperSecretPassword!");

      // Find the login button and click on it
        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        String expectedMessage = "Your username is invalid!";
        String actualMessage = getTextFromElement(By.xpath("//div[@id='flash-messages']")).substring(0,25);
        Assert.assertEquals(" Username not valid", expectedMessage, actualMessage);
    }

       @Test
    public void verifyThePasswordErrorMessage(){
           // Find log in link and click on login link
           sendTextToElement(By.name("username"),"tomsmith");
           // Find the password field element
        sendTextToElement(By.name("password"),"tomsmith");
        // Find the login button and click on it
        clickOnElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']"));
        String expectedMessage = "Your password is invalid!";

        String actualMessage = getTextFromElement(By.xpath("//div[contains(@class,'flash error')]")).substring(0,25);
           Assert.assertEquals(" Password not valid", expectedMessage, actualMessage);
    }
    public void closeBrowser(){
        driver.quit();
    }
}
