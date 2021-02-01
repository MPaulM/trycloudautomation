package com.trycloud.tests;

import com.github.javafaker.Faker;
import com.trycloud.utilities.ConfigurationReader;
import com.trycloud.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class US01 {
    WebDriver driver;
    @BeforeMethod
    public void setUpMethod() {

        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperties("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testCase02() {
        driver.get(ConfigurationReader.getProperties("tryCloudURL"));
        WebElement login = driver.findElement(By.id("user"));

        Random rd = new Random();
        ArrayList<String> userNames = new ArrayList<>();
        userNames.addAll(Arrays.asList(ConfigurationReader.getProperties("login1"), ConfigurationReader.getProperties("login2"), ConfigurationReader.getProperties("login3"), ConfigurationReader.getProperties("login4")));

        login.sendKeys(userNames.get(rd.nextInt(userNames.size())));
        WebElement password = driver.findElement(By.id("password"));
        Faker faker = new Faker();
        password.sendKeys(faker.internet().password());

        driver.findElement(By.id("submit-form")).click();

        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        WebElement confirmationNote = driver.findElement(By.xpath("//div//p[@class='warning wrongPasswordMsg']"));
        String expectedNote = "Wrong username or password.";
        String expectedTitle = "Trycloud - QA";


        Assert.assertTrue(confirmationNote.getText().equals(expectedNote));
        Assert.assertTrue(title.contains(expectedTitle));
        Assert.assertTrue(url.equals(ConfigurationReader.getProperties("tryCloudURL")));
    }

    @AfterMethod
    public void tearDownMethod() {
        driver.close();

    }
}
