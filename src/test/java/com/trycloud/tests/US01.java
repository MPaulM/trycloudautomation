package com.trycloud.tests;

import com.trycloud.tests.base.TestBase;
import com.trycloud.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class US01 {
    WebDriver driver;
    Properties properties = new Properties();
    @BeforeMethod
    public void setUpMethod() throws IOException {
        //Properties properties = new Properties();
        String path = "configuration.properties";
        FileInputStream file = new FileInputStream(path);
        properties.load(file);
        driver = WebDriverFactory.getDriver(properties.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testCase02() {
        driver.get(System.getProperty("tryCloudURL"));
        WebElement login = driver.findElement(By.id("user"));

        Random rd = new Random();
        ArrayList<String> userNames = new ArrayList<>();
        userNames.addAll(Arrays.asList(properties.getProperty("login1"), properties.getProperty("login2"), properties.getProperty("login3"), properties.getProperty("login4")));

        login.sendKeys(userNames.get(rd.nextInt(userNames.size())));
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("password");

        driver.findElement(By.id("submit-form")).click();

        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        WebElement confirmationNote = driver.findElement(By.xpath("//"));
        String expectedNote = "Wrong username or password.";
        String expectedTitle = "";


        Assert.assertTrue(confirmationNote.getText().equals(expectedNote));
        Assert.assertTrue(title.equals(expectedTitle));
        Assert.assertTrue(url.equals(System.getProperty("tryCloudURL")));
    }

    @AfterMethod
    public void tearDownMethod() {
        driver.close();

    }
}
