package pl.kubskyworks.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ParameterizationPage {

    private static WebDriver driver;
    @FindBy(id = "group_1")
    WebElement sizeSection;
    @FindBy(name = "qty")
    WebElement qtySection;
    @FindBy(className = "add")
    WebElement addButton;

    public ParameterizationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void iChooseTheSize(String size) {
        WebElement selectElement = sizeSection;
        Select selectObject = new Select(selectElement);
        selectObject.selectByVisibleText(size);
    }

    public void iChooseQuantity(int quantity) {
        qtySection.clear();
        qtySection.sendKeys(String.valueOf(quantity));
    }

    public void iChooseColour(String colour) {
        String colourLocator = "//label[contains(.,'" + colour + "')]";
        driver.findElement(By.xpath(colourLocator)).click();
    }

    public void addClick() {
        addButton.click();
    }
}
