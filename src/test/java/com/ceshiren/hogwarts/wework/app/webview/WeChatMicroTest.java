package com.ceshiren.hogwarts.wework.app.webview;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class WeChatMicroTest {

    private static AndroidDriver driver;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.tencent.mm");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ui.LauncherUI");

        capabilities.setCapability("chromedriverExecutableDir", "/Users/seveniruby/projects/chromedriver/chromedrivers");
        capabilities.setCapability(
                "chromedriverExecutable",
                "/Users/seveniruby/projects/chromedriver/chromedrivers/chromedriver_86.0.4240");
        capabilities.setCapability("showChromedriverLog", true);
        capabilities.setCapability("ensureWebviewsHavePages", false);

        capabilities.setCapability("adbPort", 5038);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    void microProgram() throws InterruptedException {
        driver.findElement(By.cssSelector("*[text=微信]"));
        driver.findElement(By.cssSelector("*[text=发现]")).click();
        driver.findElement(By.cssSelector("*[text=小程序]")).click();
        driver.findElement(By.cssSelector("*[text=我的小程序]")).click();
        driver.findElement(By.cssSelector("*[text=京东购物]")).click();

        for (int i = 0; i < 5; i++) {
            System.out.println(driver.getContextHandles());
            Thread.sleep(200);
        }

        String webviewContext = "WEBVIEW_com.tencent.mm:appbrand0";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        System.out.println("context");
        driver.context(webviewContext);
        System.out.println(driver.getPageSource());
        System.out.println(driver.getWindowHandles());
        switchWindow(":VISIBLE");
        System.out.println(driver.getPageSource());
        driver.findElement(By.cssSelector("[data-name=\"京东超市\"]")).click();
        switchWindow("京东超市");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.icon-text"))).click();
    }

    public void switchWindow(String pattern) {
        for (String contextHandle : driver.getContextHandles()) {
            if (contextHandle.contains("appbrand")) {
                System.out.println(contextHandle);
                driver.context(contextHandle);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                for (String window : driver.getWindowHandles()) {
                    driver.switchTo().window(window);
                    String title = driver.getTitle();
                    System.out.println(title);
                    System.out.println(driver.getCurrentUrl());
                    if (title.contains(pattern)) {
                        break;
                    }
                }
            }

        }

    }
}
