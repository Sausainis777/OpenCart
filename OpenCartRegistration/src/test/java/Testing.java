import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Testing {


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
    void teardown() {
        driver.quit();
    }

    @Disabled
    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/Users.csv", numLinesToSkip = 1)
    void registration(String firstName, String lastName, String email, String password) throws InterruptedException {


        driver.get("http://192.168.8.129/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.cssSelector(".list-inline-item:nth-of-type(2) .d-md-inline")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys(firstName);
        driver.findElement(By.id("input-lastname")).sendKeys(lastName);
        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);

        driver.findElement(By.cssSelector("input[name='agree']")).click();
        driver.findElement(By.cssSelector(".btn-primary")).click();
        Thread.sleep(2000);


        assertEquals("Your Account Has Been Created!", driver.findElement(By.cssSelector("div#content > h1")).getText());


    }

    @Test
    void login() throws InterruptedException {

        driver.get("http://192.168.8.129/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.findElement(By.cssSelector(".list-inline-item:nth-of-type(2) .d-md-inline")).click();
        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.id("input-email")).sendKeys("tomas3@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Tomas");
        driver.findElement(By.cssSelector("[action] .btn-primary")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleIs("My Account"));

        assertEquals("My Account", driver.findElement(By.cssSelector("#content h2:nth-of-type(1)")).getText());

        driver.findElement(By.linkText("Edit your account information")).click();
        Thread.sleep(2000);
        assertEquals("Tomas", driver.findElement(By.id("input-firstname")).getAttribute("value"));
        assertEquals("Bambliauskas", driver.findElement(By.id("input-lastname")).getAttribute("value"));
        assertEquals("tomas3@gmail.com", driver.findElement(By.id("input-email")).getAttribute("value"));

        driver.findElement(By.id("input-firstname")).clear();
        driver.findElement(By.id("input-firstname")).sendKeys("Jonas");
        driver.findElement(By.cssSelector(".btn-primary")).click();
        assertEquals("Success: Your account has been successfully updated.", driver.findElement(By.cssSelector(".alert-dismissible")).getText());

        driver.findElement(By.cssSelector("[class] .list-group-item:nth-of-type(13)")).click();
        driver.findElement(By.xpath("//aside[@id='column-right']//a[@href='http://192.168.8.129/en-gb?route=account/login']")).click();

        driver.findElement(By.cssSelector(".list-inline-item:nth-of-type(2) .d-md-inline")).click();
        driver.findElement(By.linkText("Login")).click();

        driver.findElement(By.id("input-email")).sendKeys("tomas3@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("Tomas");
        driver.findElement(By.cssSelector("[action] .btn-primary")).click();

        driver.findElement(By.linkText("Edit your account information")).click();

        Thread.sleep(2000);

        assertEquals("Jonas", driver.findElement(By.id("input-firstname")).getAttribute("value"));
        assertEquals("Bambliauskas", driver.findElement(By.id("input-lastname")).getAttribute("value"));
        assertEquals("tomas3@gmail.com", driver.findElement(By.id("input-email")).getAttribute("value"));


    }


}
