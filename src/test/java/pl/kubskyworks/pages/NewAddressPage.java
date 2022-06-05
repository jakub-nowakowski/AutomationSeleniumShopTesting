package pl.kubskyworks.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class NewAddressPage {

    private static WebDriver driver;
    @FindBy(name = "alias")
    WebElement us_alias;
    @FindBy(name = "address1")
    WebElement us_address;
    @FindBy(name = "city")
    WebElement us_city;
    @FindBy(name = "postcode")
    WebElement us_postcode;
    @FindBy(name = "id_country")
    WebElement us_id_country;
    @FindBy(name = "phone")
    WebElement us_phone;
    @FindBy(name = "submitAddress")
    WebElement submitAddressButton;

    public NewAddressPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addNewAddress(String alias, String address, String city,
                              String zipPostalCode, String country, String phone) {
        us_alias.sendKeys(alias);
        us_address.sendKeys(address);
        us_city.sendKeys(city);
        us_postcode.sendKeys(zipPostalCode);
        WebElement selectElement = us_id_country;
        Select selectObject = new Select(selectElement);
        selectObject.selectByVisibleText(country);
        us_phone.sendKeys(phone);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        submitAddressButton.submit();
    }
}
