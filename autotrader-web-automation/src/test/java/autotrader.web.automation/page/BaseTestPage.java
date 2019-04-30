package autotrader.web.automation.page;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTestPage {
    protected WebDriver driver;

    protected long timeOut = 30;

    public BaseTestPage(WebDriver driver) {
        super();
        this.driver = driver;
    }


    /**
     * @return the driver
     */
    public WebDriver getDriver() {
        return driver;
    }


    /**
     * @return
     */
    public String getTitle() {
        return driver.getTitle();
    }


    /**
     * @param text
     * @return
     */
    public boolean hasLinkWithText(String text) {
        if (driver.findElements(By.linkText(text)).size() != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Takes a screenshot of the current page
     *
     * @param filename file will be named using the format: yy-MM-dd HH-mm-ss filename
     */
    public void takeScreenshot(String filename) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String outputPath = dateFormat.format(new Date()) + " " + filename + ".png";
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File(outputPath));
        } catch (IOException e) {
            System.out.println("Error creating screenshot.");
            e.printStackTrace();
        }
    }


}



