package com.wrike;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FirstTest {

    public ChromeDriver driver;
    public WebDriverWait wait;
    public String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String fullalphabet = alphabet + alphabet.toLowerCase() + "1234567890";
    public int randomStringLenght=10;
    public StringBuilder sb;
    public Random random;
    public String URL;
    public int randombutton;
    public String str;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        sb = new StringBuilder(randomStringLenght);
        random = new Random();
    }
    @Test


    public void firstTest() {
        driver.get("https://www.wrike.com/");
        driver.findElement(By.xpath("(//header//button[text()='Get started '])[3]")).click();

        for (int i = 0; i < randomStringLenght; i++) {
            sb.append(fullalphabet.charAt(random.nextInt(fullalphabet.length())));
        }

        driver.findElement(By.xpath("//*[@id=\"modal-pro\"]/form//input")).sendKeys(sb.toString() + "wpt@wriketask.qaa");
        driver.findElement(By.xpath("//*[@id=\"modal-pro\"]/form//button")).click();

        wait.until(ExpectedConditions.titleContains("Thank you for choosing Wrike!"));

        URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.equals("https://www.wrike.com/resend-vd/") || URL.equals("https://www.wrike.com/resend/") || URL.equals("https://www.wrike.com/resend-vc/"));

        if (URL.equals("https://www.wrike.com/resend-vd/")) {
            randombutton = random.nextInt(2) + 1;
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[" + randombutton + "])[1]/span")));
            driver.findElement(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[" + randombutton + "])[1]/span")).click();

            randombutton = random.nextInt(3) + 1;
            if (randombutton == 3) {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[3])[1]/span")));
                driver.findElement(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[3])[1]/span")).click();
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[" + randombutton + "])[2]/span")));
                driver.findElement(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[" + randombutton + "])[2]/span")).click();
            }

            randombutton = random.nextInt(2) + 1;
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[1])[3]/span")));
            if (randombutton == 3) {
                for (int i = 0; i < randomStringLenght; i++) {
                    sb.append(fullalphabet.charAt(random.nextInt(fullalphabet.length())));
                }
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[3])[2]/span")));
                driver.findElement(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[3])[2]/span")).click();
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class, 'survey-question__other-input--active')]")));
                driver.findElement(By.xpath("//input[contains(@class, 'survey-question__other-input')]")).sendKeys(sb.toString());
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[" + randombutton + "])[3]/span")));
                driver.findElement(By.xpath("(//div[contains(@class,'survey-question-radio__wrapper')]/label[" + randombutton + "])[3]/span")).click();
            }

            driver.findElement(By.xpath("//form[contains(@class,'survey')]/button")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[(contains(@class,'resend-page__cell--success') and contains(@style, 'display: block'))]")));
            str = driver.findElement(By.xpath("//div[contains(@class,'resend-page__cell--success')]/div/h3")).getText();
        } else {
            randombutton = random.nextInt(2) + 1;
            driver.findElement(By.xpath("(//div[contains(@class, 'radio')])[1]/label[" + randombutton + "]/button")).click();

            randombutton = random.nextInt(5) + 1;
            driver.findElement(By.xpath("(//div[contains(@class, 'radio')])[2]/label[" + randombutton + "]/button")).click();

            randombutton = random.nextInt(2) + 1;
            driver.findElement(By.xpath("(//div[contains(@class, 'radio')])[3]/label[" + randombutton + "]/button")).click();
            if (randombutton == 3) {
                for (int i = 0; i < randomStringLenght; i++) {
                    sb.append(fullalphabet.charAt(random.nextInt(fullalphabet.length())));
                }
                driver.findElement(By.xpath("(//div[contains(@class, 'radio')])[1]/label[" + 3 + "]/button/span/input")).sendKeys(sb.toString());
            }

            driver.findElement(By.xpath("//form[contains(@class,'survey-form')]/button")).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[(contains(@class,'survey-success') and contains(@style, 'display: block'))]")));
            str = driver.findElement(By.xpath("//div[contains(@class,'survey-success')]/h3")).getText();
        }
        Assert.assertEquals(str, "Thanks for helping us out!");
        Assert.assertNotNull(driver.findElement(By.xpath("//div[contains(@class, 'wg-footer__group--social')]")));

        Assert.assertEquals("https://twitter.com/wrike", driver.findElement(By.xpath("(//a[contains(@class, 'wg-footer__social-link')])[1]")).getAttribute("href"));
        String icon = driver.findElement(By.xpath("(//a[contains(@class, 'wg-footer__social-link')])[1]")).getAttribute("innerHTML");
        icon = icon.substring(icon.lastIndexOf("href=")+6, icon.indexOf("\"></use>"));

        System.out.println(icon);
        Assert.assertEquals("/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v2#twitter", icon);

    }
    @After
    public void Close() {
        //driver.quit();
    }
}
