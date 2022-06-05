package pl.kubskyworks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.kubskyworks.pages.LoginPage;
import pl.kubskyworks.pages.NewAddressPage;
import pl.kubskyworks.pages.PrintScreenMethod;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AddAddressSteps {

    private WebDriver driver;
    LoginPage loginPage;
    NewAddressPage addAddress;
    PrintScreenMethod takesScreen;

    @Given("I'm on mystore-testlab.coderslab.pl page")
    public void iMOnMystoreTestlabCoderslabPlPage() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://mystore-testlab.coderslab.pl/");
    }

    @When("^I sing in as a registered user with (.*), (.*)$")
    public void iSingInAsARegisteredUserWithEMailPassword(String email, String password) {
        loginPage = new LoginPage(driver);
        loginPage.loginAs(email, password);
    }

    @And("^I add new address and confirm it with (.*), (.*), (.*), (.*), (.*), (.*)$")
    public void iAddNewAddressAndConfirmItWithAliasAddressCityZipPostalCodeCountryPhone(String alias, String address, String city,
                                                                                        String zipPostalCode, String country, String phone) {
        driver.findElement(By.linkText("Addresses")).click();
        driver.findElement(By.partialLinkText("Create new address")).click();
        addAddress = new NewAddressPage(driver);
        addAddress.addNewAddress(alias, address, city, zipPostalCode, country, phone);
    }

    @Then("^I compare the address details with (.*), (.*), (.*), (.*), (.*), (.*), (.*)$")
    public void iCompareTheAddressDetailsWithAliasFullNameAddressCityZipPostalCodeCountryPhone(String alias, String fullName, String address, String city,
                                                                                               String zipPostalCode, String country, String phone) {
        List<WebElement> addressesList = driver.findElements(By.className("address-body"));
        String valMsg2 = addressesList.get(1).getText();
        Assert.assertEquals(String.join("", alias + "\n", fullName + "\n",
                address + "\n", city + "\n", zipPostalCode + "\n", country + "\n", phone), valMsg2);
    }

    @And("I delete the address and see success deleted alert {string}")
    public void iDeleteTheAddressAndSeeSuccessDeletedAlert(String expectedMsg) {
        driver.findElement(By.xpath("//a[@data-link-action='delete-address']")).click();
        String valMsg = driver.findElement(By.className("alert-success")).getText();
        Assert.assertEquals(expectedMsg, valMsg);
    }


    @Then("I take screenshot")
    public void iTakeScreenshot() throws IOException {
        takesScreen = new PrintScreenMethod(driver);
        takesScreen.takeScreenShot();
    }

    @And("I close the browser")
    public void iCloseTheBrowser() {
        driver.quit();
    }
}
