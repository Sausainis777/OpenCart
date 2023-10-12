import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Registration {

    WebDriver driver;
    By inputFirstName = By.id("input-firstname");
    By inputLastName = By.id("input-lastname");
    By inputEmail = By.id("input-email");
    By inputPassword = By.id("input-password");
    By clickAgree = By.cssSelector("input[name='agree']");
    By clickToRegister = By.cssSelector(".btn-primary");

    public Registration(WebDriver driver){
        this.driver = driver;
    }

    public void setFirstName(String firstName){
        driver.findElement(inputFirstName).sendKeys(firstName);
    }
    public void setLastName(String lastName){
        driver.findElement(inputLastName).sendKeys(lastName);
    }
    public void setEmail(String email){
        driver.findElement(inputEmail).sendKeys(email);
    }
    public void setPassword(String password){
        driver.findElement(inputPassword).sendKeys(password);
    }
    public void agreeButtonClick(){
        driver.findElement(clickAgree).click();
    }
    public void registerButtonClick(){
        driver.findElement(clickToRegister).click();
    }

}
