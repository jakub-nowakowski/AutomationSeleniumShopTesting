package pl.kubskyworks.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private static WebDriver driver;
    @FindBy(partialLinkText = "Sign in")
    WebElement signIn;
    @FindBy(name = "email")
    WebElement us_email;
    @FindBy(name = "password")
    WebElement us_password;
    @FindBy(id = "submit-login")
    WebElement sub_login;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginAs(String email, String password) {
        signIn.click();
        us_email.sendKeys(email);
        us_password.sendKeys(password);
        sub_login.click();
    }
}
