package com.trycloud.tests.base;

import com.trycloud.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
   public WebDriver driver;

    @BeforeClass
    public void setUpClass() throws IOException {
       // driver = WebDriverFactory.getDriver("chrome");
        Properties properties = new Properties();
        String path = "configuration.properties";
        FileInputStream file = new FileInputStream(path);
        properties.load(file);
        driver = WebDriverFactory.getDriver(properties.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://qa.trycloud.net/index.php/login?clear=1");
        WebElement login = driver.findElement(By.id("user"));

        Random rd = new Random();
        ArrayList<String> userNames = new ArrayList<>();
        userNames.addAll(Arrays.asList(properties.getProperty("login1"), properties.getProperty("login2"), properties.getProperty("login3"), properties.getProperty("login4")));

        login.sendKeys(userNames.get(rd.nextInt(userNames.size())));
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys(properties.getProperty("password"));

        driver.findElement(By.id("submit-form")).click();
    }

    @AfterClass
    public void tearDownClass() {
        driver.close();
    }

}
