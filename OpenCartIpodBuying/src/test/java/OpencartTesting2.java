import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpencartTesting2 {

    WebDriver driver;

    @BeforeAll
    static void driverSetup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void driver() {

        driver = new ChromeDriver();
    }

    @AfterEach
    void over(){

        driver.quit();
    }

@Disabled
    @Test
    void productDisplayed() {
        driver.get("http://192.168.89.27/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector(".dropdown:nth-of-type(8) [data-bs-toggle]")).click();
        driver.findElement(By.cssSelector(".dropdown-menu.show > .see-all")).click();

        driver.findElement(By.cssSelector("button#button-list")).click();


        WebElement iPodClassic = driver.findElement(By.linkText("iPod Classic"));
        assertTrue(iPodClassic.isDisplayed());

        iPodClassic.click();

        driver.findElement(By.cssSelector(".btn-group .fa-heart")).click();
        assertEquals("You must login or create an account to save iPod Classic to your wish list!", driver.findElement(By.cssSelector(".alert-dismissible")).getText());
        driver.navigate().refresh();
        driver.findElement(By.cssSelector("#button-cart")).click();
        assertEquals("Success: You have added iPod Classic to your shopping cart!", driver.findElement(By.cssSelector("div#alert > .alert.alert-dismissible.alert-success")).getText());
        driver.findElement(By.cssSelector(".btn-inverse")).click();
        assertTrue(driver.findElement(By.xpath("//div[@id='header-cart']//ul[@class='dropdown-menu dropdown-menu-end p-2 show']")).isDisplayed());
    }
        @ParameterizedTest
                @CsvFileSource(files = "src/main/resources/OpenCart.csv", numLinesToSkip = 1)
        void allMp3Players(String product) throws InterruptedException {

            driver.get("http://192.168.89.27/");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.findElement(By.cssSelector(".dropdown:nth-of-type(8) [data-bs-toggle]")).click();
            driver.findElement(By.cssSelector(".dropdown-menu.show > .see-all")).click();

            driver.findElement(By.cssSelector("button#button-list")).click();


            WebElement iPodClassic = driver.findElement(By.linkText(product));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", iPodClassic);
            Thread.sleep(2000);
            assertTrue(iPodClassic.isDisplayed());
            iPodClassic.click();

            driver.findElement(By.cssSelector(".btn-group .fa-heart")).click();
            assertEquals("You must login or create an account to save " + product +  " to your wish list!", driver.findElement(By.cssSelector(".alert-dismissible")).getText());
            driver.navigate().refresh();
            driver.findElement(By.cssSelector("#button-cart")).click();
            assertEquals("Success: You have added " + product + " to your shopping cart!", driver.findElement(By.cssSelector("div#alert > .alert.alert-dismissible.alert-success")).getText());
            driver.findElement(By.cssSelector(".btn-inverse")).click();
            assertTrue(driver.findElement(By.xpath("//div[@id='header-cart']//ul[@class='dropdown-menu dropdown-menu-end p-2 show']")).isDisplayed());

    }

}