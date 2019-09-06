package com.wrike;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {

    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
    }
    @Test


    public void firstTest() {


        driver.get("https://www.wrike.com/");



    }
    @After
    public void Close() {
        driver.quit();
    }
}
