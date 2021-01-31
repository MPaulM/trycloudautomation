package com.trycloud.tests.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class TestBase {
    WebDriver driver;

    @BeforeMethod
    public void setUpMethod() {

    }

    @AfterMethod
    public void tearDownMethod() {
        driver.close();
    }

}
