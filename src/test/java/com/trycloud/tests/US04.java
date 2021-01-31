package com.trycloud.tests;

import com.trycloud.tests.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US04 extends TestBase {

    @Test
    public void testCase01() {
        driver.findElement(By.xpath("//ul[@id='appmenu']/li[4]")).click();
        String titleOfTalk = driver.getTitle();

        Assert.assertTrue(titleOfTalk.contains("Talk - Trycloud - QA"));
    }

    @Test
    public void testCase02() {

    }
}
