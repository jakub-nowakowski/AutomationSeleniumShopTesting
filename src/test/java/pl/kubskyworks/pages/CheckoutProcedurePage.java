package pl.kubskyworks.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutProcedurePage {

    private static WebDriver driver;
    @FindBy(name = "confirm-addresses")
    WebElement addConfirm;
    @FindBy(name = "confirmDeliveryOption")
    WebElement delivConfirm;
    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    WebElement termsCondition;
    @FindBy(id = "payment-confirmation")
    WebElement paymentAccept;

    public CheckoutProcedurePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void addressConfirmation(){
        addConfirm.click();
    }

    public void shipmentType(String shipment) {
        String deliveryLocator = "//label[contains(.,'" + shipment + "')]";
        driver.findElement(By.xpath(deliveryLocator)).click();
        delivConfirm.click();
    }
    public void paymentType(String payment) {
        String paymentLocator = "//label[contains(.,'" + payment + "')]";
        driver.findElement(By.xpath(paymentLocator)).click();
        termsCondition.click();
        paymentAccept.click();
    }

}
