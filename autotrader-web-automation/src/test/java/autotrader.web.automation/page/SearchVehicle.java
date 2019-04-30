package autotrader.web.automation.page;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class SearchVehicle extends BaseTestPage{
    public SearchVehicle(WebDriver driver) {
        super(driver);
    }
    By make = By.id("rfMakes");
    By model = By.id("rfModel");
    By maxPrice = By.id("rfPriceHigh");
    By postalCode = By.id("locationAddress");
    By searchButton = By.id("SearchButton");
    By year = By.id("faceted-parent-Year");
    By minYear = By.id("yearLow");
    By maxYear = By.id("yearHigh");
    By applyYearRangeButton = By.id("applyYear");
    By keywordSearch = By.id("txtKeywords");
    By keywordSearchButton = By.id("keywordSearch");

    WebDriverWait wait = new WebDriverWait(driver, 10);

    Properties pr = new Properties();
    File vehicleSpecs = new File(FilenameUtils.normalize("src/test/resources/vehicle.properties"));
    FileInputStream readVehicleSpecs;
    String manufacturer;
    String mod;
    String price;
    String pcode;
    String yearMin;
    String yearMax;
    String keyword;
    {
        try {
            readVehicleSpecs = new FileInputStream(vehicleSpecs);
            pr.load(readVehicleSpecs);
            manufacturer = pr.getProperty("manufacturer");
            mod = pr.getProperty("model");
            price = pr.getProperty("maxPrice");
            pcode = pr.getProperty("postalCode");
            yearMin = pr.getProperty("minYear");
            yearMax = pr.getProperty("maxYear");
            keyword = pr.getProperty("keyword");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void EnterSearchInfo() {

        wait.until(ExpectedConditions.elementToBeClickable(make));
        driver.findElement(make).click();
        Select vehicleMake = new Select(driver.findElement(make));
        vehicleMake.selectByVisibleText(manufacturer);

        wait.until(ExpectedConditions.elementToBeClickable(model));
        driver.findElement(model).click();
        Select vehicleModel = new Select(driver.findElement(model));
        vehicleModel.selectByVisibleText(mod);

        wait.until(ExpectedConditions.elementToBeClickable(maxPrice));
        driver.findElement(maxPrice).click();
        Select vehicleMaxPrice = new Select(driver.findElement(maxPrice));
        vehicleMaxPrice.selectByVisibleText(price);

        wait.until(ExpectedConditions.elementToBeClickable(postalCode));
        driver.findElement(postalCode).clear();
        driver.findElement(postalCode).sendKeys(pcode);

        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();

    }

    public void AdvanceSearchInfo(){
        wait.until(ExpectedConditions.elementToBeClickable(year));
        driver.findElement(year).click();
        driver.findElement(minYear).click();
        Select vehicleMinYear = new Select(driver.findElement(minYear));
        vehicleMinYear.selectByVisibleText(yearMin);
        driver.findElement(maxYear).click();
        Select vehicleMaxYear = new Select(driver.findElement(maxYear));
        vehicleMaxYear.selectByVisibleText(yearMax);
        wait.until(ExpectedConditions.elementToBeClickable(applyYearRangeButton));
        driver.findElement(applyYearRangeButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(keywordSearch));
        driver.findElement(keywordSearch).clear();
        driver.findElement(keywordSearch).sendKeys(keyword);
        driver.findElement(keywordSearchButton).click();
    }
}
