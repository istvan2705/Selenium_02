import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailSendingTest {

    private static final Logger logger = LogManager.getLogger(EmailSendingTest.class);
    public static final String URL = "http://mail.google.com";

    public static void main(String[] args) {

        try (InputStream input = new FileInputStream("src\\main\\resources\\user.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String user = prop.getProperty("email");
            String password = prop.getProperty("password");
            String customerEmail = prop.getProperty("customer_email");

            WebDriver driver = DriverManager.getDriver();
            driver.get(URL);

            WebElement emailField = driver.findElement(By.id("identifierId"));
            emailField.sendKeys(user);

            WebElement enterButton = driver.findElement(By.xpath("//span[@class='RveJvd snByac']"));
            enterButton.click();

            WebElement passwordField = driver.findElement(By.name("password"));
            WebDriverWait wait = new WebDriverWait(driver, 3);
            wait.until(ExpectedConditions.elementToBeClickable(passwordField));
            passwordField.sendKeys(password);

            WebElement nextButton = driver.findElement(By.cssSelector("#passwordNext"));
            nextButton.click();

            WebElement composeButton = driver.findElement(By.className("aic"));
            composeButton.click();

            WebElement toWhom = driver.findElement(By.name("to"));
            toWhom.sendKeys(customerEmail);

            WebElement subject = driver.findElement(By.className("aoT"));
            subject.sendKeys("Test");

            WebElement textField = driver.findElement(By.cssSelector(".Ar.Au div"));
            textField.sendKeys("Hello friend");

            WebElement sendEmailButton = driver.findElement(By.cssSelector(".dC div"));
            sendEmailButton.click();
            logger.info("The email was sent");
            driver.quit();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
