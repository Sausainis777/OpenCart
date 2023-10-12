import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;
    By clickMyAccount = By.cssSelector(".list-inline-item:nth-of-type(2) .d-md-inline");
    By clickRegister = By.linkText("Register");

    public MainPage(WebDriver driver) {
        this.driver = driver;

    }

    public void clickMyAccount(){
        driver.findElement(clickMyAccount).click();
    }
    public void clickRegister(){
        driver.findElement(clickRegister).click();
    }
}