package pl.kubskyworks.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class PrintScreenMethod {

    private static WebDriver driver;

    public PrintScreenMethod(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void takeScreenShot() throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,195)", "");
        Date date = new Date();
        String nameDate = date.toString().replace(" ", "-").replace(":", "-");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile,
                new File("src/test/screenshots/" + nameDate + ".jpg"));
    }
}
