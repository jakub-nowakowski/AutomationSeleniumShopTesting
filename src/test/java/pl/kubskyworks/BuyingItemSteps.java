package pl.kubskyworks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    @Then("^User parametrize it with (.*), (.*), (.*)$")
    public void userParametrizeIt(String size, int quantity, String colour) {
        parameterPage = new ParameterizationPage(driver);
        parameterPage.iChooseTheSize(size);
        parameterPage.iChooseColour(colour);
        parameterPage.addClick();
        parameterPage.proceedBtn();
        parameterPage.iChooseQuantity(quantity);
    }

    @And("User checks the discount and proceeds to checkout")
    public void userProceedsToCheckout() {
        try {
            String regPrice = driver.findElement(By.className("regular-price")).getText();
            String reg = regPrice.replaceAll("[^0-9]", "");

            String disPrice = driver.findElement(By.className("price")).getText();
            String dis = disPrice.replaceAll("[^0-9]", "");
            double a = Integer.parseInt(dis);
            double b = Integer.parseInt(reg);
            double c = 100 * (a / b);
            double d = 100 - c;
            int e = (int) d;

            System.out.println("Discount: " + e + "%");
        } catch (NoSuchElementException ex) {
            System.out.println("No discount for this item");
        }
        driver.findElement(By.partialLinkText("PROCEED TO CHECKOUT")).click();
    }

    @Then("^User chooses delivery, (.*) and (.*) methods$")
    public void userChoosesDeliveryShipmentAndPaymentMethods(String delivery, String payment) {
        checkoutPage = new CheckoutProcedurePage(driver);
        checkoutPage.addressConfirmation();
        checkoutPage.shipmentType(delivery);
        checkoutPage.paymentType(payment);
    }

    @And("User takes screenshot of order confirmation")
    public void userTakesScreenshotOfOrderConfirmation() throws IOException {
        printScreen = new PrintScreenMethod(driver);
        printScreen.takeScreenShot();
    }

    @Then("User closes browser")
    public void userClosesBrowser() {
        driver.quit();
    }

}

