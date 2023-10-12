import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
    WebDriver driver;
    By inputLoginEmail = By.id("input-email");
    By inputLoginPassWord = By.id("input-password");
    By clickMyAccount = By.cssSelector(".list-inline-item:nth-of-type(2) .d-md-inline");
    By clickLogin = By.linkText("Login");

    By clickToLogin = By.cssSelector("[action] .btn-primary");

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    public void setLoginEmail(String email){
        driver.findElement(inputLoginEmail).sendKeys(email);
    }
    public void setLoginPassWord(String password){
        driver.findElement(inputLoginPassWord).sendKeys(password);
    }
    public void  clickMyAccount(){
        driver.findElement(clickMyAccount).click();
    }

    public void clickLogin(){
        driver.findElement(clickLogin).click();
    }

    public void clickToLogin(){
        driver.findElement(clickToLogin).click();
    }


    }
