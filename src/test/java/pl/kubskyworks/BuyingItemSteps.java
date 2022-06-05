package pl.kubskyworks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.kubskyworks.pages.CheckoutProcedurePage;
import pl.kubskyworks.pages.LoginPage;
import pl.kubskyworks.pages.ParameterizationPage;
import pl.kubskyworks.pages.PrintScreenMethod;

import java.io.IOException;
import java.time.Duration;

public class BuyingItemSteps {

    private WebDriver driver;
    LoginPage loginPage;
    ParameterizationPage parameterPage;
    CheckoutProcedurePage checkoutPage;
    PrintScreenMethod printScreen;

    @Given("User's on mystore-testlab.coderslab.pl page")
    public void iMOnMystoreTestlabCoderslabPlPage() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://mystore-testlab.coderslab.pl/");
    }

    @When("^User logs-in with (.*), (.*)$")
    public void userLogsIn(String email, String password) {
        loginPage = new LoginPage(driver);
        loginPage.loginAs(email, password);
    }

    @And("^User adds new (.*) too cart$")
    public void userAddsNewItemTooCart(String item) {
        driver.findElement(By.id("_desktop_logo")).click();
        driver.findElement(By.partialLinkText(item)).click();
    }

    @Then("^User checks the (.*)$")
    public void userChecksTheDiscount(String discount) {
        String discountVisible = driver.findElement(By.className("discount-percentage")).getText();
        Assert.assertEquals(discount, discountVisible);
    }


    @And("^User parametrize it with (.*), (.*), (.*)$")
    public void userParametrizeIt(String size, int quantity, String colour) {
        parameterPage = new ParameterizationPage(driver);
        parameterPage.iChooseQuantity(quantity);
        parameterPage.iChooseTheSize(size);
        parameterPage.iChooseColour(colour);
        parameterPage.addClick();
    }

    @Then("User proceeds to checkout")
    public void userProceedsToCheckout() {
        driver.findElement(By.partialLinkText("PROCEED TO CHECKOUT")).click();
        driver.findElement(By.partialLinkText("PROCEED TO CHECKOUT")).click();
    }

    @And("^User chooses delivery, (.*) and (.*) methods$")
    public void userChoosesDeliveryShipmentAndPaymentMethods(String delivery, String payment) {
        checkoutPage = new CheckoutProcedurePage(driver);
        checkoutPage.addressConfirmation();
        checkoutPage.shipmentType(delivery);
        checkoutPage.paymentType(payment);
    }

    @Then("User takes screenshot of order confirmation")
    public void userTakesScreenshotOfOrderConfirmation() throws IOException {
        printScreen = new PrintScreenMethod(driver);
        printScreen.takeScreenShot();
    }

    @And("User closes browser")
    public void userClosesBrowser() {
        driver.quit();
    }
}

