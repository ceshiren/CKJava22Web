package com.ceshiren.hogwarts.wework.app.webview;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class WebViewTest {

    private static AndroidDriver driver;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.appium.android.apis");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ApiDemos");

        capabilities.setCapability("chromedriverExecutableDir", "/Users/seveniruby/projects/chromedriver/chromedrivers");
        capabilities.setCapability("showChromedriverLog", true);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    void webview() throws InterruptedException {
        driver.findElement(By.cssSelector("*[description=Views]")).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"WebView\"))")).click();

        String webviewContext = "WEBVIEW_io.appium.android.apis";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(webDriver -> {
            Set<String> contexts = driver.getContextHandles();
            System.out.println(contexts);
            return contexts.contains(webviewContext);
        });


        driver.context(webviewContext);
        System.out.println(driver.getPageSource());

        System.out.println(driver.getWindowHandles());
        driver.getWindowHandles().forEach(window -> {
            driver.switchTo().window(window);
            System.out.println(driver.getTitle());
            System.out.println(driver.getCurrentUrl());
        });

        driver.findElement(By.name("i_am_a_textbox")).sendKeys("ceshiren.com");
        driver.findElement(By.linkText("i am a link")).click();
        assertThat(
                driver.findElement(By.cssSelector("body")).getText(),
                containsString("I am some other page content")
        );


    }
}
