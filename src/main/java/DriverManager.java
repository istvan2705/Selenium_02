import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static final String DRIVER_NAME = "webdriver.chrome.driver";
    public static final String PATH_TO_CHROME_DRIVER = "src\\main\\resources\\chromedriver.exe";
    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            System.setProperty(DRIVER_NAME, PATH_TO_CHROME_DRIVER);
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        return driver;
    }
}






