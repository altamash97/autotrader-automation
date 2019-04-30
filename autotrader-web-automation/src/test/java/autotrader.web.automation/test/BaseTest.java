package autotrader.web.automation.test;

import junit.framework.TestCase;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class BaseTest extends TestCase {

    public static WebDriver driver;
    public static String baseurl;
    public String seleniumUrl;
    public String platform;

    //@BeforeTest
    public void SetUp() throws IOException {
        Properties prop = new Properties();
        File f = new File(FilenameUtils.normalize("src/test/resources/config.properties"));

        // wiring the logger properties for log4j
        PropertyConfigurator.configure("src/test/resources/log4j.properties");


        FileInputStream ip = new FileInputStream(f);
        prop.load(ip);
        System.out.println(prop.getProperty("browser"));

        String browser = prop.getProperty("browser");
        // Setting up driver

        baseurl = prop.getProperty("baseurl");
        seleniumUrl = prop.getProperty("seleniumUrl");
        System.out.println(seleniumUrl);

        platform = prop.getProperty("platform");
        System.out.println(platform);

        // start capabilities setup
        DesiredCapabilities capability;
        if (browser != null)
        {
            System.out.println("System property browser provided in .properties file: " + browser);

            // Firefox
            if ("firefox".equalsIgnoreCase(browser))
            {
                System.out.println("starting firefox");
                capability = DesiredCapabilities.firefox();
            }

            else if ("chrome".equalsIgnoreCase(browser))
            {
                System.out.println("starting chrome");
                capability = DesiredCapabilities.chrome();
            }
            else
            {
                System.out.println("System property browser provided, but did not match anything, setting default browser to chrome");
                capability = DesiredCapabilities.chrome();
            }
        }
        else
        {
            System.out.println("System property browser not provided, setting default browser to chrome");
            capability = DesiredCapabilities.chrome();
        }

        if (prop.getProperty("platform") != null)
        {
            String platform = prop.getProperty("platform");
            System.out.println("System property platform provided: " + platform);

            // macos
            if ("macos".equalsIgnoreCase(platform))
            {
                capability.setPlatform(Platform.MAC);
            }
            // windows
            else if ("windows".equalsIgnoreCase(platform))
            {
                capability.setPlatform(Platform.WINDOWS);
            }
            else
            {
                System.out.println("System property platform not provided, setting default platform to any");
                capability.setPlatform(Platform.ANY);
            }
        }
        //end capabilities setup


        if (prop.getProperty("seleniumUrl") != null )
        {
            System.out.println("System property seleniumUrl provided:" + prop.getProperty("seleniumUrl"));
            seleniumUrl = prop.getProperty("seleniumUrl");
        }
        else
        {
            System.out.println("System property seleniumUrl NOT provided, please provide it in config.properties");
        }

        driver = new RemoteWebDriver(new URL(seleniumUrl), capability);



        if (prop.getProperty("browser").equalsIgnoreCase("chrome")||prop.getProperty("browser").equalsIgnoreCase("firefox")){
            driver.get(baseurl);

        }

    }

//    @AfterTest
//    public void tearDown() {
//        driver.quit();
//    }

    public static WebDriver getdriver(){
        return driver;
    }
}