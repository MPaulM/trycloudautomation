package com.trycloud.tests;

import com.github.javafaker.Faker;
import com.trycloud.tests.base.TestBase;
import com.trycloud.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
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
        driver.findElement(By.xpath("//input[@class='app-navigation-search__input']")).sendKeys(Keys.SPACE);
        List<WebElement> userList = driver.findElements(By.xpath("//ul[@class='contacts-list']//div[@class='acli__content__line-one']"));
        System.out.println(userList.size());
        Random rd = new Random();
        driver.findElement(By.xpath("//input[@class='app-navigation-search__input']")).sendKeys(Keys.BACK_SPACE, userList.get(rd.nextInt(userList.size())).getText());
        driver.findElement(By.xpath("//a[@class='acli']")).click();
        WebElement msgInput = driver.findElement(By.xpath("//div[@class='new-message-form__advancedinput']"));
        Faker faker = new Faker();
        msgInput.sendKeys(faker.ancient().hero());
       // msgInput.click();
        driver.findElement(By.cssSelector("button[aria-label='Send message']")).click();

        BrowserUtils.sleep(5);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='message__main__text']")).isDisplayed());

        //Talk updates ✅- You can now change your camera and microphone while being in a call23
    }
}
