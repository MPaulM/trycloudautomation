package com.trycloud.tests.base;

import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
   public WebDriver driver;
   public Random rd = new Random();
    @BeforeClass
    public void setUpClass() throws IOException {
       // driver = WebDriverFactory.getDriver("chrome");

        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperties("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(ConfigurationReader.getProperties("tryCloudURL"));
        WebElement login = driver.findElement(By.id("user"));

        //Random rd = new Random();
        ArrayList<String> userNames = new ArrayList<>();
        userNames.addAll(Arrays.asList(ConfigurationReader.getProperties("login1"), ConfigurationReader.getProperties("login2"), ConfigurationReader.getProperties("login3"), ConfigurationReader.getProperties("login4")));

        login.sendKeys(userNames.get(rd.nextInt(userNames.size())));
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(ConfigurationReader.getProperties("password"));

        driver.findElement(By.id("submit-form")).click();
        String expectedPageUrl = "http://qa.trycloud.net/index.php/apps/files/?dir=/";
        String actualPageUrl = driver.getCurrentUrl();
        Assert.assertTrue(expectedPageUrl.contains(actualPageUrl));
    }

    @AfterClass
    public void tearDownClass() {
        driver.close();
    }

}
