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
        driver.get("http://192.168.8.129/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
    public String generateEmail(){
        String emailAddress = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        while (emailAddress.length() < 5) {
            int character = (int) (Math.random() * 26);
            emailAddress += alphabet.substring(character, character + 1);
            emailAddress += Integer.valueOf((int) (Math.random() * 99)).toString();
            emailAddress += "@gmail.com" ;}
        return emailAddress;

    }


    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/Users.csv", numLinesToSkip = 1)
    void registration(String firstName, String lastName, String email, String password) throws InterruptedException {


        Registration page = new Registration(driver);
        MainPage mainPage = new MainPage(driver);



        mainPage.clickMyAccount();
        mainPage.clickRegister();

        page.setFirstName(firstName);
        page.setLastName(lastName);
        page.setEmail(generateEmail());
        page.setPassword(password);
        page.agreeButtonClick();
        page.registerButtonClick();

        Thread.sleep(2000);

        assertEquals("Your Account Has Been Created!", driver.findElement(By.cssSelector("div#content > h1")).getText());


    }

    @Disabled
    @Test
    void login() throws InterruptedException {

        Registration page = new Registration(driver);
        Login loginpage = new Login(driver);

        loginpage.clickMyAccount();
        loginpage.clickLogin();

        loginpage.setLoginEmail("tomas3@gmail.com");
        loginpage.setLoginPassWord("Tomas");
        loginpage.clickToLogin();


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

        page.setEmail("tomas3@gmail.com");
        page.setPassword("Tomas");
        driver.findElement(By.cssSelector("[action] .btn-primary")).click();

        driver.findElement(By.linkText("Edit your account information")).click();

        Thread.sleep(2000);

        assertEquals("Jonas", driver.findElement(By.id("input-firstname")).getAttribute("value"));
        assertEquals("Bambliauskas", driver.findElement(By.id("input-lastname")).getAttribute("value"));
        assertEquals("tomas3@gmail.com", driver.findElement(By.id("input-email")).getAttribute("value"));


    }


}
