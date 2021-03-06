package pl.kubskyworks.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ParameterizationPage {

    private static WebDriver driver;
    @FindBy(id = "group_1")
    WebElement sizeSection;
    @FindBy(name = "product-quantity-spin")
    WebElement qtySection;
    @FindBy(className = "add")
    WebElement addButton;
    @FindBy(partialLinkText = "PROCEED TO CHECKOUT")
    WebElement proceedButton;

    public ParameterizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void iChooseTheSize(String size) {
        try {
            WebElement selectElement = sizeSection;
            Select selectObject = new Select(selectElement);
            selectObject.selectByVisibleText(size);
        } catch (NoSuchElementException ex) {
            System.out.println("You cannot parameterize the size for this item");
        }
    }

    public void iChooseQuantity(int quantity) {
        qtySection.click();
        qtySection.sendKeys(Keys.BACK_SPACE);
        qtySection.sendKeys(String.valueOf(quantity));
    }

    public void iChooseColour(String colour) {
        String colourLocator = "//label[contains(.,'" + colour + "')]";
        try {
            driver.findElement(By.xpath(colourLocator)).click();
        } catch (NoSuchElementException ex) {
            System.out.println("You cannot parameterize the colour for this item");
        }
    }

    public void addClick() {
        addButton.click();
    }

    public void proceedBtn() {
        proceedButton.click();
    }
}
