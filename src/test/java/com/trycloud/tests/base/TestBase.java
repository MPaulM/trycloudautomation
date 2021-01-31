package com.trycloud.tests.base;

import com.trycloud.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    WebDriver driver;

    @BeforeMethod
    public void setUpMethod() throws IOException {
        driver = WebDriverFactory.getDriver("chrome");
//        Properties properties = new Properties();
//        String path = "configuration.properties";
//        FileInputStream file = new FileInputStream(path);
//        properties.load(file);
//        driver = WebDriverFactory.getDriver(properties.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDownMethod() {
        driver.close();
    }

}
